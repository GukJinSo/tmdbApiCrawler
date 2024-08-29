package guckflix.crawlservice.service;

import guckflix.crawlservice.entity.Actor;
import guckflix.crawlservice.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static guckflix.crawlservice.dto.CreditRequestResults.*;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    @Transactional
    public void save(CreditDto credit) {
        Actor actor = Actor.builder().name(credit.getName())
                .id(credit.getId())
                .name(credit.getName())
                .popularity(credit.getPopularity())
                .profilePath(credit.getProfilePath())
                .build();
        actorRepository.save(actor);
    }

    public Actor findById(Long id){
        return actorRepository.findById(id);
    }

    public int findLastPage(){
        return actorRepository.getTotalPage();
    }

    public List<String> findAllImagePath(){
        return actorRepository.findAllImagePath();
    }

    @Transactional
    public void updateActors(Actor actor, LocalDate birthDay, LocalDate deathDay, String placeOfBirth, String biography) {
        System.out.println("actor.getId() = " + actor.getId());
        actor.updateBirthDay(birthDay);
        actor.updatedeathDay(deathDay);
        actor.updatePlaceOfBirth(placeOfBirth);
        actor.updateBiography(biography);
    }

    public List<Actor> findActorPaging(int page, int offset) {
        return actorRepository.findActorsPaging(page, offset);
    }
}
