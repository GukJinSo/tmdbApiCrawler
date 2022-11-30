package guckflix.crawlservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import guckflix.crawlservice.dto.CreditRequestResults;
import guckflix.crawlservice.file.FileStore;
import guckflix.crawlservice.service.ActorService;
import guckflix.crawlservice.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import static guckflix.crawlservice.dto.CreditRequestResults.*;
import static guckflix.crawlservice.dto.MovieRequestResults.*;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    FileStore fileStore;

    @Autowired
    ApiRequester apiRequester;

    @GetMapping("/crawl")
    public String crawl() throws JsonProcessingException, ParseException, InterruptedException {

        // 1. Movie 엔티티로 저장하는 과정.
        // 평점(소수점)이 실시간으로 바뀌기 때문에 distinct 되질 않는다. merge() 처리
        apiRequester.saveMovie();

        // 2. 영화 이미지 파일 write 하는 과정
        fileStore.saveMovieImages();

        // 3. Actor 엔티티로 저장하는 과정
        // 1에서 저장된 ID 조회한 뒤, api 요청으로 작품마다 참여 배우들을 요청함
        // 다작 배우의 경우 같은 id의 엔티티가 중복될 수 있으므로 merge() 처리
        List<CreditDto> credits = apiRequester.saveActor();

        // 4. Credit 엔티티로 저장하는 과정
        // 3에서 소거한 데이터를 사용한다.
        apiRequester.saveCredit(credits);

        // 5. 배우 이미지 파일 write 하는 과정
        fileStore.saveActorImages();

        return "OK";
    }
}
