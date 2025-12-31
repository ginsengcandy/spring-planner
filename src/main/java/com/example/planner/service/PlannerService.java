package com.example.planner.service;

import com.example.planner.dto.CreatePlannerRequest;
import com.example.planner.dto.CreatePlannerResponse;
import com.example.planner.dto.GetPlannerResponse;
import com.example.planner.entity.Planner;
import com.example.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private PlannerRepository plannerRepository;

    @Transactional
    public CreatePlannerResponse createPlanner(CreatePlannerRequest request){
        Planner planner = new Planner(
                request.getTitle(),
                request.getContents(),
                request.getOwner(),
                request.getPassword()
        );
        Planner savedPlanner = plannerRepository.save(planner);
        return new CreatePlannerResponse(
                savedPlanner.getId(),
                savedPlanner.getTitle(),
                savedPlanner.getContents(),
                savedPlanner.getOwner(),
                savedPlanner.getCreatedAt(),
                savedPlanner.getModifiedAt()
        );
    }

    @Transactional(readOnly=true)
    public GetPlannerResponse findOne(Long plannerId){
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetPlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getOwner(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );
    }

    @Transactional(readOnly=true)
    public List<GetPlannerResponse> findAll(String owner){
        List<Planner> planners = plannerRepository.findAll();
        //방법 1 - List로 가져오기
//        List<GetPlannerResponse> dtos = new ArrayList<>();
//        for (Planner planner : planners) {
//            if(planner.getOwner().equals(owner)){
//                dtos.add(
//                        new GetPlannerResponse(
//                                planner.getId(),
//                                planner.getTitle(),
//                                planner.getContents(),
//                                planner.getOwner(),
//                                planner.getCreatedAt(),
//                                planner.getModifiedAt()
//                        )
//                );
//            }
//        }
//        return dtos;
        //방법2 -> Stream()으로 가져오기
        return planners.stream()
                .filter(p -> p.equals(owner))
                .map(
                        planner -> new GetPlannerResponse(
                                planner.getId(),
                                planner.getTitle(),
                                planner.getContents(),
                                planner.getOwner(),
                                planner.getCreatedAt(),
                                planner.getModifiedAt()
                        )
                ).toList();
    }
}
