package hr.smilebacksmile.service;

import hr.smilebacksmile.domain.TestData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("NO_SQL_DB_REST_TEMP_DEMO")
public interface TestDataClient {

    /*
    Feign key features here:
        - declarative -> no need to define RestTemplate
        - Spring will implement interface at run time
    */
    @GetMapping("/")
    TestData[] getData();

}
