package guckflix.crawlservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Actor {

    @Column(name = "actor_id")
    @Id private Long id;

    private String name;

    @Column(length = 5000)
    private String biography;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "death_day")
    private LocalDate deathDay;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    private String profilePath;

    private float popularity;

    public void updateBirthDay(LocalDate birthDay){
        this.birthDay = birthDay;
    }

    public void updatedeathDay(LocalDate deathDay){
        this.deathDay = deathDay;
    }

    public void updatePlaceOfBirth(String placeOfBirth){
        this.placeOfBirth = placeOfBirth;
    }

    public void updateBiography(String biography){
        this.biography = biography;
    }
}
