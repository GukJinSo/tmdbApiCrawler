package guckflix.crawlservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guckflix.crawlservice.dto.CreditRequestResults;
import guckflix.crawlservice.dto.MovieRequestResults;
import guckflix.crawlservice.service.ActorService;
import guckflix.crawlservice.service.CreditService;
import guckflix.crawlservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static guckflix.crawlservice.controller.TmdbApiConst.*;
import static guckflix.crawlservice.controller.TmdbApiConst.API_KEY;
import static guckflix.crawlservice.controller.TmdbApiConst.basicURL;
import static guckflix.crawlservice.dto.CreditRequestResults.*;
import static guckflix.crawlservice.dto.MovieRequestResults.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiRequester {

    private final MovieService movieService;

    private final CreditService creditService;
    private final ActorService actorService;
    private final ObjectMapper objectMapper;

    /**
     * tmdb api는 요청 시 20개씩 페이징함
     * LOOP_NUMBER x 20개
     */

    public void saveMovie() throws JsonProcessingException, ParseException, InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        // 저장
        for (int i = 1; i <= LOOP_NUMBER; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(basicURL+"popular?api_key="+API_KEY+"&page="+i, String.class);
            MovieRequestResults movieRequestResults = objectMapper.readValue(response.getBody(), MovieRequestResults.class);
            for (MovieDto dto : movieRequestResults.getMovies()) {
                if(dto.getBackdropPath() != null && dto.getPosterPath() != null){
                    dto.setBackdropPath(dto.getBackdropPath().replace("/", ""));
                    dto.setPosterPath(dto.getPosterPath().replace("/", ""));
                    movieService.save(dto);
                }
            }
            Thread.sleep(50);
            log.info("{} times", i);
        }

    }

    public List<CreditDto> saveActor() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        List<Long> ids = movieService.findAllIds();
        List<CreditDto> returnDtos = new ArrayList<>();

        for (Long id : ids) {
            ResponseEntity<String> response = restTemplate.getForEntity(basicURL+id+"/credits?api_key="+API_KEY, String.class);
            List<CreditDto> credits = objectMapper.readValue(response.getBody(), CreditRequestResults.class).getCredits();

            sortByOrder(credits);
            List<CreditDto> filteredActors = filterByActor(credits);

            for (CreditDto credit : filteredActors) {
                credit.setMovieId(id);
                credit.setProfilePath(credit.getProfilePath().replace("/", ""));
                actorService.save(credit);
                returnDtos.add(credit);
            }
        }

        return returnDtos;
    }

    public void saveCredit(List<CreditDto> credits) throws JsonProcessingException {
        for (CreditDto credit : credits) {
            creditService.save(credit);
        }
    }

    private void sortByOrder(List<CreditDto> credits){
        credits.sort((credit1, credit2)-> {
            if(credit1.getOrder() > credit2.getOrder()){
                return 1;
            } else if (credit1.getOrder() < credit2.getOrder()) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    private List<CreditDto> filterByActor(List<CreditDto> list){

        // 배우인지 필터링
        List<CreditDto> filteredActors = list.stream().filter((credit) ->
                credit.getProfilePath() != "" && credit.getProfilePath() != null && credit.getOrder() != null
        ).collect(Collectors.toList());

        // 15명 이상이면 15명까지만 자름
        if(filteredActors.size() > 15){
            filteredActors = filteredActors.subList(0, 15);
        }

        return filteredActors;
    }
}
