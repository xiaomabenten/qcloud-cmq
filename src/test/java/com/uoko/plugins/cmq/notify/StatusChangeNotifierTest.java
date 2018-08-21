package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationStatusChangedEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class StatusChangeNotifierTest {

    private StatusChangeNotifier statusChangeNotifier;

    @Before
    public void setup() {
        statusChangeNotifier = new StatusChangeNotifier();
    }

    @Test
    public void doNotify() {

        statusChangeNotifier.notify(new CMQApplicationStatusChangedEvent( "21321321"));
    }
}