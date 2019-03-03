package hr.smilebacksmile.controller;


import hr.smilebacksmile.domain.no_sql.dto.TestData;
import hr.smilebacksmile.service.TestDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
