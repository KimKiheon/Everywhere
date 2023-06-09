package com.ssafy.enjoytrip.trip.controller;

import com.ssafy.enjoytrip.trip.model.dto.HotPlaceRequest;
import com.ssafy.enjoytrip.trip.model.dto.SearchRequest;
import com.ssafy.enjoytrip.trip.model.dto.TripPlan;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class TripRestController {
    private final TripService tripService;
    private static final String SUCCESS = "success";

    @GetMapping("/search")
    public ResponseEntity<?> search(SearchRequest request) {
        log.debug(String.valueOf(request.getSidoCode()));
        return new ResponseEntity<>(tripService.getAttractionList(request), HttpStatus.OK);
    }

    @GetMapping("/sido")
    public ResponseEntity<?> sido() {
        return new ResponseEntity<>(tripService.getSidoList(), HttpStatus.OK);
    }

    @GetMapping("/gugun")
    public ResponseEntity<?> gugun(@RequestParam("sido") String sidoCode) {
        return new ResponseEntity<>(tripService.getGugunList(Integer.parseInt(sidoCode)), HttpStatus.OK);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<?> detail(@PathVariable int contentId) {
        return new ResponseEntity<>(tripService.getDetailJSON(contentId), HttpStatus.OK);
    }

    @GetMapping("/hotPlace")
    public ResponseEntity<?> hotPlace() {
        return new ResponseEntity<>(tripService.getHotPlaces(), HttpStatus.OK);
    }

    @PostMapping("/hotRegist")
    public ResponseEntity<?> hotRegist(@RequestBody HotPlaceRequest request) {
        tripService.hotRegist(request);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/checkRecommend")
    public ResponseEntity<?> checkRecommend(@RequestBody HotPlaceRequest request) {
        return new ResponseEntity<>(tripService.checkRecommend(request), HttpStatus.OK);
    }

    @GetMapping("/recommend/{sidoCode}/{gugunCode}")
    public ResponseEntity<?> recommend(@PathVariable int sidoCode, @PathVariable int gugunCode) {
        return new ResponseEntity<>(tripService.recommendAttractionList(sidoCode, gugunCode), HttpStatus.OK);
    }

    @GetMapping("/getAddress/{sidoCode}/{gugunCode}")
    public ResponseEntity<?> getAddress(@PathVariable int sidoCode, @PathVariable int gugunCode) {
        return new ResponseEntity<>(tripService.getAddress(sidoCode, gugunCode), HttpStatus.OK);
    }
    //planId번의 여행계획 조회
    @GetMapping("/plan/{planId}")
    public ResponseEntity<?> getPlan(@PathVariable int planId){
        return new ResponseEntity<>(tripService.getPlan(planId),HttpStatus.OK);
    }

    //모든 여행 계획 조회
    @GetMapping("/plan/all/{userId}")
    public ResponseEntity<?> getPlanAll(@PathVariable String userId){
        return new ResponseEntity<>(tripService.getPlanAll(userId),HttpStatus.OK);
    }

    //plan id 입력하면 해당 계획의 여행지 리스트 조회
    @GetMapping("/plan/get/{planId}")
    public ResponseEntity<?> getAttractionListByPlanId(@PathVariable int planId){
        return new ResponseEntity<>(tripService.getPlanAttractionList(planId),HttpStatus.OK);
    }
    @PostMapping("/plan")
    public ResponseEntity<?> addPlan(@RequestBody TripPlan request){
        tripService.addPlan(request);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId){
        tripService.deletePlan(planId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
