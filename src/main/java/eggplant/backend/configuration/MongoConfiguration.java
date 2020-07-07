package eggplant.backend.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import eggplant.backend.utils.ZonedDateTimeReadConverter;
import eggplant.backend.utils.ZonedDateTimeWriteConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import static java.util.Arrays.asList;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private char[] password;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(
                new ServerAddress(host, port),
                MongoCredential.createCredential(username, database, password),
                MongoClientOptions.builder().build()
                );
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public CustomConversions customConversions() {
        return new MongoCustomConversions(asList(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));
    }
}
