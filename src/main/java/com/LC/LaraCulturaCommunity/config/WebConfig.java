package com.LC.LaraCulturaCommunity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/activity").setViewName("activity");
        registry.addViewController("/element").setViewName("element");
        registry.addViewController("/speakers").setViewName("speakers");
        registry.addViewController("/form").setViewName("form");
        

        // registry.addViewController("/newArticle").setViewName("newArticle");

        registry.addViewController("/about").setViewName("about");

        registry.addViewController("/generic").setViewName("generic");
        registry.addViewController("/rapports").setViewName("rapports");
        registry.addViewController("/members").setViewName("members");
        registry.addViewController("/gallery").setViewName("gallery");
        registry.addViewController("/msg-succes").setViewName("msg-succes");
        
        
        

        

    }
    

}
