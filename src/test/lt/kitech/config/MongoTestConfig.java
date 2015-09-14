package lt.kitech.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.UUID;

/**
 * Created by Danielius Kibartas on 2015-09-11.
 */
@Configuration
public class MongoTestConfig extends AbstractMongoConfiguration {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    @Override
    public String getDatabaseName() {
        return "test-" + UUID.randomUUID();
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient mongo = new MongoClient(HOST, PORT);
        return mongo;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
