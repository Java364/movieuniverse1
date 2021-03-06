package academy.softserve.movieuniverse.repository;

import academy.softserve.movieuniverse.entity.Cast;
import academy.softserve.movieuniverse.entity.Movie;
import academy.softserve.movieuniverse.entity.MovieMark;
import academy.softserve.movieuniverse.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    Movie findAllByMovieMarks(MovieMark movieMark);

    @Query("select sp.star from Crew cr" + " inner join cr.starProfession sp"
            + " where cr.movie.id = :movieId and sp.profession.professionType = :profession")
    List<Star> findCreditsByProfession(@Param("movieId") Long movieId, @Param("profession") String profession);

    List<Movie> findAllByMovieNameIgnoreCaseContaining(String name);

    @Query("select c from Cast c where c.id = :castId")
    Cast findCastById(@Param("castId") Long id);
}
