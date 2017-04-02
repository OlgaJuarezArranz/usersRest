package com.ojuarezarranz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
 
import com.ojuarezarranz.configuration.JpaConfiguration;
 
 
@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.ojuarezarranz"})
public class usersRestApp {
 
    public static void main(String[] args) {
    	System.out.println("Hola mundo");
        SpringApplication.run(usersRestApp.class, args);
    }
}








