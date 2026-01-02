package com.example.planner.service;

import com.example.planner.dto.*;
import com.example.planner.entity.Planner;
import com.example.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private final PlannerRepository plannerRepository;
    //CREATE
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
    //READ
    @Transactional(readOnly=true)
    public GetPlannerResponse findOne(Long plannerId){
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetPlannerResponse(planner);
    }
    //READ 전체 조회
    @Transactional(readOnly=true)
    public List<GetPlannerResponse> findAll(){
        List<Planner> planners = plannerRepository.findAll();
        //방법 1 - List로 가져오기
        List<GetPlannerResponse> dtos = new ArrayList<>();
        for (Planner planner : planners) {
                dtos.add(new GetPlannerResponse(planner));
        }
        return dtos;
    }
    @Transactional
    public List<GetPlannerResponse> findByOwner(String owner){
        List<Planner> planners = plannerRepository.findAll();
        //READ 작성자명으로 조회
        //방법2 -> Stream()으로 가져오기
        return planners.stream()
                .filter(p -> owner.equals(p.getOwner())) //getOwner() 결과가 owner와 같은 planer만 필터링
                .map(GetPlannerResponse::new).toList();
    }
    //UPDATE
    @Transactional
    public UpdatePlannerResponse updatePlanner(Long plannerId, UpdatePlannerRequest request) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        //비밀번호 검사
        boolean correctPassword = request.getPassword().equals(planner.getPassword());
        if(!correctPassword) throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        planner.updatePlanner(request.getTitle(), request.getOwner());
        return new UpdatePlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getOwner(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );
    }
    //DELETE
    @Transactional
    public void deletePlanner(Long plannerId, DeletePlannerRequest request) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        //비밀번호 검사
        boolean correctPassword = request.getPassword().equals(planner.getPassword());
        if(!correctPassword) throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        plannerRepository.deleteById(plannerId);
    }

}
