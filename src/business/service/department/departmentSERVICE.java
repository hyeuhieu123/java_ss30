package business.service.department;

import business.model.Department;
import business.service.AppSERVICE;

import java.util.List;
import java.util.Scanner;

public interface departmentSERVICE extends AppSERVICE<Department> {
    public int selectActiveDepartment(Scanner sc);
    public List<Department> findDepartmentByName(String name);


}
