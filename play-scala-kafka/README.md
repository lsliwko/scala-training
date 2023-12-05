# Task description
https://www.baeldung.com/java-kafka-bootstrap-server


# API description
curl localhost:9000/kafka/all-messages
docker-compose up


# Debug in Play:
Main-class: play.core.server.ProdServerStart
VM args: -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9999 -Dapplication.path=. -Dplay.http.secret.key=3Xz1ZXmNVeM9eA98NkxR


# Install Docker via Cask
brew uninstall --cask docker --force
brew install --force homebrew/cask/docker


# Kafka client (from Kafka container):
# Create Kafka topic
kafka-topics.sh --create --topic play-scala-kafka-topic --bootstrap-server kafka:9092
kafka-topics.sh --list  --bootstrap-server kafka:9092


Note: Each consumer consumes from only one partition.
Once there are more consumers than partitions, the excess consumers will sit idle and receive no messages.

# Publish message
kafka-console-producer.sh --topic play-scala-kafka-topic --bootstrap-server kafka:9092

kafka-console-consumer.sh --topic play-scala-kafka-topic --from-beginning --bootstrap-server kafka:9092
kafka-consumer-groups.sh --bootstrap-server kafka:9092 --describe --group kafka-group-1

# Reset offsets
kafka-consumer-groups.sh --bootstrap-server kafka:9092  --group kafka-group-1 --topic play-scala-kafka-topic --reset-offsets --to-datetime 2000-01-01T00:00:00.000 --execute



