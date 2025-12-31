package com.example.planner.dto;

import lombok.Getter;

@Getter
public class CreatePlannerRequest {
    private String title;
    private String contents;
    private String owner;
    private String password;
}
