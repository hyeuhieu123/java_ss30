package business.service.employee;

import business.dao.employee.employeeDAO;
import business.dao.employee.employeeDAOImp;
import business.model.Employee;

import java.util.List;
import java.util.Scanner;

public class employeeSERVICEImp implements employeeSERVICE{
    private employeeDAO employeeDao;
    public employeeSERVICEImp(){
        employeeDao = new employeeDAOImp();
    }
    @Override
    public int getTotalPage() {
        return employeeDao.getTotalPage();
    }

    @Override
    public List<Employee> getDataPag(int page) {
        return employeeDao.getDataPag(page);
    }

    @Override
    public boolean insert(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public int choiceDepartment(Scanner sc) {
        return employeeDao.choiceDepartment(sc);
    }

    @Override
    public List<Employee> findEmployeeByNameAndAge(String name, int ageFrom, int ageTo) {
        return employeeDao.findEmployeeByNameAndAge(name, ageFrom, ageTo);
    }

    @Override
    public List<Employee> sortBySalary() {
        return employeeDao.sortBySalary();
    }
}
