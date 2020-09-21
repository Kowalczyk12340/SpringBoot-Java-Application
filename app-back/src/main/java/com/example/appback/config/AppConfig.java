package com.example.appback.config;

import com.example.appback.task.Task;
import com.example.appback.task.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class AppConfig
{
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner(TaskRepository repository) {
        return args -> {
            repository.save(new Task("Go to the supermarket", true));
            repository.save(new Task("Do homework", false));
            repository.save(new Task("Clean room ✨", false));

            repository.findAll()
                    .forEach(s -> log.info(s.toString()));

            log.info(repository.findById(1L).toString());
            log.info(repository.findByName("Do homework").toString());
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/v1/**")
                        .allowedOrigins("*")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PATCH.name(),
                                HttpMethod.DELETE.name());
            }
        };
    }
}
