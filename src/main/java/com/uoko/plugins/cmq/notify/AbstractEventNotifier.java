package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 抽象通知器，允许禁用和过滤事件
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public abstract class AbstractEventNotifier implements Notifier {
    /**
     * Enables the notification.
     */
    private boolean enabled = true;

    @Override
    public void notify(CMQApplicationEvent event) {
        System.out.println("set AbstractEventNotifier：" + event.toString());
        if (enabled && shouldNotify(event)) {
            try {
                doNotify(event);
            } catch (Exception ex) {
                getLogger().error("Couldn't notify for event {} ", event, ex);
            }
        }
    }

    protected boolean shouldNotify(CMQApplicationEvent event) {
        return true;
    }

    protected abstract void doNotify(CMQApplicationEvent event) throws Exception;

    private Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
