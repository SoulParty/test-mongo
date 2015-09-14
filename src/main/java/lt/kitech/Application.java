package lt.kitech;

import lt.kitech.service.InitDatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application
        extends SpringBootServletInitializer {

    @Autowired
    InitDatabaseService initDatabaseService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initMorphia() {
//        Morphia morphia = new Morphia();
//        morphia.mapPackage(DBUtil.MAP_PACKAGE);
        initDatabaseService.createPeople(100);
//        DBUtil.getDatastore().ensureIndexes();
    }
}