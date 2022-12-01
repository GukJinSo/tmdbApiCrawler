package guckflix.crawlservice.repository;

import guckflix.crawlservice.entity.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class VideoRepository {

    @Autowired
    EntityManager em;

    public void save(Video video){
        em.persist(video);
    }
}
