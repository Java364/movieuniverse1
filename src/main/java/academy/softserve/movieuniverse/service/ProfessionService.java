package academy.softserve.movieuniverse.service;

import academy.softserve.movieuniverse.entity.Profession;
import academy.softserve.movieuniverse.exception.ExceptionType;
import academy.softserve.movieuniverse.exception.NotFoundException;
import academy.softserve.movieuniverse.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {
    @Autowired
    private ProfessionRepository professionRepository;

    public void create(Profession profession) {
        if (profession.getType().isEmpty() || profession == null)
            throw new NotFoundException(ExceptionType.SAVE.getMessage() + "Profession");
        {
            professionRepository.save(profession);
        }
    }

    public List<Profession> findAll() {
        return professionRepository.findAll();
    }

    public void delete(Long id) {
        if (!professionRepository.findById(id).isPresent())
            throw new NotFoundException(ExceptionType.DELETE.getMessage() + "profession with ID - " + id.toString());

        professionRepository.deleteById(id);
    }

    public Profession update(Profession profession) {
        if (profession == null || !professionRepository.findById(profession.getId()).isPresent())
            throw new NotFoundException(ExceptionType.UPDATE.getMessage() + "Profession");

        profession = professionRepository.save(profession);
        return profession;
    }

    public Profession findById(Long id) {
        Optional<Profession> profession = professionRepository.findById(id);
        if (!profession.isPresent()) {
            throw new NotFoundException(ExceptionType.SELECT.getMessage() + "profession with ID - " + id.toString());
        }
        Profession profession1 = professionRepository.getOne(id);
        return profession1;

    }
}
