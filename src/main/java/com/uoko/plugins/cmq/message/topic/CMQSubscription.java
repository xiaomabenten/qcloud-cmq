package com.uoko.plugins.cmq.message.topic;

import com.uoko.cmq.Account;
import com.uoko.cmq.CMQClient;
import com.uoko.cmq.Topic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 腾讯云 cmq 订阅
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Component
public class CMQSubscription implements Subscription {
    @Resource(name = "topicAccount")
    private Account account;
    @Resource(name = "cmqTopicClient")
    private CMQClient cmqClient;

    @Override
    public void clearFilterTags(String topicName, String subscriptionName) throws Exception {
        com.uoko.cmq.Subscription subscription = new com.uoko.cmq.Subscription(topicName, subscriptionName, cmqClient);
        subscription.ClearFilterTags();
    }


    @Override
    public void createSubscribe(String topicName, String subscriptionName, String Endpoint, String Protocal) throws Exception {
        // 请注意，目前推送服务不能推送到私有网络中，因此 endpoint 填写为私有网络域名或地址将接收不到推送的消息，目前支持推送到公网和基础网络。
        account.createSubscribe(topicName, subscriptionName, Endpoint, Protocal);
    }

    @Override
    public List<String> ListSubscription(String topicName, int offset, int limit, String searchWord) throws Exception {
        Topic topic = account.getTopic(topicName);
        ArrayList<String> vSubscription = new ArrayList<String>();
        topic.ListSubscription(offset, limit, searchWord, vSubscription);
        return vSubscription;
    }

    @Override
    public void deleteSubscribe(String topicName, String subscriptionName) throws Exception {
        account.deleteSubscribe(topicName, subscriptionName);
    }

    // TODO: 2018/8/16 消息消费端 post发送到接口中 https://cloud.tencent.com/document/api/406/7420
}
