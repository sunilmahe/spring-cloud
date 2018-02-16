package hello.sunil.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import hello.sunil.DeviceDataDto;

@Service
public class DeviceDataProducer {
	private DeviceDataDto hegExtactor;

    @Autowired
    public DeviceDataProducer(DeviceDataDto hegExtactor) {
        this.hegExtactor = hegExtactor;
    }

    public void produceMessage(String name) {
    	hegExtactor.deviceDataOutput().send(MessageBuilder.withPayload(name).build());
    }

}
