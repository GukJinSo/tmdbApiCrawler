package guckflix.crawlservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.text.ParseException;

@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CrawlserviceApplication {

	/*
	public static void main(String[] args) throws JsonProcessingException, ParseException {
		// 스프링부트를 웹 어플리케이션으로 사용하지 않겠다는 설정
		SpringApplication springApplication = new SpringApplication(CrawlserviceApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);

		Runner runner = new Runner();
		runner.run();
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(CrawlserviceApplication.class, args);
	}
}