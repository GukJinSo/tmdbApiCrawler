package guckflix.crawlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import guckflix.crawlservice.entity.enums.ISO3166;
import guckflix.crawlservice.entity.enums.ISO639;
import guckflix.crawlservice.entity.enums.VideoProvider;
import guckflix.crawlservice.entity.enums.VideoType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class VideoRequestResults {

    @JsonProperty("results")
    private List<VideoDto> videos;

    @Getter
    @Setter
    @ToString
    public static class VideoDto{

        private String id; // tmdb는 62c041a322e4800fa8f138eb 같은 식으로 관리하기 때문에, 별도의 id를 두고 이것을 uuid로 세팅

        private Long movieId;

        private String name;

        @JsonProperty("iso_639_1")
        private ISO639 iso639;

        @JsonProperty("iso_3166_1")
        private ISO3166 iso3166;

        private String key;

        private Boolean official;

        private VideoProvider site;

        private VideoType type;

        @JsonProperty("published_at")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime publishedAt;
    }
}