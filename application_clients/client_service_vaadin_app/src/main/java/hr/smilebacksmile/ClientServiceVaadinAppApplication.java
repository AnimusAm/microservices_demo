package hr.smilebacksmile;

import com.netflix.discovery.DiscoveryClient;
import hr.smilebacksmile.domain.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceVaadinAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceVaadinAppApplication.class, args);
    }

}

