package guckflix.crawlservice.runner;

import guckflix.crawlservice.dto.MovieRequestResults.MovieDto;
import guckflix.crawlservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;

@SpringBootTest
class MovieServiceTest {

    @Autowired MovieService movieService;

    @Test
    @Rollback(value = false)
    public void test() throws Exception {
        MovieDto detail = new MovieDto();
        detail.setOverview("hjii");
        detail.setGenres(Arrays.asList("10","20","30"));
        detail.setPopularity(200);
        detail.setReleaseDate("2020-11-24");
        detail.setTitle("블랙아담");
        detail.setId(11852L);

        movieService.save(detail);
    }

}