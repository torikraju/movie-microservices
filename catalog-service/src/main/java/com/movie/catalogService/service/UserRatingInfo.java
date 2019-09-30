package com.movie.catalogService.service;

import com.movie.catalogService.domain.Rating;
import com.movie.catalogService.domain.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    private RestTemplate restTemplate;

    public UserRatingInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(@PathVariable String userId) {
        return restTemplate.getForObject(
                "http://movie-rating-service/api/rating/user/" + userId,
                UserRating.class
        );
    }

    private UserRating getFallbackUserRating(@PathVariable String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));
        return userRating;
    }
}
