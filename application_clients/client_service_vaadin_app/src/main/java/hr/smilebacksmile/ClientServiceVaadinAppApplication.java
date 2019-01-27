package hr.smilebacksmile;

import hr.smilebacksmile.domain.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientServiceVaadinAppApplication {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceVaadinAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceVaadinAppApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();
        TestData[] data = restTemplate.getForObject("http://localhost:8005/data", TestData[].class);
        for (TestData d : data) {
            log.info(d.toString());
        }
    }

}

