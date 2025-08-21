package com.fitness.activity.controler;


import com.fitness.activity.dto.ActivityRequest;
import com.fitness.activity.dto.ActivityResponse;
import com.fitness.activity.service.ActivityService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/activities")
@RestController
public class ActivityControler {

    private final ActivityService activityService;

    public ActivityControler(ActivityService activityService) {
        this.activityService = activityService;
    }


    @PostMapping("/Tracking")
    public ResponseEntity<ActivityResponse>TrackActivity(@RequestBody ActivityRequest activityRequest){

        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>>getUserActivity(@RequestHeader("X-User_ID") String userId){

        return ResponseEntity.ok(activityService.getUserActivity(userId));
    }

    @GetMapping("/{activityID}")
    public ResponseEntity<ActivityResponse>getActivity(@PathVariable String activityID){

        return ResponseEntity.ok(activityService.getActivity(activityID));
    }

}
