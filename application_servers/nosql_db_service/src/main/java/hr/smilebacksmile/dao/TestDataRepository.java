package hr.smilebacksmile.dao;


import hr.smilebacksmile.domain.TestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface TestDataRepository extends MongoRepository<TestData, BigInteger> {

    // custom default implementaiton
    default TestData findByCode(String code) {
        return StreamSupport.stream(findAll().spliterator(), true).filter(e -> StringUtils.equals(e.getCode(), code)).findFirst().orElse(null);
    }

    // will be implemented by spring
    List<TestData> findByName(String name);

    // custom default implementaiton
    default List<TestData> findByNameContaining(String text) {
        return StreamSupport.stream(findAll().spliterator(), true).filter(e -> StringUtils.equalsIgnoreCase(e.getName(), text)).collect(Collectors.toCollection(ArrayList::new));
    }

    // will be implemented by spring
    // notice that those two methods do not do the same thing in different way:
    //  - findByNameContaining - searches exact match but only in case insensitive way
    //  - findByNameLike - searches part of the pattern but in case sensitive way
    List<TestData> findByNameLike(String text);

}
