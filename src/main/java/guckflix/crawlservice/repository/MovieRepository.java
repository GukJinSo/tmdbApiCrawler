package guckflix.crawlservice.repository;


import guckflix.crawlservice.dto.MoviePathDto;
import guckflix.crawlservice.dto.MovieRequestResults;
import guckflix.crawlservice.entity.Actor;
import guckflix.crawlservice.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static guckflix.crawlservice.dto.MovieRequestResults.*;

@Repository
public class MovieRepository {

    @Autowired private EntityManager em;

    public void save(Movie movie) {
        if(movie.getId() == null){
            em.persist(movie);
        } else {
            em.merge(movie);
        }
    }

    public List<Long> findAllIds(){
        return em.createQuery("select m.id from Movie m", Long.class).getResultList();
    }

    public List<MoviePathDto> findAllImagePath() {
        return em.createQuery("select new guckflix.crawlservice.dto.MoviePathDto(m.backdropPath, m.posterPath)" +
                " from Movie m", MoviePathDto.class).getResultList();
    }

    public Movie findById(Long id) {
        Movie movie = em.createQuery("select m from Movie m where m.id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
        return movie;

    }
}
