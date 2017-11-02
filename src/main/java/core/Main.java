package core;

import ui.VServlet;
import core.config.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Enumeration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"ui", "core"})
public class Main extends SpringBootServletInitializer
        implements WebApplicationInitializer, ServletContextInitializer {

    private static AppConfig config;
    private final String[] args;
    private static boolean initialized;

    @Bean
    public AppConfig getConfig() {
        return config != null ? config : (config = new AppConfig(args));
    }

    public Main() {
        this(null);
    }

    private Main(String[] args) {
        this.args = args;
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (initialized) return;
        initialized = true;
        ServletRegistration.Dynamic servlet = servletContext.addServlet("Vaadin", VServlet.class);

        servlet.setInitParameter("UI", "ui.MyVaadinUI");
        servlet.setInitParameter("widgetset", "WidgetSet");
        servlet.addMapping("/VAADIN/*", "/", "/*");
        servlet.setAsyncSupported(true);

        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        System.out.println("-------------------------------------------------------------");

        while (initParameterNames.hasMoreElements()) {
            String elm = initParameterNames.nextElement();
            System.out.println("Init param " + elm + ": " + servletContext.getInitParameter(elm));
        }

//        Enumeration<String> attributeNames = servletContext.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String elm = attributeNames.nextElement();
//            System.out.println("Attribute " + elm + ": " + servletContext.getAttribute(elm));
//        }

        System.out.println("-------------------------------------------------------------");


        super.onStartup(servletContext);


    }

    @Override
    protected SpringApplicationBuilder createSpringApplicationBuilder() {


        return new SpringApplicationBuilder()
                .sources(Main.class)
                .properties(getConfig());
    }

    public static void main(String[] args) throws Exception {
        new Main(args).createSpringApplicationBuilder().run(args);
    }


}