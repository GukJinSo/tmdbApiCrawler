package guckflix.crawlservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Actor {

    @Column(name = "actor_id")
    @Id private Long id;

    private String name;

    private String profilePath;

    private float popularity;
}
