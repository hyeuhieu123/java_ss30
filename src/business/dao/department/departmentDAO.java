package business.dao.department;

import business.dao.AppDAO;
import business.model.Department;

import java.util.List;
import java.util.Scanner;

public interface departmentDAO extends AppDAO<Department> {
    public int selectActiveDepartment(Scanner sc );
    public List<Department> findDepartmentByName(String name);
}
