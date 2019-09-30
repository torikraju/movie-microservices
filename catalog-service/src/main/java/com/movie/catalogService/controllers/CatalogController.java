package com.movie.catalogService.controllers;

import com.movie.catalogService.domain.CatalogItem;
import com.movie.catalogService.domain.Movie;
import com.movie.catalogService.domain.Rating;
import com.movie.catalogService.domain.UserRating;
import com.movie.catalogService.service.MovieInfo;
import com.movie.catalogService.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private MovieInfo movieInfo;
    private UserRatingInfo userRatingInfo;

    public CatalogController(MovieInfo movieInfo, UserRatingInfo userRatingInfo) {
        this.movieInfo = movieInfo;
        this.userRatingInfo = userRatingInfo;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

}


