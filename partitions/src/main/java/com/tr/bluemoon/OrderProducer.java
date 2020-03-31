package com.tr.bluemoon;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class OrderProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties());

        for (int i = 0; i < 30; i++) {
            String key = UUID.randomUUID().toString();
            String value = key + ",Order:" + i + ", Value:1234";

            ProducerRecord<String, String> record = new ProducerRecord<>("TAX_NEW_ORDER", key, value);

            Callback callback = (data, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                System.out.println("Success send " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset());
            };
            producer.send(record, callback).get();
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
