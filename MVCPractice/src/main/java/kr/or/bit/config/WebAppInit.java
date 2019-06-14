package kr.or.bit.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebAppInit implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext context) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.setConfigLocation("kr.or.bit.config");
    ServletRegistration.Dynamic registration = context.addServlet("dispatcher", new DispatcherServlet(applicationContext));
    registration.setLoadOnStartup(1);
    registration.addMapping("/");

    FilterRegistration.Dynamic characterEncodingFilter = context.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
    characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    characterEncodingFilter.setInitParameter("encoding", "UTF-8");
    characterEncodingFilter.setInitParameter("forceEncoding", "true");
  }
}
