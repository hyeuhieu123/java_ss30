package business.service.department;

import business.config.ConnectionDB;
import business.dao.department.departmentDAO;
import business.dao.department.departmentDAOImp;
import business.model.Department;
import business.model.EDepartment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class departmentSERVICEImp implements departmentSERVICE {
    private departmentDAO departmentDao;

    public departmentSERVICEImp(){
        departmentDao = new departmentDAOImp();
    }


    @Override
    public int getTotalPage() {
        return departmentDao.getTotalPage();
    }

    @Override
    public List<Department> getDataPag(int page) {
        return departmentDao.getDataPag(page);
    }

    @Override
    public boolean insert(Department department) {
        return departmentDao.insert(department);
    }

    @Override
    public boolean update(Department department) {
        return departmentDao.update(department);
    }

    @Override
    public boolean delete(Department department) {

        return departmentDao.delete(department);
    }

    @Override
    public Department findById(int id) {
    return departmentDao.findById(id);
    }

    @Override
    public int selectActiveDepartment(Scanner sc) {
        return departmentDao.selectActiveDepartment(sc);
    }

    @Override
    public List<Department> findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }
}
