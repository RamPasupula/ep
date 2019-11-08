package com.uob.edag.edagportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
 
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.uob.edag.edagportal")
@EnableScheduling
public class WebAppInitializer{
 
    public static void main(String[] args) throws Exception{
        SpringApplication.run(WebAppInitializer.class, args);
    }
    
   
    
}

