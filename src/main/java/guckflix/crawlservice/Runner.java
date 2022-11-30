package guckflix.crawlservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import guckflix.crawlservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class Runner {

    /**
     * 메서드에서 runner = new Runner()로 호출된다.
     * 호출하는 Runner 클래스가 빈이 아니므로 스프링 Bean을 사용할 경우 아래처럼 꺼내야 함
     */
    ApplicationContext ac = new AnnotationConfigApplicationContext(CrawlserviceApplication.class);

    MovieService movieService = ac.getBean("movieService", MovieService.class);

    /**
     * API 통신을 하기 위한 RestTemplate 사용
     */
    RestTemplate restTemplate = new RestTemplate();
    private final String basicURL = "https://api.themoviedb.org/3/movie/";
    private final String API_KEY = "";
    private ObjectMapper mapper;

    /**
     * new Runner()의 트랜잭션 이슈
     * Repository 단일 테스트 시 잘 동작하는데, Runner 클래스를 사용해서 테스트하면 insert문만 로그에 찍힐 뿐, 실제 db insert가 되지 않았음
     * 같은 코드를 컨트롤러에서 Runner 클래스를 빼고 서비스를 직접 불러 사용해보니 동작 확인
     * -> Runner가 스프링에 관리받는 Bean이 아니므로 트랜잭션이 제대로 사용되지 않는다고 판단함
     *
     * 나처럼 빈이 아닌 경우 stackoverflow에서 txTemplate을 사용해보라는 답변 발견
     * https://stackoverflow.com/questions/57973944/how-to-use-transactional-in-non-spring-managed-beans
     */

    // List<MovieDto> results = new ArrayList<>();

    public void run() throws JsonProcessingException, ParseException {


        // ID만 API 호출해서 따오는 과정
        //getIds(results);

        // 따온 ID로 영화 정보, 크레딧이 누가 있는지 다시 API 호출해서 알아내고 저장하는 과정
        //getDetail();

        // 영화를 저장하고, 포스터 파일에 write 하는 과정

    }

//    private void getDetail() throws JsonProcessingException, ParseException {
//
//        try {
//            for (MovieDto result : results) {
//                ResponseEntity<String> response = restTemplate.getForEntity(basicURL+result.getId()+"?&api_key="+API_KEY, String.class);
//                MovieDetail detail = mapper.readValue(response.getBody(), MovieDetail.class);
//                detail.setId(result.getId());
//                movieService.save(detail);
//            }
//        } catch (RuntimeException e){
//            log.warn("exception = ", e);
//        }
//
//    }

//    private void getIds(List<MovieDto> results){
//        try {
//            for (int i = 1; i <= 1; i++) {
//                ResponseEntity<String> response = restTemplate.getForEntity(basicURL+"popular?api_key="+API_KEY+"&page="+i, String.class);
//                RequestResult requestResult = mapper.readValue(response.getBody(), RequestResult.class);
//                for (MovieDto result : requestResult.getMovies()) {
//                    results.add(result);
//                }
//            }
//        } catch (Exception e) {
//            log.warn("Exception ", e);
//        }
//        System.out.println("results = " + results.size());
//    }

    /**
     * init 메서드
     */
    public Runner() {
        mapperInit();
    }

    /**
     * 오브젝트 매퍼
     * Date 타입을 아래 양식대로 받아올 것이고
     * 나머지 필드는 역직렬화 생략 설정 안해주면 익셉션 터짐
     */
    private void mapperInit(){
        mapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-mm-dd"))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
