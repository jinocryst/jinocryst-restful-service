package net.jinocryst.jinocrystrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class JinocrystRestfulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinocrystRestfulServiceApplication.class, args);

        /*
        // Read All beanNames
        ApplicationContext ac = SpringApplication.run(JinocrystRestfulServiceApplication.class, args);

        String[] allBeanNames = ac.getBeanDefinitionNames();
        for (String beansName : allBeanNames) {
            System.out.println(beansName);
        }
         */

    }


    @Bean
    public LocaleResolver localResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
}
