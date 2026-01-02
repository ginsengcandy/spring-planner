package com.example.planner.repository;

import com.example.planner.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPlannerId(Long plannerId);
    long countByPlannerId(Long plannerId);
}
