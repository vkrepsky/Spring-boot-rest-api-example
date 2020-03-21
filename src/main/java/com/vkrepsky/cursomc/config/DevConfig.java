package com.vkrepsky.cursomc.config;

import com.vkrepsky.cursomc.services.DBService;
import com.vkrepsky.cursomc.services.EmailService;
import com.vkrepsky.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }

        dbService.instaniateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
