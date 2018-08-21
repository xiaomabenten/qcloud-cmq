package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 通知器，它提醒某些状态使用委托发送提醒通知
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class RemindingNotifier extends AbstractEventNotifier {
    private final ConcurrentHashMap<String, Reminder> reminders = new ConcurrentHashMap<>();
    private long reminderPeriod = TimeUnit.MINUTES.toMillis(10L);
    private final Notifier delegate;

    public RemindingNotifier(Notifier delegate) {
        Assert.notNull(delegate, "'delegate' must not be null!");
        this.delegate = delegate;
    }

    @Override
    public void doNotify(CMQApplicationEvent event) {
        delegate.notify(event);
    }

    public void sendReminders() {
        long now = System.currentTimeMillis();
        for (Reminder reminder : new ArrayList<>(reminders.values())) {
            if (now - reminder.getLastNotification() > reminderPeriod) {
                reminder.setLastNotification(now);
                //pushlish 发送过来，然后这边刷新处理 不采用异步
                delegate.notify(reminder.getEvent());
            }
        }
    }

    public void setReminderPeriod(long reminderPeriod) {
        this.reminderPeriod = reminderPeriod;
    }


    private static class Reminder {
        private final CMQApplicationEvent event;
        private long lastNotification;

        private Reminder(CMQApplicationEvent event) {
            this.event = event;
            this.lastNotification = event.getTimestamp();
        }

        public void setLastNotification(long lastNotification) {
            this.lastNotification = lastNotification;
        }

        public long getLastNotification() {
            return lastNotification;
        }

        public CMQApplicationEvent getEvent() {
            return event;
        }
    }
}
