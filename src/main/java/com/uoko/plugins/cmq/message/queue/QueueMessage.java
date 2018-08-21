package com.uoko.plugins.cmq.message.queue;

import com.uoko.plugins.cmq.message.Message;

import java.util.List;

/**
 * <p>
 * 消息内容处理接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface QueueMessage {

    /**
     * 发送消息
     *
     * @param msgQueue 消息队列
     * @param msgBody  消息内容
     * @return 消息id
     */
    String sendMessage(String msgQueue, String msgBody) throws Exception;


    /**
     * 发送消息
     *
     * @param msgQueue  消息队列
     * @param msgBody   消息内容
     * @param delayTime 消息延迟时间 延迟消息范围 0-3600s
     * @return 消息id
     * @throws Exception
     */
    String sendMessage(String msgQueue, String msgBody, int delayTime) throws Exception;

    /**
     * 消息回溯（当消息被删除后，消息在一定时间内会重新回到消息队列中），此方法仅用于，需要确保消息100%达到使用
     *
     * @param msgQueue         消息队列名
     * @param backTrackingTime 设定该时间，则（Batch）receiveMessage 接口，会按照生产消息的先后顺序消费该时间戳以后的消息。
     *                        （回溯时间不能大于消息保留时间。）
     * @throws Exception
     */
    void rewindQueue(String msgQueue, int backTrackingTime) throws Exception;

    /**
     * 批量发送消息
     *
     * @param msgQueue  消息队列
     * @param vtMsgBody 消息内容列表
     * @return
     * @throws Exception
     */
    List<String> batchSendMessage(String msgQueue, List<String> vtMsgBody) throws Exception;

    /**
     * 批量发送延迟消息
     *
     * @param msgQueue  消息队列
     * @param vtMsgBody 消息内容列表
     * @param delayTime 消息延迟时间 延迟消息范围 0-3600s
     * @return
     * @throws Exception
     */
    List<String> batchSendMessage(String msgQueue, List<String> vtMsgBody, int delayTime) throws Exception;


    /**
     * 获取消息内容
     *
     * @param msgQueue           消息队列
     * @param pollingWaitSeconds 请求最长的Polling等待时间
     * @return 服务器返回消息
     * @throws Exception
     */
    Message receiveMessage(String msgQueue, int pollingWaitSeconds) throws Exception;

    /**
     * 批量获取消息
     *
     * @param msgQueue           消息队列
     * @param numOfMsg           准备获取消息数
     * @param pollingWaitSeconds 请求最长的Polling等待时间
     * @return
     * @throws Exception
     */
    List<Message> batchReceiveMessage(String msgQueue, int numOfMsg, int pollingWaitSeconds) throws Exception;

    /**
     * 删除消息
     *
     * @param msgQueue      消息队列
     * @param receiptHandle 消息句柄,获取消息时由服务器返回
     * @throws Exception
     */
    void deleteMessage(String msgQueue, String receiptHandle) throws Exception;

    /**
     * 批量删除
     *
     * @param msgQueue        消息队列
     * @param vtReceiptHandle 消息句柄列表，获取消息时由服务器返回
     * @throws Exception
     */
    void batchDeleteMessage(String msgQueue, List<String> vtReceiptHandle) throws Exception;
}
