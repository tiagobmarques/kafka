Download bin ![kafka](https://kafka.apache.org/downloads)

## Start zookeeper

```
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

## Start kafka server

```
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Creating topic
-Go to kafka \bin\windows directory 

```
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic PocKafka
```

## Listing topic

```
kafka-topics.bat --list --bootstrap-server localhost:9092
```

## Describe topic

```
kafka-topics.bat --bootstrap-server localhost:9092 --describe
```

## Deleting topic

```
.\bin\kafka-topics.bat --zookeeper localhost:2181 --delete --topic PocKafka
```

## Creating producer

```
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic PocKafka

```
The broker-list parameter specifies the brokers to be connected as <node_address:port> — that is, localhost:9092.

## Creating consumer
-Go to kafka \bin\windows directory 

```
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic PocKafka
```
