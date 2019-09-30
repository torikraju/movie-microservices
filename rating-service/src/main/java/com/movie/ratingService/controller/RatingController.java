package com.movie.ratingService.controller;

import com.movie.ratingService.domain.Rating;
import com.movie.ratingService.domain.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @GetMapping("/{id}")
    public Rating getRatingById(@PathVariable("id") String id) {
        Random rand = new Random();
        return new Rating(id, rand.nextInt((10 - 1) + 1) + 1);
    }

    @GetMapping("/user/{id}")
    public UserRating getRatingByUserId(@PathVariable("id") String id) {
        Random rand = new Random();
        List<Rating> ratings = Arrays.asList(
                new Rating("100", rand.nextInt((10 - 1) + 1) + 1),
                new Rating("101", rand.nextInt((10 - 1) + 1) + 1)
        );
        return new UserRating(ratings);
    }
}
