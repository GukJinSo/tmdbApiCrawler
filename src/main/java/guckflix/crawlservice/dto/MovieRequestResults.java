package guckflix.crawlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
public class MovieRequestResults {

    @JsonProperty("results")
    private List<MovieDto> movies;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class MovieDto {

        private Long id;

        @JsonProperty("backdrop_path")
        private String backdropPath;

        private float popularity;

        @JsonProperty("title")
        private String title;

        private String overview;

        @JsonProperty("poster_path")
        private String posterPath;

        @JsonProperty("release_date")
        private String releaseDate;

        @JsonProperty("genre_ids")
        private List<String> genres;

        @JsonProperty("vote_average")
        private float voteAverage;

        @JsonProperty("vote_count")
        private float voteCount;

        public MovieDto(String backdropPath, String posterPath) {
            this.backdropPath = backdropPath;
            this.posterPath = posterPath;
        }
    }

}
