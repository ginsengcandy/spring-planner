package com.example.planner.dto;

import com.example.planner.entity.Planner;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String contents;
    private String owner;
    private String password;
}
