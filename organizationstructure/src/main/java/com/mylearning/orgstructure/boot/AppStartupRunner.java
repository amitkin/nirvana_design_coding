package com.mylearning.orgstructure.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
    private EmployeeNode ceo;

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
                ceo = employeeNode;
            }
        });
        buildHierarchyTree(ceo);
        printHierarchyTree(ceo, 0);
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

    public EmployeeNode closestCommonManager(final EmployeeNode first, final EmployeeNode second) {
        if (ceo == null || first == null || second == null)
            return null;
        if ((!isPresent(ceo, first)) && isPresent(ceo, second))
            return null;
        final Queue<EmployeeNode> queue = new LinkedList<>();
        queue.offer(ceo);
        EmployeeNode closestKnownManager = null;
        while (!queue.isEmpty()) {
            EmployeeNode employee = queue.poll();
            if (isPresent(employee, first) && isPresent(employee, second)) {
                closestKnownManager = employee;
                for (EmployeeNode em : employee.subordinates) {
                    queue.offer(em);
                }
            }
        }
        return closestKnownManager;
    }

    private boolean isPresent(final EmployeeNode manager, final EmployeeNode employee) {
        if (manager == null)
            return false;
        if (manager.id.equals(employee.id))
            return true;
        if (manager.subordinates == null)
            return false;

        boolean covers = false;
        for (EmployeeNode em : manager.subordinates) {
            covers = covers || isPresent(em, employee);
        }
        return covers;
    }

    public EmployeeNode getEmployeeNode(long id){
        return employeeMap.getOrDefault(id, null);
    }
}
