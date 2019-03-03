package hr.smilebacksmile.service;

import hr.smilebacksmile.domain.no_sql.dto.TestData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// list of IDs of names of service applications (or as you can also say it, other clients registered with Eureka)
@FeignClient("no-sql-db-rest-temp-demo")
public interface TestDataClient {

    /*
    Feign key features here:
        - declarative -> no need to define RestTemplate
        - Spring will implement interface at run time (name of the method does not matter, but input and output types do)
            - annotation can be standard Spring MVC annotation, or JAX/RS
        - annotation is kind-off reversed: here it doesn't state (as usually we would expect with server side applications) what kind of requests it will receive,
            but, what kind of requests it is going to emmit
        - Ribbon is automatically enabled (loadbalancing is used implicitly)
    */
    @RequestMapping(method=RequestMethod.GET, value = "/data")
    List<TestData> getData();

}
