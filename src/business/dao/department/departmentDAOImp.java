package business.dao.department;

import business.config.ConnectionDB;
import business.model.Department;
import business.model.EDepartment;
import business.validator.ValidateData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class departmentDAOImp implements departmentDAO {
    private final int  PAGE_SIZE = 5;

    @Override
    public int getTotalPage() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt= conn.prepareCall("SELECT COUNT(id) as total FROM department");
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
    public List<Department> getDataPag(int page) {
        List<Department> list = new ArrayList<>();
        Connection conn = null ;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{call getDataPag(?, ?)}");
            callSt.setInt(1, PAGE_SIZE);
            callSt.setInt(2, (page - 1) * PAGE_SIZE);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDes(rs.getString("description"));
                department.setStatus(EDepartment.valueOf(rs.getString("status")));
                list.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean insert(Department department) {
        Connection conn = null;
        CallableStatement callSt= null;
        try{
            conn = ConnectionDB.getConnection();
            callSt=conn.prepareCall("{call insert_department(?, ?, ?)}");
           callSt.setString(1,department.getName());
           callSt.setString(2,department.getDes());
           callSt.setString(3,department.getStatus().toString());
           callSt.execute();
           return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  false;

    }

    @Override
    public boolean update(Department department) {
        Connection conn  = null;
        CallableStatement callSt= null;
        try {
            conn = ConnectionDB.getConnection();
            callSt=conn.prepareCall("{call update_department(?, ?, ?, ?)}");
            callSt.setInt(1,department.getId());
            callSt.setString(2,department.getName());
            callSt.setString(3,department.getDes());
            callSt.setString(4,department.getStatus().toString());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();

        }

        return  false;
    }

    @Override
    public boolean delete(Department department) {
        Connection conn = null;
        CallableStatement callSt= null;
        try{
            conn = ConnectionDB.getConnection();
            callSt=conn.prepareCall("{call delete_department(?)}");
            callSt.setInt(1,department.getId());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  false;

    }

    @Override
    public Department findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("select * from department where id = ?");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDes(rs.getString("description"));
                department.setStatus(EDepartment.valueOf(rs.getString("status")));
                return department;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public  int selectActiveDepartment(Scanner sc) {
        Connection conn = null;
        CallableStatement callSt= null;
        List<Department> list = new ArrayList<>();
        try{
            conn = ConnectionDB.getConnection();
            callSt=conn.prepareCall("select  * from  department\n" +
                    "where id not in (\n" +
                    "    select department_id from employee\n" +
                    "    where department_id is not null\n" +
                    "    )");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDes(rs.getString("description"));
                department.setStatus(EDepartment.valueOf(rs.getString("status")));
                list.add(department);
            }
            for (Department de : list){
                System.out.println(de);
            }
            int choice = ValidateData.validateInteger("nhap id phong ban can xoa", sc);
//            System.out.println("test "+ list.stream().anyMatch(de->de.getId() == choice));;
            if(list.stream().anyMatch(de->de.getId() == choice)){
                return  choice;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Department> findDepartmentByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> list = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("call finDepartmentByName(?)");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setDes(rs.getString("description"));
                department.setStatus(EDepartment.valueOf(rs.getString("status")));
                list.add(department);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
