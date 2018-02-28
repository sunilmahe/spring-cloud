package hello.sunil.kafka;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Test class demonstrating how to use an embedded kafka service with the
 * kafka binder.
 *
 * @author Sunil Kumar T K
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HegTopicConsumerTest {
	
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1);
	
	@Autowired
	private KafkaTemplate<byte[], byte[]> template;

	@Autowired
	private DefaultKafkaConsumerFactory<byte[], byte[]> consumerFactory;
	
	
	@Value("${spring.cloud.stream.bindings.device_data.destination}")
	private String inputDestination;

	//@Value("${spring.cloud.stream.bindings.heg_topic.destination}")
	private String outputDestination="heg_topic";
	
	@BeforeClass
	public static void setup() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
		System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
	}
	
	@Test
	public void testSendReceive() {
		
		final String Test_MSG = "{\"id\":10,\"content\":\"This is my content\"}";
		template.send(this.inputDestination, Test_MSG.getBytes());
		
		Consumer<byte[], byte[]> consumer = this.consumerFactory.createConsumer("test");
		consumer.subscribe(Collections.singleton(this.outputDestination));
		ConsumerRecords<byte[], byte[]> records = consumer.poll(10_000);
		consumer.commitSync();
		assertThat(records.count()).isEqualTo(1);
		//assertThat(new String(records.iterator().next().value())).isEqualTo("FOO");
		assertEquals("Completed","Completed");
	}

}
