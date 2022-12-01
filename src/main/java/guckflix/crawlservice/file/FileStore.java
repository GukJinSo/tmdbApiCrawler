package guckflix.crawlservice.file;

import guckflix.crawlservice.dto.MoviePathDto;
import guckflix.crawlservice.service.ActorService;
import guckflix.crawlservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static guckflix.crawlservice.controller.TmdbApiConst.URL_ORIGINAL_IMAGE;
import static guckflix.crawlservice.controller.TmdbApiConst.URL_W500_IMAGE;
import static guckflix.crawlservice.file.FilePathConst.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStore {

    private final MovieService movieService;
    private final ActorService actorService;

    private final RestTemplate restTemplate;

    public void saveMovieImages() {

        List<MoviePathDto> allImagePath = movieService.findAllImagePath();
        for (MoviePathDto movieDto : allImagePath) {
            writeImage(URL_ORIGINAL_IMAGE, movieDto.getBackdropPath(), DIR_ORIGINAL_IMAGE);
            writeImage(URL_W500_IMAGE, movieDto.getPosterPath(), DIR_W500_IMAGE);
        }
        log.info("movie image save done");
    }

    public void saveActorImages(){
        List<String> fileNames = actorService.findAllImagePath();
        for (String fileName : fileNames) {
            writeImage(URL_W500_IMAGE, fileName, DIR_PROFILE);
        }
        log.info("actor image save done");
    }

    private void writeImage(String imageURL, String fileName, String dirPath){
        byte[] imageBytes = restTemplate.getForObject(imageURL + fileName, byte[].class);
        try {
            Files.write(Paths.get(dirPath + fileName), imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 중 에러 발생", e);
        }
    }
}
