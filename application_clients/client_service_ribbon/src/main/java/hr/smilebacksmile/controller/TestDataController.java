package hr.smilebacksmile.controller;


import hr.smilebacksmile.domain.TestData;
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

    /*
    Ribbon key features here:

            - enables load balancing for the client (client balances requests towards server) - not visible if mulitple serverside instances are not present
            - enables rest template to be intervawed with loadbalancing and dynamic (Eureka - since if Eureka is present on the classpath, it's used as default) discovery

    DiscoveryClient was replaced with LoadBalancerClient
    */
    @Autowired
    private LoadBalancerClient client;

    @GetMapping("/data")
    public @ResponseBody List<TestData> getData() {
        final ServiceInstance serviceInstance = client.choose("no_sql_db_rest_temp_demo"); // notice, always one instance is returned!
        TestData[] data = new TestData[]{};
        URI uri = serviceInstance.getUri();
        uri = uri.resolve(uri.getPath()+"/data");
        if (uri != null ) {
            data = (new RestTemplate()).getForObject(uri, TestData[].class);
        }
        return Arrays.asList(data) ;
    }
}
