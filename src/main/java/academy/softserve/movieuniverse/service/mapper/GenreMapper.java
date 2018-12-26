package academy.softserve.movieuniverse.service.mapper;

import academy.softserve.movieuniverse.controller.GenreController;
import academy.softserve.movieuniverse.dto.genre.GenreDTO;
import academy.softserve.movieuniverse.dto.genre.GenreRequest;
import academy.softserve.movieuniverse.entity.Genre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class GenreMapper{
    private ModelMapper modelMapper;

    @Autowired
    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public GenreDTO mapToDTO(Genre genre) {
        GenreDTO genreDTO = modelMapper.map(genre, GenreDTO.class);
        Link selfRelLink = linkTo(GenreController.class).slash(genre.getId()).withSelfRel();
        genreDTO.add(selfRelLink);
        return genreDTO;
    }

    public Genre mapToEntity(GenreRequest genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    public List<Genre> mapToEntityList(List<GenreRequest> genres) {
        return genres.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    public List<GenreDTO> mapToDTOList(List<Genre> genres) {
        return genres.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}