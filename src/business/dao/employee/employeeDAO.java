package business.dao.employee;

import business.dao.AppDAO;
import business.model.Department;
import business.model.Employee;

import java.util.List;
import java.util.Scanner;

public interface employeeDAO extends AppDAO<Employee> {
    public int choiceDepartment(Scanner sc);

    public List<Employee> findEmployeeByNameAndAge(String name, int ageFrom, int ageTo);

    public List<Employee> sortBySalary();
}