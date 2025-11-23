package ir.nrdc.springframework.netflux.bootstrap;

import ir.nrdc.springframework.netflux.domain.Movie;
import ir.nrdc.springframework.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class InitMovie {

    private final MovieRepository movieRepository;

    @Bean
    CommandLineRunner initMovieRunner() {
        return args -> {
            System.out.println("Hello World from CommandLineRunner deleting previous movies");

            movieRepository.deleteAll()
                    .thenMany(
                            Flux.just(  "Inception",
                                    "Interstellar",
                                    "The Dark Knight",
                                    "Fight Club",
                                    "The Matrix",
                                    "Forrest Gump",
                                    "The Shawshank Redemption",
                                    "Pulp Fiction",
                                    "Gladiator",
                                    "The Lord of the Rings: The Fellowship of the Ring")
                                    .map(title -> Movie.builder().title(title).build())
                                    .flatMap(movieRepository::save)
                    ).subscribe(null, null, () ->
                                movieRepository.findAll().subscribe(System.out::println)
                    );
        };
    }
}
