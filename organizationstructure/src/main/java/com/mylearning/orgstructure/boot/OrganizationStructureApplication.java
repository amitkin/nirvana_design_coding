package com.mylearning.orgstructure.boot;

import com.mylearning.orgstructure.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import(value = {AppConfig.class})
public class OrganizationStructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationStructureApplication.class, args);
        log.info("Started Organization Structure Application" );
    }
}
