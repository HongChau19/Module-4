package org.example.ex1;

import org.example.ex1.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.ex1.controllers")
//load beans --> (controller, view...)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //đường dẫn đến view ở đâu
        viewResolver.setPrefix("/WEB-INF/view/");
        // đuôi file view là gì (Jsp)
        viewResolver.setSuffix(".jsp");
        // font hiển thị tiếng việt
        viewResolver.setContentType("text/html;charset=UTF-8");

        return viewResolver;
    }
}
