package com.uoko.plugins.cmq.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class RemindingNotifierTest {
    private RemindingNotifier notifier;

    @Autowired
    private ApplicationContext applicationContext;
//    @Before
//    public void setup() {
//        TestNotifier
//        notifier = new RemindingNotifier();
//    }

    @org.junit.Test
    public void doNotify() {
//        TestListener testListener = new TestListener();
//        StatusChangeNotifier statusChangeNotifier = new StatusChangeNotifier();
//        FilteringNotifier filteringNotifier = new FilteringNotifier(statusChangeNotifier);
//        notifier = new RemindingNotifier(filteringNotifier);
        //long version, String type, String mgs
//        notifier.notify(new CMQApplicationStatusChangedEvent("100", "queue", "happy"));
//        applicationContext.publishEvent(new TestApplicationEvent("ASDA"));
    }
}
