package ntk.tlu.project1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@EnableCaching
public class Project1Application {
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}
	
	// model Mapper
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
/*
 * Mỗi product có nhiều Comment(id, content, User createdBy, Date createdDate,
 * Product product)
 * 
 * Mỗi product có nhiều Review(id, int startCounter, User createdBy, Date
 * createdDate, Product product)
 */