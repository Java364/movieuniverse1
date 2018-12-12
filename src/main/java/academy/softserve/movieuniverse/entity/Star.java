package academy.softserve.movieuniverse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_stars")
public class Star extends Person {

    @Column(name = "star_biography")
    private String biography;

    @Column(name = "star_growth")
    private Double growth;

    @Column(name = "star_country")
    private String countryOfBirth; //TODO Country

    @Column(name = "star_city")
    private String cityOfBirth;

    @OneToMany(mappedBy = "star")
    private List<StarActivityInFilms> roles = new ArrayList<StarActivityInFilms>();

    @OneToMany(mappedBy = "star")
    private List<StarProfession> professions = new ArrayList<StarProfession>();

    @OneToMany(mappedBy = "star")
    private List<Links> links = new ArrayList<Links>();

    @ManyToMany
    @JoinTable(name = "star_movie",
            joinColumns = @JoinColumn(name = "star_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies = new ArrayList<Movie>();
    
    @OneToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public Star() {
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<StarActivityInFilms> getRoles() {
        return roles;
    }

    public void setRoles(List<StarActivityInFilms> roles) {
        this.roles = roles;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public List<StarProfession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<StarProfession> professions) {
        this.professions = professions;
    }
}