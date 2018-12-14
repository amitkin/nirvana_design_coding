package com.mylearning.orgstructure.config;

import javax.sql.DataSource;

import com.mylearning.orgstructure.data.DataImporter;
import com.mylearning.orgstructure.data.JsonDataImporter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = { "com.mylearning.orgstructure" })
public class AppConfig {

    @Bean
    @Qualifier("jsonDataImporter")
    public DataImporter jsonDataImporter(){
        return new JsonDataImporter();
    }

    @Bean
    @Qualifier("h2JdbcTemplate")
    public JdbcTemplate h2JdbcTemplate() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
        return new JdbcTemplate(dataSource);
    }
}
