package hr.smilebacksmile.controller;


import hr.smilebacksmile.domain.no_sql.dto.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@RestController
// @RibbonClient(name="no-sql-db-rest-temp-demo", configuration = LoadBalanceConfiguration.class)
// Not needed - only if we want specifically configure this Ribbon handled client for accessing another client (namely service)
//      whose name we gave in annotation
public class TestDataController {

    /*
    Ribbon key features here:

            - enables load balancing for the client (client balances requests towards server) - not visible if mulitple serverside instances are not present
            - enables rest template to be intervawed with loadbalancing and dynamic (Eureka - since if Eureka is present on the classpath, it's used as default) discovery

    */

    //	This is referencing the RestTemplate we defined in configuration as load balanced
    @Autowired
    private RestTemplate template;

    @GetMapping(value = "/data")
    public @ResponseBody List<TestData> getData() {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("no-sql-db-rest-temp-demo").path("/data").build();

        URI uri = uriComponents.toUri();
        TestData[] data = new TestData[]{};
        if (uri != null ) {
            data = this.template.getForObject(uri, TestData[].class);
        }
        return Arrays.asList(data) ;
    }
}
