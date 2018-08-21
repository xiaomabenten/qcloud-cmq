package com.uoko.plugins.cmq.message.queue;

import com.uoko.cmq.Account;
import com.uoko.cmq.QueueMeta;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 * 腾讯云 cmq 消息
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
@Component
public class CMQQueue implements Queue {

    @Resource(name = "messageAccount")
    private Account account;

    @Override
    public void createMessageQueue(String queueName) throws Exception {
        QueueMeta meta = new QueueMeta();
        meta.pollingWaitSeconds = 10;
        meta.visibilityTimeout = 10;
        meta.maxMsgSize = 1048576;
        meta.msgRetentionSeconds = 345600;
        account.createQueue(queueName, meta);
    }

    @Override
    public ArrayList<String> listQueue(String searchWord, int offset, int limit) throws Exception {
        ArrayList<String> vtQueue = new ArrayList<String>();
        account.listQueue("", offset, limit, vtQueue);
        return vtQueue;
    }

    @Override
    public void deleteQueue(String queueName) throws Exception {
        account.deleteQueue(queueName);
    }
}
