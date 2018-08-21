package com.uoko.plugins.cmq.message.topic;

import com.uoko.cmq.Account;
import com.uoko.cmq.CMQClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 腾讯云 cmq topic
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Component
public class CMQTopic implements Topic {

    @Resource(name = "topicAccount")
    private Account account;
    @Override
    public void createTopic(String topicName, int maxMsgSize) throws Exception {
        account.createTopic(topicName, maxMsgSize);
    }

    @Override
    public List<String> listTopic(String searchWord, int offset, int limit) throws Exception {
        List<String> vTopicList = new ArrayList<>();
        account.listTopic(searchWord, vTopicList, offset, limit);
        return vTopicList;
    }

    @Override
    public void deleteTopic(String topicName) throws Exception {
        account.deleteTopic(topicName);
    }
}
