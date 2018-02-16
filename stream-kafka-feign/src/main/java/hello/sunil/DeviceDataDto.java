package hello.sunil;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DeviceDataDto {

	final String DEVICE_DATA_TOPIC = "device_data";
	final String HEG_MSG_TOPIC = "heg_topic";

	@Output(DEVICE_DATA_TOPIC)
	MessageChannel deviceDataOutput();

	@Input(HEG_MSG_TOPIC)
	SubscribableChannel hegInput();
}