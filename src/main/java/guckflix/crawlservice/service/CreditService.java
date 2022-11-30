package guckflix.crawlservice.service;

import guckflix.crawlservice.dto.CreditRequestResults.CreditDto;
import guckflix.crawlservice.entity.Actor;
import guckflix.crawlservice.entity.Credit;
import guckflix.crawlservice.entity.Movie;
import guckflix.crawlservice.repository.ActorRepository;
import guckflix.crawlservice.repository.CreditRepository;
import guckflix.crawlservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void save(CreditDto dto){

        Actor findActor = actorRepository.findById(dto.getId());
        Movie findMovie = movieRepository.findById(dto.getMovieId());

        Credit credit = Credit.builder()
                .actorId(findActor.getId())
                .movieId(findMovie.getId())
                .character(dto.getCharacter())
                .castingOrder(dto.getOrder())
                .build();
        creditRepository.save(credit);
    }


}
