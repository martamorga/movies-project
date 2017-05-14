package pl.mmo;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import pl.mmo.web.SpringWebConfig;


public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String CHARACTER_ENCODING = "UTF-8";


    public SpringWebApplicationInitializer() {
        super();
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringBusinessConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
