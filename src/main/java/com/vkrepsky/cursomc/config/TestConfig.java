package com.vkrepsky.cursomc.config;

import com.vkrepsky.cursomc.services.DBService;
import com.vkrepsky.cursomc.services.EmailService;
import com.vkrepsky.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDataBase() throws ParseException {
        dbService.instaniateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
