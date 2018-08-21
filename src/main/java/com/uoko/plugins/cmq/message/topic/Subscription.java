package com.uoko.plugins.cmq.message.topic;

import java.util.List;

/**
 * <p>
 * 订阅消息相关接口
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public interface Subscription {

    /**
     * 清空订阅标签
     *
     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                         必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。
     *                         订阅名称是一个不超过 64 个字符的字符串，必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @throws Exception
     */
    void clearFilterTags(String topicName, String subscriptionName) throws Exception;


    /**
     * 创建订阅
     *
     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                         必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。订阅名称是一个不超过 64 个字符的字符串，
     *                         必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @throws Exception
     */
    void createSubscribe(final String topicName, final String subscriptionName, String Endpoint, String Protocal) throws Exception;

    /**
     * 获取订阅列表
     *
     * @param topicName  主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                   必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param offset     分页时本页获取订阅列表的起始位置。如果填写了该值，必须也要填写 limit。
     *                   该值缺省时，后台取默认值 0。取值范围 0-1000。
     * @param limit      分页时本页获取订阅的个数，该参数取值范围 0-100。如果不传递该参数，则该参数默认为 20。
     * @param searchWord 用于过滤订阅列表，后台用模糊匹配的方式来返回符合条件的订阅列表。如果不填该参数，默认返回帐号下的所有订阅。
     * @return
     * @throws Exception
     */
    List<String> ListSubscription(String topicName, int offset, int limit, String searchWord) throws Exception;

    /**
     * 删除订阅
     *
     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                         必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。订阅名称是一个不超过 64 个字符的字符串，
     *                         必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @throws Exception
     */
    void deleteSubscribe(final String topicName, final String subscriptionName) throws Exception;


}
