package hr.smilebacksmile.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
// NB: configuration class is not in the same package with main class
//  (which is annotated with @SpringBootApplication which makes it source of beans definitions)
// so this class does not qualify as configuration class without it's own annotation
public class LoadBalanceConfiguration {

    //  "LoadBalanced" RestTemplate that is used by Ribbon to hook towards available server side services
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
