package hello.sunil.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(url="http://localhost:8010", name="feigntest")
public  interface FeignRestClient {
	@RequestMapping(value = "/feigntest", method = RequestMethod.GET)
    String version();
}
