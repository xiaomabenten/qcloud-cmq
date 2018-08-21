package com.uoko.plugins.cmq.message.topic;

import java.util.List;

/**
 * <p>
 * 消息主题接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface Topic {
    /**
     * 创建主题
     *
     * @param topicName  主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                   必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param maxMsgSize 消息最大长度。取值范围 1024-65536 Byte（即1-64K），默认值 65536。
     * @throws Exception
     */
    void createTopic(final String topicName, final int maxMsgSize) throws Exception;

    /**
     * 获取主题列表
     *
     * @param searchWord 用于过滤主题列表，后台用模糊匹配的方式来返回符合条件的主题列表。如果不填该参数，默认返回帐号下的所有主题。
     * @param offset     分页时本页获取主题列表的起始位置。如果填写了该值，必须也要填写 limit 。该值缺省时，后台取默认值 0
     * @param limit      分页时本页获取主题的个数，如果不传递该参数，则该参数默认为 20，最大值为 50。
     * @return 主题列表信息列表
     */
    List<String> listTopic(final String searchWord, final int offset, final int limit) throws Exception;

    /**
     * 删除主题
     *
     * @param topicName 主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                  必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @throws Exception
     */
    void deleteTopic(final String topicName) throws Exception;
}
