package hello.sunil.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import hello.Greeting;
import hello.sunil.DeviceDataDto;
import hello.sunil.feign.FeignRestClient;

@Service
public class HegTopicConsumer {
	
	@Autowired
	FeignRestClient feignRestClient;

	@Autowired
	DeviceDataProducer deviceDataProducer;

    @StreamListener(DeviceDataDto.HEG_MSG_TOPIC)
    public void consumeMessage(Greeting greet){
    	System.out.println("GOT : --"+greet.getContent());
    	for (int i=0;i<=2;i++){
    		deviceDataProducer.produceMessage(greet.getContent() + " - "+i); 
    		}
    	String respMsg = feignRestClient.version();
    	System.out.println("Response Message "+ respMsg);
    	
    	
    }
}
