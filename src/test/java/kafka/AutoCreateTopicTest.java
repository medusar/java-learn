package kafka;

import java.util.UUID;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.ClassRule;
import org.junit.Test;

import com.salesforce.kafka.test.junit4.SharedKafkaTestResource;

public class AutoCreateTopicTest {

    @ClassRule
    public static final SharedKafkaTestResource kafka = new SharedKafkaTestResource();

    @Test
    public void testFetchingPartitionInfos() {
        try (KafkaConsumer<String, String> consumer = kafka.getKafkaTestUtils()
                .getKafkaConsumer(StringDeserializer.class, StringDeserializer.class)) {
            String topic = "topic_foo_" + UUID.randomUUID();

            printOutAllTopics();
            System.out.println("Partitions for topic by listTopics():");
            System.out.println(consumer.listTopics().get(topic));

            printOutAllTopics();
            System.out.println("Partitions for topic by partitonsFor():");
            System.out.println(consumer.partitionsFor(topic));

            printOutAllTopics();
            System.out.println("Partitions for topic by listTopics():");
            System.out.println(consumer.listTopics().get(topic));

            printOutAllTopics();
        }
    }

    private void printOutAllTopics() {
        System.out.println();
        System.out.println("All Topics:");
        System.out.println(kafka.getKafkaTestUtils().getTopicNames());
        System.out.println();
    }
}
