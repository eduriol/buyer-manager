package buyer;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        launchKafkaConsumers();
    }

    private void launchKafkaConsumers() {
        Thread kafkaConsumerThread = new Thread(() -> {
            try {
                LOGGER.info("Starting Kafka consumer thread.");
                SimpleKafkaConsumer simpleKafkaConsumer = new SimpleKafkaConsumer("add-purchase-topic");
                simpleKafkaConsumer.runSingleWorker(BuyerController.buyerRepository);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        });
        kafkaConsumerThread.start();
    }

}
