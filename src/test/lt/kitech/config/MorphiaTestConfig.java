package lt.kitech.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class MorphiaTestConfig {

    @Autowired
    private MongoClient mongo;

    @Autowired
    private Environment environment;

    @Bean
    public Datastore datastore() throws ClassNotFoundException {
        Morphia morphia = new Morphia();
        morphia.mapPackage(environment.getRequiredProperty("morphia.map.package"));
        return morphia.createDatastore(mongo, mongo.getDatabaseNames().get(0));
    }
}
