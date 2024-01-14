package com.apress.spring6recipes.reactive.court;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.WebApplicationInitializer;
import jakarta.servlet.ServletContext;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

public class WebFluxInitializer implements WebApplicationInitializer, AbstractReactiveWebInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        var context = new AnnotationConfigApplicationContext(WebFluxConfiguration.class);
        var httpHandler = WebHttpHandlerBuilder.applicationContext(context).build();
        var adapter = new ServletHttpHandlerAdapter(httpHandler);

        var registration = servletContext.addServlet("dispatcher-handler", adapter);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        registration.setAsyncSupported(true);
    }

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class<?>[] {WebFluxConfigurer.class };
    }
}
