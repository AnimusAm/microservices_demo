package hr.smilebacksmile.dao;

import hr.smilebacksmile.domain.TestData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TestDataSpecification implements Specification<TestData> {

    private SearchCriteria criteria;

    public TestDataSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<TestData> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase("NULL")) {
            return builder.isNull(root.get(criteria.getKey()));
        } else if (criteria.getOperation().equalsIgnoreCase("NOT NULL")) {
            return builder.isNotNull(root.get(criteria.getKey()));
        }

        return null;
    }

}
