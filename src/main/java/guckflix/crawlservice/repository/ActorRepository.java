package guckflix.crawlservice.repository;

import guckflix.crawlservice.entity.Actor;
import guckflix.crawlservice.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepository {

    @Autowired
    EntityManager em;

    public void save(Actor actor){
        if(actor.getId() == null){
            em.persist(actor);
        } else {
            em.merge(actor);
        }
    }

    public Actor findById(Long id){
        Actor actor = em.createQuery("select a from Actor a where a.id = :id", Actor.class)
                .setParameter("id", id)
                .getSingleResult();
        return actor;
    }

    public List<String> findAllImagePath() {
        return em.createQuery("select a.profilePath from Actor a", String.class).getResultList();
    }

    public List<Actor> findActorsPaging(int page, int pageSize){
        return em.createQuery("select a from Actor a order by a.id asc", Actor.class)
                .setFirstResult(page*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    public int getTotalPage() {
        return Math.toIntExact((long) em.createQuery("select count(a) from Actor a", Long.class).getSingleResult());
    }
}
