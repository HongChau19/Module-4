package org.example.demo1;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { //nơi khai báo bean dành services/data/.
        return new Class<?>[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //nơi khai báo bean dành cho web layer (controller, view..)
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //đường dẫn mapping
        return new String[] {"/"};
    }
}
