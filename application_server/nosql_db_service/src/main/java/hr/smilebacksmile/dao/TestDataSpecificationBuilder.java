package hr.smilebacksmile.dao;

import hr.smilebacksmile.db_client_demo.test_db.domain.TestData;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDataSpecificationBuilder {

    private final List<SearchCriteria> params;

    public TestDataSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public TestDataSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<TestData> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<TestData>> specs = params.stream().map(TestDataSpecification::new).collect(Collectors.toList());

        Specification<TestData> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
