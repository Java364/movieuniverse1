package academy.softserve.movieuniverse.dto.movie;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import java.util.List;

@JsonDeserialize(as = MovieDTO.class)
public interface MovieCreateDTO extends  MovieInfoDTO {
    String getGenres();
    void setGenres(String genres);

    String getCountries();
    void setCountries(String countries);
}
