package hr.smilebacksmile;

import hr.smilebacksmile.dao.TestDataRepository;
import hr.smilebacksmile.domain.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NosqlDbServiceApplication {


    public static void main(String[] args)  {
        SpringApplication.run(NosqlDbServiceApplication.class, args);

    }

}

