package com.uoko.plugins.cmq.notify;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class TestListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("接收到消息");
    }
}
