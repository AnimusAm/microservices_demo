package hr.smilebacksmile.db_client_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbClientDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbClientDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DbClientDemoApplication.class, args);
    }


}

