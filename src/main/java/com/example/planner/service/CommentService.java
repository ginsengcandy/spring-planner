package com.example.planner.service;

import com.example.planner.dto.CreateCommentRequest;
import com.example.planner.dto.CreateCommentResponse;
import com.example.planner.entity.Comment;
import com.example.planner.entity.Planner;
import com.example.planner.repository.CommentRepository;
import com.example.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PlannerRepository plannerRepository;
    //CREATE
    @Transactional
    public CreateCommentResponse createComment(Long plannerId, CreateCommentRequest request) {
        //예외처리-존재하지 않는 게시글
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 게시글입니다.")
        );
        //예외처리-11번째 댓글 작성 시도
        long commentCount = commentRepository.countByPlannerId(plannerId);
        if(commentCount >= 10) throw new IllegalStateException("댓글은 10개까지만 작성할 수 있습니다.");
        //비즈니스 로직 : 댓글 등록
        Comment comment = new Comment(
                request.getContents(),
                request.getOwner(),
                request.getPassword(),
                planner
        );
        return new CreateCommentResponse(commentRepository.save(comment));
    }
}
