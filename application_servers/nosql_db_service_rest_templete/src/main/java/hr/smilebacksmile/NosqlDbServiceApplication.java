package hr.smilebacksmile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NosqlDbServiceApplication {

    public static void main(String[] args)  {
        SpringApplication.run(NosqlDbServiceApplication.class, args);

    }

}

