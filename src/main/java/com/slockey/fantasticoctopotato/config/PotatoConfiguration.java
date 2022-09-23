package com.slockey.fantasticoctopotato.config;

import com.slockey.fantasticoctopotato.stream.PotatoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PotatoConfiguration {

    @Bean
    public PotatoRepository getPotatoRepository() {
        return new PotatoRepository();
    }

}
