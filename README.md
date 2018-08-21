###uoko cmq 消息对列使用文档

## 概要

cmq消息队列：采用腾讯云消息队列（Cloud Message Queue，CMQ）是一种分布式消息队列服务，它能够提供可靠的基于消息的异步通信机制，能够将分布式部署的不同应用（或同一应用的不同组件）之间的收发消息，存储在可靠有效的 CMQ 队列中，防止消息丢失。 CMQ 支持多进程同时读写，收发互不干扰，无需各应用或组件始终处于运行状态。 [腾讯云CMQ官方文档](https://cloud.tencent.com/document/product/406)

## 特性

-  消息队列（Queue）模型
-  消息主题（Topic）模型  ****消息投递无法投递私网***

## 使用
**maven**
```aidl
      <dependency>
            <groupId>com.uoko.framework</groupId>
            <artifactId>uoko-framework-cmq</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

## 举例

###消息队列（Queue）模型

* 创建队列
   传入参数 ：
    * queueName：队列名字，在单个地域同一帐号下唯一。队列名称是一个不超过， 64 个字符的字符串，必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueue.createMessageQueue(queueName);

.....
```

* 获取队列列表
   传入参数 ：
     * @param searchWord 用于过滤队列列表，后台用模糊匹配的方式来返回符合条件的队列列表。如果不填该参数，默认返回帐号下的所有队列。
     * @param offset     分页时本页获取队列列表的起始位置。如果填写了该值，必须也要填写 limit 。该值缺省时，后台取默认值 0
     * @param limit      分页时本页获取队列的个数，如果不传递该参数，则该参数默认为 0，最大值为 0。

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueue.listQueue(searchWord, offset, limit);

.....
```

* 删除队列
   传入参数 ：
   * @param queueName 消息队列名

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueue.deleteQueue(queueName);

.....
```

* 批量删除队列
   传入参数 ：
     * @param msgQueue        消息队列
     * @param vtReceiptHandle 消息句柄列表，获取消息时由服务器返回

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueueMessage.batchDeleteMessage(msgQueue, vtReceiptHandle);

.....
```
* 发送消息
   传入参数 ：
   * @param msgQueue 消息队列
    * @param msgBody  消息内容
    * @return 消息id

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueueMessage.sendMessage(msgQueue, msgBody);

.....
```


* 发送延时消息
   传入参数 ：
* @param msgQueue  消息队列
     * @param msgBody   消息内容
     * @param delayTime 消息延迟时间 延迟消息范围 0-3600s
     * @return 消息id

 案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

cmqQueueMessage.sendMessage(msgQueue, msgBody, delayTime);

.....
```

* 批量发送消息
   传入参数 ：
   * @param msgQueue  消息队列
     * @param vtMsgBody 消息内容列表
    * @return 消息id List

  案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

cmqQueueMessage.batchSendMessage(msgQueue, vtMsgBody);

.....
```


* 批量发送延迟消息
   传入参数 ：
     * @param msgQueue  消息队列
     * @param vtMsgBody 消息内容列表
     * @param delayTime 消息延迟时间 延迟消息范围 0-3600s
     * @return

 案例: 
  
```
@Autowired
private CMQQueue cmqQueue;

......

 cmqQueueMessage.batchSendMessage(msgQueue, vtMsgBody, delayTime);

.....
```

* 单个获取消息内容 （注意，此接口需要使用cmq的长轮训结合定时器，周期性拉去数据，需要我们手动pull）
   传入参数 ：
     * @param msgQueue           消息队列
     * @param pollingWaitSeconds 请求最长的Polling等待时间
     * @return 服务器返回消息

 案例: 
  
```
@Autowired
private CMQQueueMessage cmqQueueMessage;
......

 Message message = cmqQueueMessage.receiveMessage(msgQueue, pollingWaitSeconds);

.....
```


* 批量获取消息
   传入参数 ：
    * @param msgQueue           消息队列
     * @param numOfMsg           准备获取消息数
     * @param pollingWaitSeconds 请求最长的Polling等待时间
     * @return 服务器返回消息

 案例: 

```
/**
 * <p>
 *   队列接收消息通知接收器
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/15
 */
public class QueueStatusChangeNotifier extends AbstractStatusChangeNotifier {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doNotify(CMQApplicationEvent cmqApplicationEvent) throws Exception {
        if (cmqApplicationEvent instanceof CMQApplicationStatusChangedEvent) {
            logger.info("接收到消息");
        }
        logger.info("接收到消息不对");

    }
}
```


```
@Autowired
private CMQQueueMessage cmqQueueMessage;
@Autowired
 private QueueStatusChangeNotifier queueStatusChangeNotifier;
......

     Message message = cmqQueueMessage.receiveMessage(msgQueue, pollingWaitSeconds);
      if (message != null) {
//通知notify
           queueStatusChangeNotifier.notify(new CMQApplicationStatusChangedEvent("12", "1231", "12321"));
       }

.....
```

###消息主题（Topic）模型


* 创建主题
   传入参数 ：

     * @param topicName  主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
     *                   必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param maxMsgSize 消息最大长度。取值范围 1024-65536 Byte（即1-64K），默认值 65536。

 案例: 
  
```
@Autowired
private Topic topic;
......

  topic.createTopic(topicName, maxMsgSize);

.....
```


* 获取主题列表
   传入参数 ：

     * @param searchWord 用于过滤主题列表，后台用模糊匹配的方式来返回符合条件的主题列表。如果不填该参数，默认返回帐号下的所有主题。
     * @param offset     分页时本页获取主题列表的起始位置。如果填写了该值，必须也要填写 limit 。该值缺省时，后台取默认值 0
     * @param limit      分页时本页获取主题的个数，如果不传递该参数，则该参数默认为 20，最大值为 50。
     * @return 主题列表信息列表

 案例: 
  
```
@Autowired
private Topic topic;
......

 topic.listTopic(searchWord, offset, limit);

.....
```


* 删除主题
   传入参数 ：

     * @param topicName 主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
                      必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。

 案例: 
  
```
@Autowired
private Topic topic;
......

   topic.deleteTopic(topicName);

.....
```



* 清空订阅标签
   传入参数 ：

     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
                             必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。
                             订阅名称是一个不超过 64 个字符的字符串，必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。

 案例: 
  
```
@Autowired
 private Subscription subscription;
......

 subscription.clearFilterTags(topicName, subscriptionName);

.....
```



* 创建订阅
   传入参数 ：

     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
                            必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。订阅名称是一个不超过 64 个字符的字符串，
                            必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。

 案例: 
  
```
@Autowired
 private Subscription subscription;
......

  subscription.createSubscribe(topicName, subscriptionName, endpoint, Protocal);

.....
```



* 获取订阅列表
   传入参数 ：

     * @param topicName  主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
                       必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param offset     分页时本页获取订阅列表的起始位置。如果填写了该值，必须也要填写 limit。
                      该值缺省时，后台取默认值 0。取值范围 0-1000。
     * @param limit      分页时本页获取订阅的个数，该参数取值范围 0-100。如果不传递该参数，则该参数默认为 20。
     * @param searchWord 用于过滤订阅列表，后台用模糊匹配的方式来返回符合条件的订阅列表。如果不填该参数，默认返回帐号下的所有订阅。

 案例: 
  
```
@Autowired
 private Subscription subscription;
......

subscription.ListSubscription(topicName, offset, limit, searchWord);

.....
```


* 删除订阅
   传入参数 ：

     * @param topicName        主题名字，在单个地域同一帐号下唯一。主题名称是一个不超过 64 个字符的字符串，
                              必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。
     * @param subscriptionName 订阅名字，在单个地域同一帐号的同一主题下唯一。订阅名称是一个不超过 64 个字符的字符串，
                             必须以字母为首字符，剩余部分可以包含字母、数字和横划线(-)。

 案例: 
  
```
@Autowired
 private Subscription subscription;
......

subscription.deleteSubscribe(topicName, subscriptionName);

.....
```

* 发布消息
   传入参数 ：

      * @param message 消息正文。至少 1 Byte，最大长度受限于设置的主题消息最大长度属性。

 案例: 
  
```
@Autowired
  private TopicMessage topicMessage;
......

topicMessage.publishMessage(topicName, message);

.....
```


* 批量发布消息
   传入参数 ：

      * @param vMsgList消息正文。至少 1 Byte，最大长度受限于设置的主题消息最大长度属性。

 案例: 
  
```
@Autowired
  private TopicMessage topicMessage;
......

topicMessage.batchPublishMessage(topicName, vMsgList);

.....
```

* 投递消息(腾讯云push到每个服务)
需要业务自行封装post请求 [投递消息](https://cloud.tencent.com/document/product/406/7420)