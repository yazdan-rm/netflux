package ir.nrdc.springframework.netflux.services;

import ir.nrdc.springframework.netflux.domain.Movie;

import ir.nrdc.springframework.netflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovieService {

    Mono<Movie> findById(String id);

    Flux<Movie> findAll();

    Flux<MovieEvent> streamMovieEvents(String movieId);

    List<MovieEvent> streamMovieEventsBlock(String movieId);
}
