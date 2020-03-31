package helloworld;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class GetHelloWorld {

    public static void main(String[] args) throws InterruptedException {
        KafkaConsumer consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Collections.singletonList("GREETINGS"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            if (!records.isEmpty()) {
                System.out.println("I found " + records.count() + " records");

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("----------------------------------------");
                    System.out.println("Processing new greetings...");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.offset());
                    try {
                        Thread.sleep(5000);
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
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GetHelloWorld.class.getSimpleName());
        return properties;
    }
}
