package com.uoko.plugins.cmq.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 腾讯 cmq 配置信息
 * </p>
 *
 * @author shaohua
 * Email shaohua@uoko.com
 * created by 2018/8/16
 */
public class CMQProperties {
    public CMQProperties() {
    }

    public static String getSecretId() {
        return getConfig().getString("secretId");
    }

    public static String getSecretKey() {
        return getConfig().getString("secretKey");
    }


    public static String getEndpoint() {
        return getConfig().getString("endpoint");
    }
    public static String getTopicEndpoint() {
        return getConfig().getString("topicEndpoint");
    }

    /**
     * 获取Generator 配置文件
     *
     * @return
     */
    public static Configuration getConfig() {
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        try {
            configuration.load(new InputStreamReader(CMQProperties.class.getClassLoader().getResourceAsStream("cmq.properties"), "UTF-8"));
        } catch (ConfigurationException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("配置文件获取失败,请检查---> cmq.properties 文件是否存在", e);
        }
        return configuration;
    }
}
