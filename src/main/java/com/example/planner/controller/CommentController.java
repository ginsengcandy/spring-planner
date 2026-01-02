package com.example.planner.controller;

import com.example.planner.dto.CreateCommentRequest;
import com.example.planner.dto.CreateCommentResponse;
import com.example.planner.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planners")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentservice;
    //CREATE-POST
    @PostMapping("/{plannerId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long plannerId,
            @RequestBody CreateCommentRequest request
    ) {
        CreateCommentResponse response = commentservice.createComment(plannerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
