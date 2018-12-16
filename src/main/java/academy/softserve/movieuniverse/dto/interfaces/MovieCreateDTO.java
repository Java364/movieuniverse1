package academy.softserve.movieuniverse.dto.interfaces;

import academy.softserve.movieuniverse.dto.genre.GenreDto;

import java.util.List;

public interface MovieCreateDTO extends  MovieInfoDTO {
    List<GenreDto> getGenres();
    void setGenres(List<GenreDto> genres);
}
