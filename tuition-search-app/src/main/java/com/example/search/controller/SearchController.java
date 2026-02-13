package com.example.search.controller;

import com.example.search.dto.SearchFilter;
import com.example.search.entity.ClassEntity;
import com.example.search.service.ClassSearchService;
import com.example.search.service.GeminiService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Slf4j
public class SearchController {

    private final GeminiService geminiService;
    private final ClassSearchService searchService;

    public SearchController(GeminiService geminiService,
                            ClassSearchService searchService) {
        this.geminiService = geminiService;
        this.searchService = searchService;
    }

    @GetMapping
    public List<ClassEntity> search(@RequestParam String query) throws Exception {
        SearchFilter filter = geminiService.extractFilters(query);
        log.info(query.toString());
        return searchService.search(filter);
    }
}
