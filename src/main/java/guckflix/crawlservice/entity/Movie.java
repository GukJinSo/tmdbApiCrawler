package guckflix.crawlservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    @Id private Long id;

    private String title;

    @Column(length = 1000)
    private String overview;

    private Date releaseDate;

    private String genres;

    private float popularity;

    private int voteCount;

    private float voteAverage;

    private String backdropPath;

    private String posterPath;

}
