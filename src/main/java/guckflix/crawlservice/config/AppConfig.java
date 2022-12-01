package guckflix.crawlservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

@Configuration
public class AppConfig {

    /**
     * 오브젝트 매퍼
     * 데이트 타입을 아래 양식대로 받아올 것이고
     * 나머지 필드는 역직렬화 생략 설정 안해주면 익셉션 터짐
     * 모듈은 LocalDateTime 받기 위해서 추가
     */
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-mm-dd"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
