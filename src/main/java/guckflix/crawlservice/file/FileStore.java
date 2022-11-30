package guckflix.crawlservice.file;

import guckflix.crawlservice.dto.MoviePathDto;
import guckflix.crawlservice.dto.MovieRequestResults.MovieDto;
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

import static guckflix.crawlservice.controller.TmdbApiConst.originalImageURL;
import static guckflix.crawlservice.controller.TmdbApiConst.w500ImageURL;
import static guckflix.crawlservice.file.FilePathConst.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStore {

    private final MovieService movieService;
    private final ActorService actorService;

    public void saveMovieImages() {

        RestTemplate restTemplate = new RestTemplate();
        List<MoviePathDto> allImagePath = movieService.findAllImagePath();
        for (MoviePathDto movieDto : allImagePath) {
            writeImage(restTemplate, originalImageURL, movieDto.getBackdropPath(), originalImageDir);
            writeImage(restTemplate, w500ImageURL, movieDto.getPosterPath(), w500ImageDir);
        }
    }

    public void saveActorImages(){
        RestTemplate restTemplate = new RestTemplate();
        List<String> fileNames = actorService.findAllImagePath();
        for (String fileName : fileNames) {
            writeImage(restTemplate, w500ImageURL, fileName, profileDir);
        }
    }

    private void writeImage(RestTemplate restTemplate, String imageURL, String fileName, String dirPath){
        byte[] imageBytes = restTemplate.getForObject(imageURL + fileName, byte[].class);
        try {
            Files.write(Paths.get(dirPath + fileName), imageBytes);
        } catch (IOException e) {
            log.error("이미지 저장 중 에러 발생 = Exception ", e);
            throw new RuntimeException(e);
        }
    }
}
