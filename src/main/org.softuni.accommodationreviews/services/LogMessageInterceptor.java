package org.softuni.accommodationreviews.services;

import org.softuni.accommodationreviews.entities.LogMessage;
import org.softuni.accommodationreviews.repositories.LogMessageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class LogMessageInterceptor extends HandlerInterceptorAdapter {

    private Long currentTimeBeforeExecution;
    private Long currentTimeAfterExecution;
    private Long currentTimeAfterViewRendered;

    private final LogMessageRepository logMessageRepository;

    public LogMessageInterceptor(LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        this.currentTimeBeforeExecution = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        this.currentTimeAfterExecution = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

        this.currentTimeAfterViewRendered = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            String message = String.format(
                    "[%s - %s] Action Execute Time: %d ms, Overall Execute Time: %d ms",
                    handlerMethod.getBeanType().getName(),
                    handlerMethod.getMethod().getName(),
                    this.currentTimeAfterExecution - this.currentTimeBeforeExecution,
                    this.currentTimeAfterViewRendered - this.currentTimeBeforeExecution
                    );
            Date date = new Date();
            LogMessage messageEntity = new LogMessage();
            messageEntity.setCreatedOn(date);
            messageEntity.setMessage(message);
            this.logMessageRepository.saveAndFlush(messageEntity);
        }

        super.afterCompletion(request, response, handler, ex);
    }
}
