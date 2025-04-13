package business.service.statistict;

import java.util.Map;

public interface statisticSERVICE {
    public Map<String,Integer> compileEmployeeEachDepartment();
    public int countEmployee();
    public  Map<String,Integer> departmentWithMostEmployee();
    public  Map<String,Integer> departmentWithhighestSalary();
}
