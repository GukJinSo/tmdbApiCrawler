package guckflix.crawlservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePathDto {
    private String backdropPath;
    private String posterPath;
}
