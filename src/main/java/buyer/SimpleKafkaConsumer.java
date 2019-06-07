package buyer;

import buyer.entities.Buyer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class SimpleKafkaConsumer {

    private KafkaConsumer<String, String> kafkaConsumer;

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public SimpleKafkaConsumer(String topicName) throws IOException {
        InputStream input = new FileInputStream("src/main/resources/kafka.properties");
        Properties consumerProperties = new Properties();
        consumerProperties.load(input);
        String environment = "";
        if (System.getProperty("environment") != null) {
            environment = System.getProperty("environment");
        }
        if (environment.equals("compose")) {
            consumerProperties.setProperty("bootstrap.servers", "kafka1:19092");
        }
        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(topicName));
    }

    public void runSingleWorker(List<Buyer> repository) {

        while(true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                String message = record.value();
                LOGGER.info("Received message: " + message);
                Gson gson = new Gson();
                Buyer newBuyer = gson.fromJson(message, Buyer.class);
                // Once we finish processing a Kafka message, we have to commit the offset.
                // But first we simulate a delay in Kafka processing of 20 seconds
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                commitOffset(record);

                for (Buyer buyer : repository) {
                    if (buyer.getId().equals(newBuyer.getId())) {
                        repository.remove(buyer);
                        repository.add(newBuyer);
                        break;
                    }
                }
            }
        }
    }

    private void commitOffset(ConsumerRecord<String, String> record) {
        Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();
        commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                new OffsetAndMetadata(record.offset() + 1));
        kafkaConsumer.commitSync(commitMessage);
        LOGGER.info("Offset committed to Kafka.");
    }

}
