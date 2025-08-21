package com.ai.aiservice.service;


import com.ai.aiservice.model.Recommendation;
import com.ai.aiservice.repository.RecommendationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepo recommendationRepo;

    public List<Recommendation> getUserRecommendation(String userId) {

        return recommendationRepo.findByUserId(userId);

    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepo.findByActivityId(activityId)
                .orElseThrow(()-> new RuntimeException("Not Found any Recommendations " + activityId));
    }
}
