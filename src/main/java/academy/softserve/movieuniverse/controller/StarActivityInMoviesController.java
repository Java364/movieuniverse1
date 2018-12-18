package academy.softserve.movieuniverse.controller;

import academy.softserve.movieuniverse.dto.StarActivityInMoviesDTO;
import academy.softserve.movieuniverse.entity.StarActivityInMovies;
import academy.softserve.movieuniverse.service.StarActivityInMoviesService;
import academy.softserve.movieuniverse.service.mapper.StarActivityInMoviesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StarActivityInMoviesController {

    private StarActivityInMoviesService starActivityInMoviesService;
    private StarActivityInMoviesMapper starActivityInMoviesMapper;

    @Autowired
    public StarActivityInMoviesController(StarActivityInMoviesService starActivityInMoviesService, StarActivityInMoviesMapper starActivityInMoviesMapper) {
        this.starActivityInMoviesService = starActivityInMoviesService;
        this.starActivityInMoviesMapper = starActivityInMoviesMapper;
    }

    @GetMapping("/starActivityInMovies")
    public List<StarActivityInMoviesDTO> viewAll() {
        List<StarActivityInMovies> allStarActivityInMovies = starActivityInMoviesService.findAllStarActivityInMovies();
        List<StarActivityInMoviesDTO> allStarActivityInMoviesDTOs = new ArrayList<>();
        for (StarActivityInMovies starActivityInMovies: allStarActivityInMovies) {
            StarActivityInMoviesDTO starActivityInMoviesDTO = starActivityInMoviesMapper.mapToDto(starActivityInMovies);
            allStarActivityInMoviesDTOs.add(starActivityInMoviesDTO);
        }
        return allStarActivityInMoviesDTOs;
    }

    @GetMapping("/starActivityInMovies/{id}")
    public StarActivityInMoviesDTO getStarActivityInMovies(@PathVariable Long id) {
        StarActivityInMovies starActivityInMovies = starActivityInMoviesService.getStarActivityInMovies(id);
        return starActivityInMoviesMapper.mapToDto(starActivityInMovies);
    }

    @DeleteMapping("/starActivityInMovies")
    public ResponseEntity deleteStarActivity(@RequestParam Long id) {
        starActivityInMoviesService.deleteStarActivityInMovies(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}