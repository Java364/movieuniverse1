package academy.softserve.movieuniverse.dto.movie;

public class MovieDTO implements MovieInfoDTO, MovieCreateDTO {
    private Long id;
    private String movieName;
    private int year;
    private String description;
    private Long duration;
    private String ageLimitation;
    private String self;
    private String genres;
    private String gallery;
    private String stars;
    private String roles;
    private String countries;
    private String comments;
    private String movieMarks;

    @Override
    public String getGenres() {
        return genres;
    }

    @Override
    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMovieName() {
        return movieName;
    }

    @Override
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Long getDuration() {
        return duration;
    }

    @Override
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String getAgeLimitation() {
        return ageLimitation;
    }

    @Override
    public void setAgeLimitation(String ageLimitation) {
        this.ageLimitation = ageLimitation;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMovieMarks() {
        return movieMarks;
    }

    public void setMovieMarks(String movieMarks) {
        this.movieMarks = movieMarks;
    }
}