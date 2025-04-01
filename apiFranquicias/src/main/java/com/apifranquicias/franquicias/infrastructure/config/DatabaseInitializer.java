package com.apifranquicias.franquicias.infrastructure.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DatabaseInitializer {

    private final DatabaseClient databaseClient;

    public DatabaseInitializer(DatabaseClient databaseClient){
        this.databaseClient = databaseClient;
    }

    @PostConstruct
    public void initializeDatabase() {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(new ClassPathResource("schema.sql").getURI())));
            databaseClient.sql(sql)
                    .fetch()
                    .rowsUpdated()
                    .subscribe();

            System.out.println("✅ Base de datos inicializada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
