package com.movie.catalogService.service;

import com.movie.catalogService.domain.CatalogItem;
import com.movie.catalogService.domain.Movie;
import com.movie.catalogService.domain.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    private RestTemplate restTemplate;

    public MovieInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-information-service/api/movies/" + rating.getId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", 0);
    }

}

/*                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/api/movies/" + rating.getId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();*/
