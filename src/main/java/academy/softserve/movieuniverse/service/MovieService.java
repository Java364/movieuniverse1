package academy.softserve.movieuniverse.service;

import academy.softserve.movieuniverse.entity.Movie;
import academy.softserve.movieuniverse.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        movie = movieRepository.save(movie);
        return movie;
    }

    public List<Movie> showAllMovie() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }
}