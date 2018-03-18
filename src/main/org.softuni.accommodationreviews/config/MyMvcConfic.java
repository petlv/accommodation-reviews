package org.softuni.accommodationreviews.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfic implements WebMvcConfigurer {

    /*private final LogMessageInterceptor logMessageInterceptor;
    private final CaptchaInterceptor captchaInterceptor;

    @Autowired
    public MyMvcConfic(LogMessageInterceptor logMessageInterceptor, CaptchaInterceptor captchaInterceptor) {
        this.logMessageInterceptor = logMessageInterceptor;
        this.captchaInterceptor = captchaInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.logMessageInterceptor);
        registry.addInterceptor(this.captchaInterceptor);
    }*/
}
