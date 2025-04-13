package business.dao.statistict;

import java.util.Map;

public interface statistictDAO {
    public Map<String,Integer> compileEmployeeEachDepartment();
    public int countEmployee();
    public  Map<String,Integer> departmentWithMostEmployee();
    public  Map<String,Integer> departmentWithhighestSalary();

}
