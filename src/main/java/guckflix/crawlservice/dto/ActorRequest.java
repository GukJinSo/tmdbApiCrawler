package guckflix.crawlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class ActorRequest {

    @Column(length = 2000)
    private String biography;

    @JsonProperty("birthday")
    private LocalDate birthDay;

    @JsonProperty("deathday")
    private LocalDate deathDay;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    private String profilePath;

    private float popularity;
}
