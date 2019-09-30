package com.movie.informationService.controller;

import com.movie.informationService.domain.Movie;
import com.movie.informationService.domain.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/movies")
@PropertySource(name = "local-properties", value = {"classpath:application-local.properties"})
public class MovieController {

    @Value("${themoviedb.api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    public MovieController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Movie getMovieInformation(@PathVariable("id") String id) {
        MovieSummary movieSummary = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey,
                MovieSummary.class
        );
        return new Movie(id, movieSummary.getTitle(), movieSummary.getOverview());

    }
}
