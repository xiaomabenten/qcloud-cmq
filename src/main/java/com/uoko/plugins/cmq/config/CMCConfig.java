package com.uoko.plugins.cmq.config;

import com.uoko.cmq.Account;
import com.uoko.cmq.CMQClient;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 腾讯 cmq 的 accout，Client配置初始化
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
@Configuration
public class CMCConfig {

    @Bean(autowire = Autowire.BY_NAME, value = "messageAccount")
    Account messageAccount() {
        return new Account(CMQProperties.getEndpoint(),
                CMQProperties.getSecretId(), CMQProperties.getSecretKey());
    }

    @Bean(autowire = Autowire.BY_NAME, value = "topicAccount")
    Account topicAccount() {
        return new Account(CMQProperties.getTopicEndpoint(),
                CMQProperties.getSecretId(), CMQProperties.getSecretKey());
    }


    @Bean(autowire = Autowire.BY_NAME, value = "cmqMessageClient")
    CMQClient cmqMessageClient() {
        return new CMQClient(CMQProperties.getEndpoint(), "/v2/index.php", CMQProperties.getSecretId(), CMQProperties.getSecretKey(), "POST");
    }

    @Bean(autowire = Autowire.BY_NAME, value = "cmqTopicClient")
    CMQClient cmqTopicClient() {
        return new CMQClient(CMQProperties.getTopicEndpoint(), "/v2/index.php", CMQProperties.getSecretId(), CMQProperties.getSecretKey(), "POST");
    }
}
