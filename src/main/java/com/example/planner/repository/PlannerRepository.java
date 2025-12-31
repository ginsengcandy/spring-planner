package com.example.planner.repository;

import com.example.planner.entity.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}
