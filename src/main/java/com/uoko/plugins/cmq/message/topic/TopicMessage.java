package com.uoko.plugins.cmq.message.topic;

import java.util.List;
import java.util.Vector;

/**
 * <p>
 * topic 消息 相关接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface TopicMessage {
    /**
     * 发布消息
     *
     * @param message 消息正文。至少 1 Byte，最大长度受限于设置的主题消息最大长度属性。
     * @return
     * @throws Exception
     */
    String publishMessage(String topicName, String message) throws Exception;

    /**
     * 批量发布消息
     *
     * @param vMsgList
     * @return
     * @throws Exception
     */
    Vector<String> batchPublishMessage(String topicName, List<String> vMsgList) throws Exception;

}
