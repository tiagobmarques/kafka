## Scenario

-One topic with three partitions(execute the command below with --partitions 3)
-One Producer with the name OrderProducer
-One Consumer with the name AccountingEntry

## Using Kafka in the Docker

```
docker-compose up
```

## Using Kafka installed in machine

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
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic TAX_NEW_ORDER

Docker
kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 3 --topic TAX_NEW_ORDER
```
### Describe topic
-Go to kafka \bin\windows directory, in the Docker go to opt/bitnami/kafka/bin 

```
Windows
kafka-topics.bat --bootstrap-server localhost:9092 --describe

Docker
kafka-consumer-groups.sh --all-groups --bootstrap-server localhost:9092 --describe
```

