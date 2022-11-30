package guckflix.crawlservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {

    @Id @GeneratedValue @Column(name = "credit_id")
    private Long id;

    @Column(length = 500)
    private String character;

    private Integer castingOrder;

    @JoinColumn(name = "movie_id")
    private Long movieId;

    @JoinColumn(name = "actor_id")
    private Long actorId;

}
