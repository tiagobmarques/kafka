##Using Kafka in the Docker

```
docker-compose up
```

##Using Kafka installed in machine

Download bin ![kafka](https://kafka.apache.org/downloads)

### Start zookeeper

```
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

### Start kafka server

```
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Tips

### Creating topic
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin

```
Windows
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic GREETINGS

Docker
kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic GREETINGS
```

### Listing topic
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin

```
Windows
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

Docker
kafka-topics.sh --list --bootstrap-server localhost:9092
```

### Describe topic
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin 

```
Windows
kafka-topics.bat --bootstrap-server localhost:9092 --describe

Docker
kafka-topics.sh --bootstrap-server localhost:9092 --describe
```

### Deleting topic
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin 

```
Windows
kafka-topics.bat --zookeeper localhost:2181 --delete --topic PocKafka

Docker
kafka-topics.sh --zookeeper localhost:2181 --delete --topic PocKafka
```

### Creating producer
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin 

```
Windows
kafka-console-producer.bat --broker-list localhost:9092 --topic PocKafka

Docker
kafka-console-producer.sh --broker-list localhost:9092 --topic PocKafka
```
The broker-list parameter specifies the brokers to be connected as <node_address:port> — that is, localhost:9092.

### Creating consumer
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin 

```
Windows
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic PocKafka

Docker
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic PocKafka
```
