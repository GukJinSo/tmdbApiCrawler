package guckflix.crawlservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import guckflix.crawlservice.dto.CreditRequestResults;
import guckflix.crawlservice.file.FileStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    FileStore fileStore;

    @Autowired
    ApiRequester apiRequester;

    @GetMapping("/saveMovie")
    public String saveMovie() throws JsonProcessingException, ParseException, InterruptedException {
        // 1. Movie 엔티티로 저장하는 과정.
        // 평점(소수점)이 실시간으로 바뀌기 때문에 distinct 되질 않는다. merge() 처리
        apiRequester.saveMovie();
        return "1. OK";

    }

    @GetMapping("saveMovieImage")
    public  String saveMovieImage() {
        // 2. 영화 이미지 파일 write 하는 과정
        fileStore.saveMovieImages();
        return "2. OK";
    }

    @GetMapping("saveActor")
        public String saveActor() throws JsonProcessingException {
        // 3. Actor 엔티티로 저장하는 과정
        // 1에서 저장된 ID 조회한 뒤, api 요청으로 작품마다 참여 배우들을 요청함
        // 다작 배우의 경우 같은 id의 엔티티가 중복될 수 있으므로 merge() 처리
        List<CreditRequestResults.CreditDto> credits = apiRequester.saveActor();

        // 4. Credit 엔티티로 저장하는 과정
        // 3에서 소거한 데이터를 사용한다.
        apiRequester.saveCredit(credits);
        return "3, 4. OK";
    }


    @GetMapping("saveActorImage")
        public String saveActorImage() {
        // 5. 배우 이미지 파일 write 하는 과정
        fileStore.saveActorImages();
        return "5. OK";
    }

    @GetMapping("saveVideo")
    public String saveVideo() throws ParseException, JsonProcessingException, InterruptedException {
        // 6. 비디오 URL 가져오기 (JDBC 벌크로 처리)
        apiRequester.saveVideos();
        return "6. OK";
    }

    @GetMapping("updateActor")
    public String updateActor() {
        // 7. actor들 업데이트하기
        apiRequester.updateActors();
        return "7. OK";
    }

}
