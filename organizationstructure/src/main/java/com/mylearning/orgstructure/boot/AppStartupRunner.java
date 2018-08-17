package com.mylearning.orgstructure.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private OrganizationRepository organizationRepository;

    private Map<Long, EmployeeNode> employeeMap = new HashMap<>();
    private EmployeeNode root;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Your application started, loading json data...with org hierarchy");
        List<Employee> employees = dataImporter.fromJson();
        employees.forEach(e -> {
            organizationRepository.createEmployee(e);
            EmployeeNode employeeNode = new EmployeeNode(e.getId(), e.getCityName(), e.getSalary(), e.getFirstName(),
                    e.getSecondName(), e.getManagerId());
            employeeMap.put(e.getId(), employeeNode);
            if (e.getManagerId() == 0) {
                root = employeeNode;
            }
        });
        buildHierarchyTree(root);
        printHierarchyTree(root, 0);
    }

    private List<EmployeeNode> getSubordinatesById(long rid) {
        List<EmployeeNode> subordinates = new ArrayList<>();
        for (EmployeeNode e : employeeMap.values()) {
            if (e.getManagerId() == rid) {
                subordinates.add(e);
            }
        }
        return subordinates;
    }

    private void buildHierarchyTree(EmployeeNode localRoot) {
        EmployeeNode employee = localRoot;
        List<EmployeeNode> subordinates = getSubordinatesById(employee.getId());
        employee.setSubordinates(subordinates);
        if (subordinates.size() == 0) {
            return;
        }

        for (EmployeeNode e : subordinates) {
            buildHierarchyTree(e);
        }
    }

    private void printHierarchyTree(EmployeeNode localRoot, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(localRoot.getFirstName());

        List<EmployeeNode> subordinates = localRoot.getSubordinates();
        System.out.print(" ");
        for (EmployeeNode e : subordinates) {
            printHierarchyTree(e, level + 1);
        }
    }
}
