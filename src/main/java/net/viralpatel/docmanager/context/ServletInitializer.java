package net.viralpatel.docmanager.context;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebContext.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { ApplicationContext.class };
  }
}

