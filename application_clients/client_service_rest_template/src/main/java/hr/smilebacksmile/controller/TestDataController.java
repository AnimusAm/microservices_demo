package hr.smilebacksmile.controller;


import hr.smilebacksmile.domain.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@RestController
public class TestDataController {

    final private DiscoveryClient client;

    @Autowired
    public TestDataController(DiscoveryClient client) {
        this.client = client;
    }

    @GetMapping("/data")
    public @ResponseBody List<TestData> getData() {
        final List<ServiceInstance> list = client.getInstances("no_sql_db_rest_temp_demo");
        TestData[] data = new TestData[]{};
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            uri = uri.resolve(uri.getPath()+"/data");
            if (uri != null ) {
                data = (new RestTemplate()).getForObject(uri, TestData[].class);
            }
        }
        return Arrays.asList(data) ;
    }
}
