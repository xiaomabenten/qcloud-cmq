package com.uoko.plugins.cmq.event;

import java.io.Serializable;

/**
 * <p>
 * 抽象事件重新定义spring引导管理客户端
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public abstract class CMQApplicationEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private final long timestamp;

    protected CMQApplicationEvent() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

}
