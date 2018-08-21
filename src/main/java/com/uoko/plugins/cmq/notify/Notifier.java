package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;

/**
 * <p>
 * 在客户端中发出状态更改通知的组件的接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface Notifier {

    /**
     * 通知消息
     *
     * @param event
     */
    void notify(CMQApplicationEvent event);
}
