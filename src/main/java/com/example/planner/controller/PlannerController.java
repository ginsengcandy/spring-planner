package com.example.planner.controller;

import com.example.planner.dto.*;
import com.example.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerService plannerService;
    //CREATE-POST
    @PostMapping("/planners")
    public ResponseEntity<CreatePlannerResponse> createPlanner(
            @RequestBody CreatePlannerRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(plannerService.createPlanner(request));
    }
    //READ-GET (단건조회)
    @GetMapping("/planners/{plannerId}")
    public ResponseEntity<GetPlannerResponse> getPlanner(
            @PathVariable Long plannerId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.findOne(plannerId));
    }
    //READ-GET (전체조회)
    @GetMapping("/planners")
    public ResponseEntity<List<GetPlannerResponse>> getPlanner(){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.findAll());
    }
    //UPDATE-PUT
    @PutMapping("/planners/{plannerId}")
    public ResponseEntity<UpdatePlannerResponse> updatePlanner(
            @PathVariable Long plannerId,
            @RequestBody UpdatePlannerRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.updatePlanner(plannerId, request));
    }
    //DELETE-DELETE
    @DeleteMapping("/planners/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId) {
        plannerService.deletePlanner(plannerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
