package business.dao.employee;

import business.config.ConnectionDB;
import business.dao.department.departmentDAO;
import business.dao.department.departmentDAOImp;
import business.model.*;
import business.validator.ValidateData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class employeeDAOImp implements employeeDAO{
    private final int  PAGE_SIZE = 10;
    private departmentDAO departmentDAOIMP;
    public employeeDAOImp(){
        departmentDAOIMP = new departmentDAOImp();
    }
    @Override
    public int getTotalPage() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt= conn.prepareCall("SELECT COUNT(id) as total FROM employee");
            ResultSet rs =callSt.executeQuery();
            if(rs.next()){
                int total = rs.getInt("total");
                return (int) Math.ceil((double) total / PAGE_SIZE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Employee> getDataPag(int page) {

        List<Employee> list = new ArrayList<>();
        Connection conn = null ;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call getDataPagEmp(?, ?)}");
            callSt.setInt(1, PAGE_SIZE);
            callSt.setInt(2, (page - 1) * PAGE_SIZE);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setSex(Gender.valueOf(rs.getString("sex")));
                employee.setBod(rs.getDate("bod").toLocalDate());
                Department department = departmentDAOIMP.findById(rs.getInt("department_id"));
                employee.setDepartment(department);
                employee.setSalary(rs.getDouble("salary"));
                employee.setStatus(EEmployee.valueOf(rs.getString("status")));
                list.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean insert(Employee employee) {
        Connection conn = null;
        CallableStatement callSt= null;
        try{
            conn = ConnectionDB.getConnection();
            callSt=conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getName());
            callSt.setString(2, employee.getEmail());
            callSt.setString(3, employee.getSex().toString());
            callSt.setString(4,employee.getBod().toString() );
            callSt.setInt(5, employee.getDepartment().getId());
            callSt.setDouble(6, employee.getSalary());
            callSt.setString(7, employee.getStatus().toString());
           callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call updateEmployee(?,?,?,?,?,?,?,?)}");
            callSt.setInt(1, employee.getId());
            callSt.setString(2, employee.getName());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getSex().toString());
            callSt.setString(5, employee.getBod().toString());
            callSt.setInt(6, employee.getDepartment().getId());
            callSt.setDouble(7, employee.getSalary());
            callSt.setString(8, employee.getStatus().toString());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }

    @Override
    public Employee findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("SELECT * FROM employee WHERE id = ?");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setSex(Gender.valueOf(rs.getString("sex")));
                employee.setBod(rs.getDate("bod").toLocalDate());
                Department department = departmentDAOIMP.findById(rs.getInt("department_id"));
                employee.setDepartment(department);
                employee.setSalary(rs.getDouble("salary"));
                employee.setStatus(EEmployee.valueOf(rs.getString("status")));
                return employee;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int choiceDepartment(Scanner sc) {
        Connection conn = null  ;
        CallableStatement callSt = null;
        List<Department> list = null;

        try{
         conn = ConnectionDB.getConnection();
         callSt=conn.prepareCall("select * from department where status != 'inactive'");
            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDes(rs.getString("description"));
                department.setStatus(EDepartment.valueOf(rs.getString("status")));
                list.add(department);
            }
            list.stream().forEach(System.out::println);

            while (true){
                int choice = ValidateData.validateInteger("nhap id phong ban", sc);
                if(list.stream().anyMatch(de->de.getId() == choice)){
                    return choice;
                }
                System.out.println("khong tim thay phong ban");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> findEmployeeByNameAndAge(String name, int ageFrom, int ageTo) {
        Connection conn= null;
        CallableStatement callSt = null;
        List<Employee> list = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call findEmployeeByNameAndAge(?,?,?)}");
            callSt.setString(1, name);
            callSt.setInt(2,ageFrom);
            callSt.setInt(3, ageTo);
            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setSex(Gender.valueOf(rs.getString("sex")));
                employee.setBod(rs.getDate("bod").toLocalDate());
                Department department = departmentDAOIMP.findById(rs.getInt("department_id"));
                employee.setDepartment(department);
                employee.setSalary(rs.getDouble("salary"));
                employee.setStatus(EEmployee.valueOf(rs.getString("status")));
                list.add(employee);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> sortBySalary() {
        Connection conn= null;
        CallableStatement callSt = null;
        List<Employee> list = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("select * from employee\n" +
                    "order by salary desc,name asc");

            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setSex(Gender.valueOf(rs.getString("sex")));
                employee.setBod(rs.getDate("bod").toLocalDate());
                Department department = departmentDAOIMP.findById(rs.getInt("department_id"));
                employee.setDepartment(department);
                employee.setSalary(rs.getDouble("salary"));
                employee.setStatus(EEmployee.valueOf(rs.getString("status")));
                list.add(employee);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
