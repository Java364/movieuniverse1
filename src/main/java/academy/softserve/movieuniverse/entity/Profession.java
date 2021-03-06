package academy.softserve.movieuniverse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie_professions")
public class Profession extends AbstractEntity {

    @Column(name = "profession_type")
    private String professionType;

    @OneToMany(mappedBy = "profession")
    private List<StarProfession> stars = new ArrayList<StarProfession>();

    public Profession() {
        super();
    }

    public String getType() {
        return professionType;
    }

    public void setType(String type) {
        this.professionType = type;
    }

    public List<StarProfession> getStars() {
        return stars;
    }

    public void setStars(List<StarProfession> stars) {
        this.stars = stars;
    }
}
