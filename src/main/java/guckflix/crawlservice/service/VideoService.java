package guckflix.crawlservice.service;

import guckflix.crawlservice.dto.VideoRequestResults.VideoDto;
import guckflix.crawlservice.entity.Video;
import guckflix.crawlservice.repository.MovieRepository;
import guckflix.crawlservice.repository.VideoJdbcRepository;
import guckflix.crawlservice.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoJdbcRepository videoJdbcRepository;

    @Transactional
    public void save(VideoDto videoDto) throws ParseException {
        Video video = dtoToVideoEntity(videoDto);
        videoRepository.save(video);
    }

    private Video dtoToVideoEntity(VideoDto videoDto) throws ParseException {
        Video video = Video.builder()
                .id(videoDto.getId())
                .movieId(videoDto.getMovieId())
                .site(videoDto.getSite())
                .type(videoDto.getType())
                .iso639(videoDto.getIso639())
                .iso3166(videoDto.getIso3166())
                .official(videoDto.getOfficial())
                .publishedAt(videoDto.getPublishedAt())
                .key(videoDto.getKey())
                .name(videoDto.getName())
                .build();
        return video;
    }

    public void bulkSave(List<VideoDto> list) throws ParseException {
        List<Video> entities = new ArrayList<>();
        for (VideoDto videoDto : list) {
            Video video = dtoToVideoEntity(videoDto);
            entities.add(video);
        }
        videoJdbcRepository.bulkSave(entities);
    }
}

