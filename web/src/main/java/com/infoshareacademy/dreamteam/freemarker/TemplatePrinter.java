package com.infoshareacademy.dreamteam.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RequestScoped
public class TemplatePrinter {

    private static final Logger logger = LoggerFactory.getLogger(TemplatePrinter.class);

    @Inject
    private TemplateProvider templateProvider;

    public void printTemplate(HttpServletResponse resp, Map<String, Object> map, ServletContext servletContext,
                              String templateName) {
        try {
            PrintWriter writer = resp.getWriter();
            Template template = templateProvider.getTemplate(servletContext, templateName);
            template.process(map, writer);
        } catch (TemplateException | IOException e) {
            logger.error("Problem with printing the template {}\n", templateName, e);
        }
    }

}
