package com.example.planner.dto;

import com.example.planner.entity.Comment;
import com.example.planner.entity.Planner;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonPropertyOrder({"id", "title", "owner", "contents", "createdAt", "modifiedAt", "comments"})
public class GetPlannerResponse {
    private final Long id;
    private final String title;
    private final String contents;
    private final String owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;

    public GetPlannerResponse(Planner planner) {
        this.id = planner.getId();
        this.title = planner.getTitle();
        this.contents = planner.getContents();
        this.owner = planner.getOwner();
        this.createdAt = planner.getCreatedAt();
        this.modifiedAt = planner.getModifiedAt();
        this.comments = planner.getComments().stream().map(c -> new GetCommentResponse(c)).toList();
    }
}
