package usermangt.usermanagt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class UserManagtApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(UserManagtApplication.class, args);
    }

}
