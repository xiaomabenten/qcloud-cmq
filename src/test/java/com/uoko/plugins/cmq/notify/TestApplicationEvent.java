package com.uoko.plugins.cmq.notify;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class TestApplicationEvent extends ApplicationEvent {
    public TestApplicationEvent(Object source) {
        super(source);
    }
}
