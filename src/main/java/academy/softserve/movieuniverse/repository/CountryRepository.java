package academy.softserve.movieuniverse.repository;

import academy.softserve.movieuniverse.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
