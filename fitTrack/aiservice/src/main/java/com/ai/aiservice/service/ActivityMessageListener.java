package com.ai.aiservice.service;

import com.ai.aiservice.model.Activity;
import com.ai.aiservice.model.Recommendation;
import com.ai.aiservice.repository.RecommendationRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepo recommendationRepo;

     @RabbitListener(queues="activity.queue")
    public void processActivity(Activity activity){

        log.info("Received activity process: {}",activity.getId());
        //log.info("Recommendation of Activity: {}",activityAiService.generateRecommendation(activity));
         Recommendation recommendation = activityAiService.generateRecommendation(activity);
         recommendationRepo.save(recommendation);
    }

}
