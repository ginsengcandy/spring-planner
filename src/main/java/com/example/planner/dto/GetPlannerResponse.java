package com.example.planner.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPlannerResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetPlannerResponse(Long id, String title, String contents, String owner, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.owner = owner;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
