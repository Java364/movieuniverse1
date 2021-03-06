package academy.softserve.movieuniverse.repository;

import academy.softserve.movieuniverse.dto.user.UserCreateInfo;
import academy.softserve.movieuniverse.entity.MovieMark;
import academy.softserve.movieuniverse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIsRemoved(Boolean isRemoved);

    User findByMovieMarks(MovieMark movieMark);

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
