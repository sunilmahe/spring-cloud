package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.sunil.kafka.DeviceDataProducer;

@RestController
public class GreetingController {
	
	@Autowired
	DeviceDataProducer deviceDateProducer;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("Controller");
    	deviceDateProducer.produceMessage(name);
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/feigntest")
    public String testingGetMethod(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("The Client called this API");
        return "The Feign Client called this API.";
    }
}
