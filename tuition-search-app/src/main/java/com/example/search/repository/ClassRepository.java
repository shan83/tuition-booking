package com.example.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.search.entity.ClassEntity;

public interface ClassRepository
        extends JpaRepository<ClassEntity, Long>,
                JpaSpecificationExecutor<ClassEntity> {
}
