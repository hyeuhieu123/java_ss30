package business.service.statistict;

import business.dao.statistict.statistictDAO;
import business.dao.statistict.statistictDAOImp;

import java.util.Map;

public class statisticSERVICEImp implements statisticSERVICE{
    private statistictDAO statistictDao;
    public statisticSERVICEImp() {
        this.statistictDao = new statistictDAOImp();
    }
    @Override
    public Map<String, Integer> compileEmployeeEachDepartment() {
        return statistictDao.compileEmployeeEachDepartment();
    }

    @Override
    public int countEmployee() {
        return statistictDao.countEmployee();
    }

    @Override
    public Map<String, Integer> departmentWithMostEmployee() {
        return statistictDao.departmentWithMostEmployee();
    }

    @Override
    public Map<String, Integer> departmentWithhighestSalary() {
        return statistictDao.departmentWithhighestSalary();
    }
}
