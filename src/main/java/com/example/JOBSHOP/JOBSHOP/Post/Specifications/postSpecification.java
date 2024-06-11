package com.example.JOBSHOP.JOBSHOP.Post.Specifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.JOBSHOP.JOBSHOP.Post.Post;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class postSpecification implements Specification<Post> {

    private postSearch postSearch;
    private List<Long> postIds;

    public postSpecification(postSearch postSearch, List<Long> postIds) {
        this.postSearch = postSearch;
        this.postIds = postIds;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicatList = new ArrayList<>();

        // Filter by Title
        if (postSearch.getTitle() != null && !postSearch.getTitle().isEmpty()) {
            predicatList.add(cb.like(root.get("Title"), "%" + postSearch.getTitle() + "%"));
        }

        // Filter by Location
        if (postSearch.getLocation() != null && !postSearch.getLocation().isEmpty()) {
            predicatList.add(cb.like(root.get("location"), "%" + postSearch.getLocation() + "%"));
        }

        // Filter by EmploymentType
        if (postSearch.getEmploymentType() != null && !postSearch.getEmploymentType().isEmpty()) {
            predicatList.add(cb.like(root.get("employmentType"), "%" + postSearch.getEmploymentType() + "%"));
        }

        // Filter by CreatedDate
        if (postSearch.getCreatedDate() != null && !postSearch.getCreatedDate().isEmpty()) {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            LocalDateTime createdDate = LocalDateTime.parse(postSearch.getCreatedDate(), formatter);
            Integer year = createdDate.getYear();
            Integer month = createdDate.getMonthValue();

            if (year != null) {
                predicatList.add(cb.equal(cb.function("YEAR", Integer.class, root.get("createdDate")), year));
            }

            if (month != null) {
                predicatList.add(cb.equal(cb.function("MONTH", Integer.class, root.get("createdDate")), month));
            }
        }

        // Filter by Post IDs
        if (postIds != null && !postIds.isEmpty()) {
            predicatList.add(root.get("id").in(postIds));
        }

        return cb.and(predicatList.toArray(new Predicate[0]));
    }
}
