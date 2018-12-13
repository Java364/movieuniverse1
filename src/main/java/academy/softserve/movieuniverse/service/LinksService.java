package academy.softserve.movieuniverse.service;

import academy.softserve.movieuniverse.dto.LinksDTO;
import academy.softserve.movieuniverse.entity.Links;
import academy.softserve.movieuniverse.repository.LinksRepository;
import academy.softserve.movieuniverse.service.mapper.LinksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinksService {
    @Autowired
    private LinksRepository linksRepository;
    private LinksMapper linksMapper = new LinksMapper();

    public void saveLinks(Links links){
       linksRepository.save(links);
    }

    public List<Links> findAll(){
        return linksRepository.findAll();
    }

    public void deleteLinks(Long id){
        linksRepository.deleteById(id);
    }


    public LinksDTO getLinksDTO(Long id){
        Optional<Links> linksOptional = linksRepository.findById(id);
        return linksMapper.mapToDto(linksOptional.get());
    }
}
