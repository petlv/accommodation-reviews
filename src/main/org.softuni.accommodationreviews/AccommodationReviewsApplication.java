package org.softuni.accommodationreviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AccommodationReviewsApplication {
    public static void main(String[] arg) {
        SpringApplication.run(AccommodationReviewsApplication.class, arg);
    }

}
