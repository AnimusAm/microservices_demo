package hr.smilebacksmile.controller;

import hr.smilebacksmile.dao.TestDataRepository;
import hr.smilebacksmile.domain.TestData;
import javafx.util.converter.BigIntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TestDataController {


    final private TestDataRepository dataRepository;

    final private BigIntegerStringConverter idConverter;

    @Autowired
    TestDataController(final TestDataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.idConverter = new BigIntegerStringConverter();
    }

    @GetMapping("/data")
    public List<TestData> index(){
        return dataRepository.findAll();
    }

    @GetMapping("/data/{code}")
    public TestData show(@PathVariable String code){
        return dataRepository.findByCode(code);
    }

    @PostMapping("/data/search")
    public List<TestData> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("name_text");
        return dataRepository.findByNameContaining(searchTerm);
    }

    @PostMapping("/data/search_like")
    public List<TestData> searchLike(@RequestBody Map<String, String> body){
        String searchTerm = body.get("name_text");
        return dataRepository.findByNameLike(searchTerm);
    }

    @PostMapping("/data")
    public TestData create(@RequestBody Map<String, String> body){
        String code = body.get("code");
        String name = body.get("name");
        return dataRepository.save(new TestData(code, name));
    }


    @PutMapping("/data/{id}")
    public TestData update(@PathVariable String id, @RequestBody Map<String, String> body){
        BigInteger dataId = idConverter.fromString(id);
        Optional<TestData> dataOptional = this.dataRepository.findById(dataId);
        if (dataOptional.isPresent()) {
            TestData data = dataOptional.get();
            data.setCode(body.get("code"));
            data.setName(body.get("name"));
            return dataRepository.save(data);
        } else {
            return null;
        }
    }

    @DeleteMapping("data/{id}")
    public boolean delete(@PathVariable String id){
        BigInteger dataId = idConverter.fromString(id);
        dataRepository.deleteById(dataId);
        return true;
    }
}
