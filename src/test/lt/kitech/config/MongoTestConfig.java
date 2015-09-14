package lt.kitech.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.UUID;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class MongoTestConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public String getDatabaseName() {
        return "test-" + UUID.randomUUID();
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient(
                environment.getRequiredProperty("mongodb.test.host"),
                Integer.parseInt(environment.getRequiredProperty("mongodb.test.port")));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
