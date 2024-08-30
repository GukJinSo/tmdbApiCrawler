package guckflix.crawlservice.service;

import guckflix.crawlservice.dto.MoviePathDto;
import guckflix.crawlservice.dto.MovieRequestResults.MovieDto;
import guckflix.crawlservice.entity.Movie;
import guckflix.crawlservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    @Transactional
    public void save(MovieDto detail) throws ParseException {
        repository.save(dtoToMovieEntity(detail));
    }

    public List<Long> findAllIds(){
        return repository.findAllIds();
    }

    private Movie dtoToMovieEntity(MovieDto detail) throws ParseException {

        java.sql.Date sqlDate = null;
        if(!detail.getReleaseDate().equals("")){
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(detail.getReleaseDate());
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        Movie movie = Movie.builder().backdropPath(detail.getBackdropPath())
                .genres(
                        detail.getGenres().stream().collect(Collectors.joining(","))
                )
                .overview(detail.getOverview())
                .popularity(detail.getPopularity())
                .releaseDate(sqlDate)
                .posterPath(detail.getPosterPath())
                .title(detail.getTitle())
                .id(detail.getId())
                .voteAverage(detail.getVoteAverage())
                .voteCount(detail.getVoteCount())
                .build();
        return movie;
    }

    public List<MoviePathDto> findAllImagePath() {
        return repository.findAllImagePath();
    }
}
