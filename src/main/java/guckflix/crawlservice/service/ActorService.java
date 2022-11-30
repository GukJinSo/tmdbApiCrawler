package guckflix.crawlservice.service;

import guckflix.crawlservice.entity.Actor;
import guckflix.crawlservice.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<String> findAllImagePath(){
        return actorRepository.findAllImagePath();
    }
}
