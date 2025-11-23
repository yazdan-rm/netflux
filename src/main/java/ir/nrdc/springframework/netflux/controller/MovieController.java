package ir.nrdc.springframework.netflux.controller;

import ir.nrdc.springframework.netflux.domain.Movie;
import ir.nrdc.springframework.netflux.domain.MovieEvent;
import ir.nrdc.springframework.netflux.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/{id}")
    Mono<Movie> findById(@PathVariable String id) {
        return movieService.findById(id);
    }

    @GetMapping
    Flux<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvent(@PathVariable String id) {
        return movieService.streamMovieEvents(id);
    }


    @GetMapping(value = "/{id}/block")
    List<MovieEvent> streamMovieEventBlock(@PathVariable String id) {
        return movieService.streamMovieEventsBlock(id);
    }

}
