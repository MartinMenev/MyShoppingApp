package com.example.myshoppingapp.config;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BeanConfiguration {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public LoggedUser loggedUser() {
            return new LoggedUser();
        }

}
