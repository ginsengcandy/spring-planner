package com.example.planner.dto;

import com.example.planner.entity.Comment;
import com.example.planner.entity.Planner;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"id", "owner", "contents", "createdAt", "modifiedAt"})
public class GetCommentResponse {
    private final Long id;
    private final String contents;
    private final String owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.owner = comment.getOwner();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
