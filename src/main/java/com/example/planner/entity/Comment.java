package com.example.planner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=100, nullable=false)
    private String contents;
    @Column(nullable=false)
    private String owner;
    @Column(nullable=false)
    private String password;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="planner_id", nullable=false)
    private Planner planner;

    public Comment(String contents, String owner, String password, Planner planner) {
        this.contents = contents;
        this.owner = owner;
        this.password = password;
        this.planner = planner;
    }

    public void updateComment(String contents, String password) {
        this.contents = contents;
        this.password = password;
    }
}
