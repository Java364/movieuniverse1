package academy.softserve.movieuniverse.service;

import academy.softserve.movieuniverse.dto.GenreDto;
import academy.softserve.movieuniverse.entity.Genre;
import academy.softserve.movieuniverse.repository.GenreRepository;
import academy.softserve.movieuniverse.service.mapper.GenreDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    private GenreDtoMapper genreDtoMapper;

    @Autowired
    public GenreService(GenreRepository genreRepository, GenreDtoMapper genreDtoMapper) {
        this.genreRepository = genreRepository;
        this.genreDtoMapper = genreDtoMapper;
    }

    @Transactional
    public GenreDto saveGenre(GenreDto genreDto) {
        Genre genre = genreRepository.save(genreDtoMapper.mapToEntity(genreDto));
        return genreDtoMapper.mapToDto(genre);
    }

    public Optional<GenreDto> findGenreById(Long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        return optionalGenre.map(genre -> genreDtoMapper.mapToDto(genre));
    }

    @Transactional
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}