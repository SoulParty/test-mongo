package lt.kitech.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class MorphiaConfig {

    @Value("${mongodb.name}")
    private String DB_NAME;

    @Value("${morphia.map.package}")
    private String MAP_PACKAGE;

    @Autowired
    private MongoClient mongo;

    @Bean
    public Datastore datastore() throws ClassNotFoundException {
        Morphia morphia = new Morphia();
        morphia.mapPackage(MAP_PACKAGE);
        return morphia.createDatastore(mongo, DB_NAME);
    }
}
