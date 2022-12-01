package guckflix.crawlservice.repository;

import guckflix.crawlservice.entity.Video;
import guckflix.crawlservice.entity.enums.ISO3166;
import guckflix.crawlservice.entity.enums.ISO639;
import guckflix.crawlservice.entity.enums.VideoProvider;
import guckflix.crawlservice.entity.enums.VideoType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class VideoJdbcRepositoryTest {

    @Autowired
    VideoJdbcRepository videoJdbcRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void bulkSaveTest(){

        List<Video> dummyList = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {

            String uuid = UUID.randomUUID().toString();
            Video build = Video.builder()
                    .id(uuid)
                    .key("dk2o1959jaljl1j2l2jl4")
                    .site(VideoProvider.YouTube)
                    .publishedAt(LocalDateTime.now())
                    .type(VideoType.Behind_the_Scenes)
                    .name("IT 업체 취직하고파")
                    .official(true)
                    .iso3166(ISO3166.KR)
                    .iso639(ISO639.ko)
                    .build();
            dummyList.add(build);
        }

        videoJdbcRepository.bulkSave(dummyList);

    }

}