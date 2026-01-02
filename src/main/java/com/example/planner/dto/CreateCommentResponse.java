package com.example.planner.dto;

import com.example.planner.entity.Comment;
import com.example.planner.entity.Planner;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String contents;
    private final String owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long plannerId;

    public CreateCommentResponse(Long id, String contents, String owner, LocalDateTime createdAt, LocalDateTime modifiedAt, Long plannerId) {
        this.id = id;
        this.contents = contents;
        this.owner = owner;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.plannerId = plannerId;
    }
    //편의 생성자
    public CreateCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.owner = comment.getOwner();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.plannerId = comment.getPlanner().getId();
    }
}
