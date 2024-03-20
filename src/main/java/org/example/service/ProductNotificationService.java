package org.example.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.KafkaUtils;
import org.example.configuration.KafkaConfig;
import org.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductNotificationService {

    @Autowired
    private KafkaConfig kafkaConfig;

    public void sendNewProductNotification(String productId) {
        String topic = "new-product";
        String message = "New product added: " + productId;

        try (KafkaProducer<String , String> producer = new KafkaProducer<>(KafkaUtils.getProducerProperties(kafkaConfig))){
            producer.send(new ProducerRecord<>(topic, message));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void sendProductUpdate(Product product) {
        String topic = "product-update";
        String message = "Product updated: " + product.getId();

        try (KafkaProducer<String , String> producer = new KafkaProducer<>(KafkaUtils.getProducerProperties(kafkaConfig))){
            producer.send(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendProductCreated(Product product) {
        String topic = "product-created";
        String message = "Product created: " + product.getId();

        try (KafkaProducer<String , String> producer = new KafkaProducer<>(KafkaUtils.getProducerProperties(kafkaConfig))){
            producer.send(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
