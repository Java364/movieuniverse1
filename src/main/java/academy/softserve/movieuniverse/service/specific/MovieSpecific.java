package academy.softserve.movieuniverse.service.specific;

import academy.softserve.movieuniverse.dto.movie.MovieSearchRequest;
import academy.softserve.movieuniverse.entity.Genre;
import academy.softserve.movieuniverse.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import java.util.List;

@Component
public class MovieSpecific {

    public Specification<Movie> filter(MovieSearchRequest movieSearchRequest) {
        Specification<Movie> keyword = movieSearchRequest.getKeyword() == null ? null
                : anyFieldsContainsKeyword(movieSearchRequest.getKeyword());
        Specification<Movie> betweenYears = movieSearchRequest.getYearFrom() == null
                ? movieSearchRequest.getYearTo() == null ? null : isBetweenYears(0, movieSearchRequest.getYearTo())
                : isBetweenYears(movieSearchRequest.getYearFrom(),
                movieSearchRequest.getYearTo() == null ? 9999 : movieSearchRequest.getYearTo());

        Specification<Movie> removed = isRemoved(false);
        Specification<Movie> genre = movieSearchRequest.getGenres() == null ? null : containsGenres(movieSearchRequest.getGenres());

        return Specification.where(keyword).and(betweenYears).and(removed).and(genre);
    }

    public Specification<Movie> isRemoved(boolean isRemoved) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isRemoved"), isRemoved);
    }

    public Specification<Movie> isBetweenYears(Integer from, Integer to) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("year"), from, to);
    }

    public Specification<Movie> hasName(String name) {
        return containsKeyword("movieName", name);
    }

    private Specification<Movie> containsKeyword(String field, String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(field)),
                "%" + keyword.toLowerCase() + "%");
    }

    public Specification<Movie> anyFieldsContainsKeyword(String keyword) {
        return Specification.where(containsKeyword("movieName", keyword)).or(containsKeyword("description", keyword))
                .or(containsKeyword("ageLimitation", keyword));
    }

    public Specification<Movie> containsGenres(List<String> genres) {
        return (root, query, criteriaBuilder) -> {
            ListJoin<Movie, Genre> joinList = root.joinList("genres", JoinType.INNER);
            return (criteriaBuilder.lower(joinList.get("name")).in(genres));
        };
    }

    // public static Specification<Movie> isIncludeGenres(List<String> genre){
    // return ((root, query, criteriaBuilder) ->criteriaBuilder.equal(root.get("genres").get("name"), genre));
    // }

}
