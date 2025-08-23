# Commerce Unity

### Docker + Kafka (Producer + Consumer)

---

**Start Kafka and Zookeeper**
```json5
docker-compose up -d
```
---
**Check if Kafka is running**
```json5
docker ps
```
---
**Create kafka topicr**
```json5
docker exec -it kafka-docker-kafka-1 \
kafka-topics --create \
--topic quickstart-events \
--bootstrap-server localhost:9092
```
---
**List kafka topics**
```json5
docker exec -it kafka-docker-kafka-1 \
kafka-topics --list \
--bootstrap-server localhost:9092
```
---
**Produce messages to the topic**
```json5
docker exec -it kafka-docker-kafka-1 \
kafka-console-producer --topic quickstart-events \
--bootstrap-server localhost:9092
```
---
**Consume messages from the topic**
```json5
docker exec -it kafka-docker-kafka-1 \
kafka-console-consumer --topic quickstart-events \
--from-beginning \
--bootstrap-server localhost:9092
```
---
**stop kafka**
```json5
docker-compose down
```