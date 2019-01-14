package hr.smilebacksmile.dao;


import hr.smilebacksmile.nosql_db_client_demo.test_db.domain.TestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public interface TestDataRepository extends MongoRepository<TestData, Long>, JpaSpecificationExecutor<TestData> {

    // custom default implementaiton
    default Optional<TestData> findByCode(String code) {
       return StreamSupport.stream(findAll().spliterator(), true).filter(e -> StringUtils.equals(e.getCode(), code)).findFirst();
    }

    // will be implemented by spring
    List<TestData> findByName(String name);
}
