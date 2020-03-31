package com.tr.bluemoon;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

/*
//-- Alterar o numero de particões de um tópico ja existente
//--Go to kafka \bin\windows directory
kafka-topics.sh --alter --zookeeper localhost:2181 --topic TAX_NEW_ORDER --partitions 3
*/

public class AccountingEntry {
    public static void main(String[] args) throws InterruptedException {
        KafkaConsumer consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Collections.singletonList("TAX_NEW_ORDER"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {
                System.out.println("I found " + records.count() + " records");

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("----------------------------------------");
                    System.out.println("Processing new order, checking for entry");
                    System.out.println("Key:" + record.key());
                    System.out.println("Value:" + record.value());
                    System.out.println("Partition:" + record.partition());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Processed!");
                }
            }
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, AccountingEntry.class.getSimpleName());
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, AccountingEntry.class.getSimpleName() + "-" + UUID.randomUUID().toString());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}
