package guckflix.crawlservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Genre {

    @Id @GeneratedValue private Long id;

    @Column(name = "genre_name")
    private String genreName;
}
