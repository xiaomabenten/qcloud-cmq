package com.uoko.plugins.cmq.message.queue;

import com.uoko.cmq.Account;
import com.uoko.plugins.cmq.message.Message;
import com.uoko.cmq.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 腾讯云 cmq消息队列发送消息
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Component
public class CMQQueueMessage implements QueueMessage {
    @Resource(name = "messageAccount")
    private Account account;

    @Override
    public String sendMessage(String msgQueue, String msgBody) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.sendMessage(msgBody);
    }

    @Override
    public String sendMessage(String msgQueue, String msgBody, int delayTime) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.sendMessage(msgBody, delayTime);
    }

    @Override
    public void rewindQueue(String msgQueue, int backTrackingTime) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        queue.rewindQueue(backTrackingTime);
    }

    @Override
    public List<String> batchSendMessage(String msgQueue, List<String> vtMsgBody) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.batchSendMessage(vtMsgBody);
    }

    @Override
    public List<String> batchSendMessage(String msgQueue, List<String> vtMsgBody, int delayTime) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.batchSendMessage(vtMsgBody, delayTime);
    }

    @Override
    public Message receiveMessage(String msgQueue, int pollingWaitSeconds) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.receiveMessage(pollingWaitSeconds);
    }

    @Override
    public List<Message> batchReceiveMessage(String msgQueue, int numOfMsg, int pollingWaitSeconds) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        return queue.batchReceiveMessage(numOfMsg, pollingWaitSeconds);

    }

    @Override
    public void deleteMessage(String msgQueue, String receiptHandle) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        queue.deleteMessage(receiptHandle);
    }

    @Override
    public void batchDeleteMessage(String msgQueue, List<String> vtReceiptHandle) throws Exception {
        Queue queue = account.getQueue(msgQueue);
        queue.batchDeleteMessage(vtReceiptHandle);
    }
}
