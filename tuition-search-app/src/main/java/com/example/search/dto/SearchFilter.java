package com.example.search.dto;

import lombok.Data;

@Data
public class SearchFilter {
    private String subject;
    private String level;
    private String district;
    private String day;
	@Override
	public String toString() {
		return "SearchFilter [subject=" + subject + ", level=" + level + ", district=" + district + ", day=" + day
				+ "]";
	}
    
    
}
