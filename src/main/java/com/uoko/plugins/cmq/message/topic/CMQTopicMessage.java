package com.uoko.plugins.cmq.message.topic;

import com.uoko.cmq.Account;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * <p>
 * 腾讯云 cmq topic message
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Component
public class CMQTopicMessage implements TopicMessage {
    @Resource(name = "topicAccount")
    private Account account;

    @Override
    public String publishMessage(String topicName, String message) throws Exception {
        return account.getTopic(topicName).publishMessage(message);
    }

    @Override
    public Vector<String> batchPublishMessage(String topicName, List<String> vMsgList) throws Exception {
        return account.getTopic(topicName).batchPublishMessage(vMsgList);
    }


}
