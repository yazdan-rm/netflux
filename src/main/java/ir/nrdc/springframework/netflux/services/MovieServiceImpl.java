package ir.nrdc.springframework.netflux.services;

import ir.nrdc.springframework.netflux.domain.Movie;
import ir.nrdc.springframework.netflux.domain.MovieEvent;
import ir.nrdc.springframework.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Mono<Movie> findById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String movieId) {
        return Flux.<MovieEvent>generate(sink -> sink.next(new MovieEvent(movieId, new Date())))
                .delayElements(Duration.ofSeconds(1));
    }

    @Override
    public List<MovieEvent> streamMovieEventsBlock(String movieId) {
        return Stream.generate(() -> new MovieEvent(movieId, new Date()))
                .toList();
    }
}
