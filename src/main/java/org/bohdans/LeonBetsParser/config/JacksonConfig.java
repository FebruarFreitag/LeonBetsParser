package org.bohdans.LeonBetsParser.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public JavaTimeModule javaTimeModule(){
        return new JavaTimeModule();
    }

    @Bean
    public ObjectMapper objectMapper(JavaTimeModule javaTimeModule){
        return new ObjectMapper().registerModule(javaTimeModule);
    }
}
