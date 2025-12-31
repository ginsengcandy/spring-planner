package com.example.planner.controller;

import com.example.planner.dto.CreatePlannerRequest;
import com.example.planner.dto.CreatePlannerResponse;
import com.example.planner.dto.GetPlannerResponse;
import com.example.planner.dto.UpdatePlannerResponse;
import com.example.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlannerController {
    private PlannerService plannerService;

    @PostMapping("/planners")
    public ResponseEntity<CreatePlannerResponse> createPlanner(
            @RequestBody CreatePlannerRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(plannerService.createPlanner(request));
    }

    @GetMapping("/planners/{plannerId}")
    public ResponseEntity<GetPlannerResponse> getPlanner(
            @PathVariable Long plannerId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.findOne(plannerId));
    }

    @GetMapping("/planners/{plannerOwner}")
    public ResponseEntity<List<GetPlannerResponse>> getPlanners(
            @PathVariable String plannerOwner
    ){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.findAll(plannerOwner));
    }

    @PutMapping("/planners/{plannerId}")
    public ResponseEntity<UpdatePlannerResponse> updatePlanner(
            @PathVariable Long plannerId,
            @RequestBody UpdatePlannerRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.updatePlanner(plannerId, request))
    }


}
