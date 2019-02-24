package hr.smilebacksmile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
// FIXME: this annotation does not work - Maven dependency can not be found
//      - dependency should be available through ribbon dependency. Maybe it has something to do with configuration classes => look into it
// @EnableFeignClients
public class ClientServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceRibbonApplication.class, args);
    }

    //  "LoadBalanced" RestTemplate that is used by Ribbon to hook towards available server side services
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

