package com.fitness.activity.service;


import com.fitness.activity.dto.ActivityRequest;
import com.fitness.activity.dto.ActivityResponse;
import com.fitness.activity.model.Activity;
import com.fitness.activity.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository activityRepository;

   private final UserValidateService userValidateService;

   private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
   private String exchange;

    @Value("${rabbitmq.routing.key}")
     private String routingKey;



    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        boolean isValidate=userValidateService.validateUser(activityRequest.getUserId());
        if(isValidate){
            throw  new RuntimeException("Invalidate user"+activityRequest.getUserId());
        }

        Activity activity=Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .duration(activityRequest.getDuration())
                .additionalMatrices(activityRequest.getAdditionalMatrices())
                .startTime(activityRequest.getStartTime())
                .build();
        Activity savedActivity = activityRepository.save(activity);

        try{

            rabbitTemplate.convertAndSend(exchange,routingKey,savedActivity);
        } catch (Exception e) {
            log.error("Fail to publish the RabbitMq",e);
        }

  return mapToResponse(savedActivity);
    }

    public ActivityResponse mapToResponse(Activity activity){

           ActivityResponse response=new ActivityResponse();

           response.setId(activity.getId());
           response.setType(activity.getType());
           response.setDuration(activity.getDuration());
           response.setUpdatedAt(activity.getUpdatedAt());
           response.setAdditionalMatrices(activity.getAdditionalMatrices());
           response.setStartTime(activity.getStartTime());
           response.setCaloriesBurned(activity.getCaloriesBurned());
           response.setCreateAt(activity.getCreateAt());
           response.setUserId(activity.getUserId());


return response;

    }

    public List<ActivityResponse> getUserActivity(String userId) {

        List<Activity>activites=activityRepository.findByUserId(userId);

        return activites.stream().
                map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivity(String activityId) {
        return activityRepository.findById(activityId).
                map(this::mapToResponse)
                .orElseThrow(()->new RuntimeException("This ID is Not Exits"+ activityId));

    }
}
