package com.example.search.service;

import com.example.search.dto.SearchFilter;
import com.example.search.entity.ClassEntity;
import com.example.search.repository.ClassRepository;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassSearchService {

    private final ClassRepository repository;

    public ClassSearchService(ClassRepository repository) {
        this.repository = repository;
    }

    public List<ClassEntity> search(SearchFilter filter) {

        Specification<ClassEntity> spec = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSubject() != null && !filter.getSubject().isBlank()) {
                predicates.add(cb.equal(root.get("subject"), filter.getSubject()));
            }

            if (filter.getLevel() != null && !filter.getLevel().isBlank()) {
                predicates.add(cb.equal(root.get("level"), filter.getLevel()));
            }

            if (filter.getDistrict() != null && !filter.getDistrict().isBlank()) {
                predicates.add(cb.equal(root.get("district"), filter.getDistrict()));
            }

            if (filter.getDay() != null && !filter.getDay().isBlank()) {
                predicates.add(cb.equal(root.get("day"), filter.getDay()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec);
    }
}
