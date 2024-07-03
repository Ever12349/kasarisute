package com.kasarisute;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ KasarisuteUserApplication.class })
public class Application {
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setAdditionalProfiles("dev");

        application.run(args);

    }
}
