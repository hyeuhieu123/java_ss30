package business.service.employee;

import business.model.Employee;
import business.service.AppSERVICE;

import java.util.List;
import java.util.Scanner;

public interface employeeSERVICE extends AppSERVICE<Employee> {
    public int choiceDepartment(Scanner sc);
    public List<Employee> findEmployeeByNameAndAge(String name, int ageFrom, int ageTo);
    public List<Employee> sortBySalary();

}
