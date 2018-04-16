package org.softuni.accommodationreviews.config;

import org.softuni.accommodationreviews.interceptors.AccommodationFormInterceptor;
import org.softuni.accommodationreviews.interceptors.CaptchaInterceptor;
import org.softuni.accommodationreviews.interceptors.LogMessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfic implements WebMvcConfigurer {

    private final LogMessageInterceptor logMessageInterceptor;
    private final CaptchaInterceptor captchaInterceptor;
    private final AccommodationFormInterceptor accommodationFormInterceptor;

    @Autowired
    public MyMvcConfic(LogMessageInterceptor logMessageInterceptor, CaptchaInterceptor captchaInterceptor,
                       AccommodationFormInterceptor accommodationFormInterceptor) {
        this.logMessageInterceptor = logMessageInterceptor;
        this.captchaInterceptor = captchaInterceptor;
        this.accommodationFormInterceptor = accommodationFormInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(this.logMessageInterceptor);
        //registry.addInterceptor(this.captchaInterceptor);
        //registry.addInterceptor(this.accommodationFormInterceptor);
    }
}
