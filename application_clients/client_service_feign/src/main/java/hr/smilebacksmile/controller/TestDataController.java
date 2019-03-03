package hr.smilebacksmile.controller;


import hr.smilebacksmile.domain.TestData;
import hr.smilebacksmile.service.TestDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@RestController
public class TestDataController {

    @Autowired
    private TestDataClient client;

    @GetMapping("/data")
    public @ResponseBody List<TestData> getData() {
        return client.getData();
    }
}
