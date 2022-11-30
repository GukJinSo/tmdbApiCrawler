package guckflix.crawlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CreditRequestResults {

    @JsonProperty("cast")
    private List<CreditDto> credits;

    @Getter
    @Setter
    @ToString
    public static class CreditDto{

        private Long id;

        private String name;

        private float popularity;

        @JsonProperty("profile_path")
        private String profilePath;

        private String character;

        private Integer order;

        private Long movieId;
    }
}

