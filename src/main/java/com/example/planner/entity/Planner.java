package com.example.planner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="planners")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Planner extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=30, nullable=false)
    private String title;
    @Column(length=200, nullable=false)
    private String contents;
    @Column(length=10, nullable=false)
    private String owner;
    @Column(length=20, nullable=false)
    private String password;
    @OneToMany(mappedBy = "planner", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Planner(String title, String contents, String owner, String password){
        this.title = title;
        this.contents = contents;
        this.owner = owner;
        this.password = password;
    }

    public void updatePlanner(String title, String owner) {
        this.title=title;
        this.owner=owner;
    }
}
