package com.mylearning.orgstructure.boot;

import java.util.List;

import com.mylearning.orgstructure.data.DataImporter;
import com.mylearning.orgstructure.model.Employee;
import com.mylearning.orgstructure.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("jsonDataImporter")
    private DataImporter dataImporter;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Your application started, loading json data...");
        List<Employee> employees = dataImporter.fromJson();
        employees.forEach(employee -> {
            organizationRepository.createEmployee(employee);
        });
        log.info("Loading data complete!!");
    }
}
