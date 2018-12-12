package academy.softserve.movieuniverse.repository;

import academy.softserve.movieuniverse.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    @Query("select ur from UserReview ur inner join ur.reviewedMovie rm where rm.id = :movieId")
    List<UserReview> findAllUserReviewsByMovieId(@Param("movieId") Long movieId);
}