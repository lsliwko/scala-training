# Task description
https://www.baeldung.com/java-kafka-bootstrap-server


# API description
curl localhost:9000/kafka/all-messages

docker-compose up

# Install Docker via Cask
brew uninstall --cask docker --force
brew install --force homebrew/cask/docker


# Kafka client (from Kafka container):
# Create Kafka topic
kafka-topics.sh --create --topic play-scala-kafka-topic --bootstrap-server kafka:9092
kafka-topics.sh --list  --bootstrap-server kafka:9092

# Publish message
kafka-console-producer.sh --topic play-scala-kafka-topic --bootstrap-server kafka:9092
kafka-console-consumer.sh --topic play-scala-kafka-topic --from-beginning --bootstrap-server kafka:9092


