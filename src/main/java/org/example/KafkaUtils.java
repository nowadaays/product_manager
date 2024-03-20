package org.example;

import org.example.configuration.KafkaConfig;

import java.util.Properties;

public class KafkaUtils {
    public static Properties getProducerProperties(KafkaConfig kafkaConfig) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
