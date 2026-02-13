package com.example.search.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String level;
    private String district;
    private String day;
    private String teacherName;
}
