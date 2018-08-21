package com.uoko.plugins.cmq.message.queue;

import java.util.ArrayList;

/**
 * <p>
 * 消息队列暴露接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface Queue {

    /**
     * 创建队列
     *
     * @param queueName 队列名字，在单个地域同一帐号下唯一。队列名称是一个不超过
     *                  64 个字符的字符串，必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @return
     */
    void createMessageQueue(String queueName) throws Exception;

    /**
     * 获取队列列表
     *
     * @param searchWord 用于过滤队列列表，后台用模糊匹配的方式来返回符合条件的队列列表。如果不填该参数，默认返回帐号下的所有队列。
     * @param offset     分页时本页获取队列列表的起始位置。如果填写了该值，必须也要填写 limit 。该值缺省时，后台取默认值 0
     * @param limit      分页时本页获取队列的个数，如果不传递该参数，则该参数默认为 0，最大值为 0。
     * @return
     */
    ArrayList<String> listQueue(String searchWord, int offset, int limit) throws Exception;


    /**
     * 删除队列
     *
     * @param queueName 消息队列名
     * @return
     */
    void deleteQueue(String queueName) throws Exception;

}