package hr.smilebacksmile.dao;


import hr.smilebacksmile.domain.TestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public interface TestDataRepository extends MongoRepository<TestData, String> {

    // custom default implementaiton
    default TestData findByCode(String code) {
        return StreamSupport.stream(findAll().spliterator(), true).filter(e -> StringUtils.equals(e.getCode(), code)).findFirst().orElse(null);
    }

    // will be implemented by spring
    List<TestData> findByName(String name);
}
