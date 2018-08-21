package com.uoko.plugins.cmq.message;

import com.uoko.cmq.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * <p>
 *
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Component
public class TestService {
    public void testQueue() {
        /*
		消息队列 CMQ
        API内网访问信息如下：
            http://cmq-queue-sh.api.tencentyun.com/v2/index.php?Action=CreateQueue&Region=sh&Timestamp=1465750149（）
			&Nonce=46364&SecretId=AKIDpYM3avi2oOmyzowsCL5hDXfeR2RekUCi&Signature=Jnft+GejV+6TdJtkaer6wcqvgK8=
			&queueName=uokoSH-micro-service-MQ
            &pollingWaitSeconds=30

            API签名串信息如下：
                   {$region} 需用具体地域替换：sh（上海）
                   SecretId=AKIDpYM3avi2oOmyzowsCL5hDXfeR2RekUCi
                   Signature=Jnft+GejV+6TdJtkaer6wcqvgK8=
                   src：POSTcmq-queue-sh.api.qcloud.com/v2/index.php?Action=CreateQueue&Nonce=1994404654&RequestClient=SDK_JAVA_1.3&SecretId=AKIDpYM3avi2oOmyzowsCL5hDXfeR2RekUCi&SignatureMethod=HmacSHA1&Timestamp=1534399996&maxMsgSize=1048576&msgRetentionSeconds=345600&pollingWaitSeconds=10&queueName=uokoSH-micro-service-MQ&visibilityTimeout=10
		 */
        //从腾讯云官网查询的云API密钥信息
        String secretId = "AKIDpYM3avi2oOmyzowsCL5hDXfeR2RekUCi";
        String secretKey = "c5M0ToyxpV0JPK2ZXLAqMhplV3QYYN5b";
//        String endpoint = "http://cmq-queue-sh.api.qcloud.com";
        String endpoint = "http://cmq-queue-sh.api.tencentyun.com";

        try {
            Account account = new Account(endpoint, secretId, secretKey);

            //account.deleteQueue("queue-test10");
            //创建队列
            System.out.println("---------------create queue ...---------------");
            QueueMeta meta = new QueueMeta();
            meta.pollingWaitSeconds = 10;
            meta.visibilityTimeout = 10;
            meta.maxMsgSize = 1048576;
            meta.msgRetentionSeconds = 345600;
            account.createQueue("uokoSH-123213-micro-service-MQ", meta);
            System.out.println("queue-test10 created");
            account.createQueue("queue-1231-test11", meta);
            System.out.println("queue-test11 created");
            account.createQueue("queue-21321-test12", meta);
            System.out.println("queue-test12 created");

            //列出当前帐号下所有队列名字
            System.out.println("---------------list queue ...---------------");
            ArrayList<String> vtQueue = new ArrayList<String>();
            int totalCount = account.listQueue("", -1, -1, vtQueue);
            System.out.println("totalCount:" + totalCount);
            for (int i = 0; i < vtQueue.size(); i++) {
                System.out.println("queueName:" + vtQueue.get(i));
            }

            //删除队列
            System.out.println("---------------delete queue ...---------------");
            account.deleteQueue("queue-1231-test11");
            System.out.println("queue-test11 deleted");
            account.deleteQueue("queue-21321-test12");
            System.out.println("queue-test12 deleted");

            //获得队列实例
            System.out.println("--------------- queue[queue-test10] ---------------");
            Queue queue = account.getQueue("uokoSH-123213-micro-service-MQ");

            //设置队列属性
            System.out.println("---------------set queue attributes ...---------------");
            QueueMeta meta1 = new QueueMeta();
            meta1.pollingWaitSeconds = 20;
            queue.setQueueAttributes(meta1);
            System.out.println("pollingWaitSeconds=20 set");

            //获取队列属性
            System.out.println("---------------get queue attributes ...---------------");
            QueueMeta meta2 = queue.getQueueAttributes();
            System.out.println("maxMsgHeapNum:" + meta2.maxMsgHeapNum);
            System.out.println("pollingWaitSeconds:" + meta2.pollingWaitSeconds);
            System.out.println("visibilityTimeout:" + meta2.visibilityTimeout);
            System.out.println("maxMsgSize:" + meta2.maxMsgSize);
            System.out.println("createTime:" + meta2.createTime);
            System.out.println("lastModifyTime:" + meta2.lastModifyTime);
            System.out.println("activeMsgNum:" + meta2.activeMsgNum);
            System.out.println("inactiveMsgNum:" + meta2.inactiveMsgNum);

            //发送消息
            System.out.println("---------------send message ...---------------");
            String msgId = queue.sendMessage("hello world,this is cmq sdk for java");
            System.out.println("[hello world,this is cmq sdk for java] sent");

            //接收消息
            System.out.println("---------------recv message ...---------------");
            Message msg = queue.receiveMessage(10);

            System.out.println("msgId:" + msg.msgId);
            System.out.println("msgBody:" + msg.msgBody);
            System.out.println("receiptHandle:" + msg.receiptHandle);
            System.out.println("enqueueTime:" + msg.enqueueTime);
            System.out.println("nextVisibleTime:" + msg.nextVisibleTime);
            System.out.println("firstDequeueTime:" + msg.firstDequeueTime);
            System.out.println("dequeueCount:" + msg.dequeueCount);

            //删除消息
            System.out.println("---------------delete message ...---------------");
            queue.deleteMessage(msg.receiptHandle);
            System.out.println("receiptHandle:" + msg.receiptHandle + " deleted");

            //批量操作
            //批量发送消息
            System.out.println("---------------batch send message ...---------------");
            ArrayList<String> vtMsgBody = new ArrayList<String>();
            String msgBody = "hello world,this is cmq sdk for java 1";
            vtMsgBody.add(msgBody);
            msgBody = "hello world,this is cmq sdk for java 2";
            vtMsgBody.add(msgBody);
            msgBody = "hello world,this is cmq sdk for java 3";
            vtMsgBody.add(msgBody);
            List<String> vtMsgId = queue.batchSendMessage(vtMsgBody);
            for (int i = 0; i < vtMsgBody.size(); i++) {
                System.out.println("[" + vtMsgBody.get(i) + "] sent");
            }
            for (int i = 0; i < vtMsgId.size(); i++) {
                System.out.println("msgId:" + vtMsgId.get(i));
            }

            //批量接收消息
            ArrayList<String> vtReceiptHandle = new ArrayList<String>(); //保存服务器返回的消息句柄，用于删除消息
            System.out.println("---------------batch recv message ...---------------");
            List<Message> msgList = queue.batchReceiveMessage(10, 10);
            System.out.println("recv msg count:" + msgList.size());
            for (int i = 0; i < msgList.size(); i++) {
                Message msg1 = msgList.get(i);
                System.out.println("msgId:" + msg1.msgId);
                System.out.println("msgBody:" + msg1.msgBody);
                System.out.println("receiptHandle:" + msg1.receiptHandle);
                System.out.println("enqueueTime:" + msg1.enqueueTime);
                System.out.println("nextVisibleTime:" + msg1.nextVisibleTime);
                System.out.println("firstDequeueTime:" + msg1.firstDequeueTime);
                System.out.println("dequeueCount:" + msg1.dequeueCount);

                vtReceiptHandle.add(msg1.receiptHandle);
            }
            //批量删除消息
            System.out.println("---------------batch delete message ...---------------");
            queue.batchDeleteMessage(vtReceiptHandle);
            for (String aVtReceiptHandle : vtReceiptHandle) {
                System.out.println("receiptHandle:" + aVtReceiptHandle + " deleted");
            }

        } catch (CMQServerException e1) {
            System.out.println("Server Exception, " + e1.toString());
        } catch (CMQClientException e2) {
            System.out.println("Client Exception, " + e2.toString());
        } catch (Exception e) {
            System.out.println("error..." + e.toString());
        }
    }

