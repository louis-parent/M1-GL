package fr.umontpellier.TP7;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return null ;
    }

    @Override
    protected Class <?>[] getServletConfigClasses()
    {
        return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] { "/" } ;
    }
}
