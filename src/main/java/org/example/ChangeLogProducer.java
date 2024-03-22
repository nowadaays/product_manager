package org.example;

import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ChangeLogProducer {

    private static final String TOPIC_NAME = "change-log-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    private final Producer<String, String> producer;

    public ChangeLogProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    public void sendChangeLog(String change){
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, change);
        producer.send(record , ((metadata, exception) -> {
            if (exception != null) {
                System.err.println("Failed to send change log: " + exception.getMessage());
            } else {
                System.out.println("Change log sent successfully: " + metadata.topic() + ", " + metadata.partition() + ", " + metadata.offset());
            }
        }));
    }

    public void close(){
        producer.close();
    }
}