    public void testTopic() {
        // 请在腾讯云官网查看 id ,key endpoint
        String secretId = "AKIDpYM3avi2oOmyzowsCL5hDXfeR2RekUCi";
        String secretKey = "c5M0ToyxpV0JPK2ZXLAqMhplV3QYYN5b";
        String endpoint = "http://cmq-topic-sh.api.tencentyun.com";
        String path = "/v2/index.php";
        String method = "POST";

        try {
            int batchMessageSize = 5;
            // create account;
            Account account = new Account(endpoint, secretId, secretKey);
            // create topic
            System.out.println("init account ");
            String topicName = "topic-test";
            account.createTopic(topicName, 1024 * 1024);

            System.out.println("create topic");
            // get topic meta
            Topic topic = account.getTopic(topicName);
            TopicMeta topicMeta = new TopicMeta();


            // set  and get topic meta
            topicMeta.maxMsgSize = 32768;
            topic.setTopicAttributes(topicMeta.maxMsgSize);
            topicMeta = topic.getTopicAttributes();

            System.out.println("set and get topic meta  ");

            // publish message and batch publish message
            Vector<String> vMsg = new Vector<String>();
            for (int i = 0; i < batchMessageSize; ++i) {
                String msg = "this is a test message publish";
                vMsg.add(msg);
            }

            // publish message without tag
            String msg = "this is a test message";
            topic.publishMessage(msg);
            System.out.println("publish message  ");
            topic.batchPublishMessage(vMsg);
            System.out.println(" batch publish message");
            Vector<String> vTopicList = new Vector<String>();
            account.listTopic("", vTopicList, -1, -1);
            for (String TopicName : vTopicList) {
                System.out.println(TopicName);
            }

            System.out.println(" list topic");
            // create subscription input your endpoint and protocal
            String subscriptionName = "sub-test";
            String Endpoint = "";
            String Protocal = "";
            account.createSubscribe(topicName, subscriptionName, Endpoint, Protocal);

            System.out.println("create sub ");
            // set subscription meta
            Subscription sub = account.getSubscription(topicName, subscriptionName);
            SubscriptionMeta subscriptionMeta = sub.getSubscriptionAttributes();

            System.out.println("set sub meta  ");
            // list subscription
            ArrayList<String> vSubscription = new ArrayList<String>();

            int SubscriptionCount = topic.ListSubscription(-1, -1, "", vSubscription);
            for (String subscription : vSubscription) {
                System.out.println("Subscription name :" + subscription);
            }
            System.out.println("list sub ");
            // delete subscription and topic
            account.deleteSubscribe(topicName, subscriptionName);
            System.out.println("delete sub ");
            account.deleteTopic(topicName);
            System.out.println("delete topic  ");

        } catch (CMQServerException e1) {
            System.out.println("Server Exception, " + e1.toString());
        } catch (CMQClientException e2) {
            System.out.println("Client Exception, " + e2.toString());
        } catch (Exception e) {
            System.out.println("error..." + e.toString());
        }
    }
}
