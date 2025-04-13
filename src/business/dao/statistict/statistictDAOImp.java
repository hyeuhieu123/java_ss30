package business.dao.statistict;

import business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class statistictDAOImp implements statistictDAO{
    @Override
    public Map<String, Integer> compileEmployeeEachDepartment() {
        Connection conn = null;
        CallableStatement callableStatement = null;
        Map<String,Integer> map = null;
        try {
            conn = ConnectionDB.getConnection();
            callableStatement= conn.prepareCall("{call compileEmployeeEachDepartment()}");
            ResultSet rs = callableStatement.executeQuery();
           map = new HashMap<>();
            while (rs.next()){
                String name = rs.getString("name");
                int count = rs.getInt("count");
                map.put(name,count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public int countEmployee() {
        Connection conn = null;
        CallableStatement callableStatement = null;

        try {
            conn = ConnectionDB.getConnection();
            callableStatement= conn.prepareCall("{call countEmployee()}");
            ResultSet rs = callableStatement.executeQuery();

            if (rs.next()){
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return 0;
    }

    @Override
    public Map<String, Integer> departmentWithMostEmployee() {
        Connection conn = null;
        CallableStatement callableStatement = null;
        Map<String,Integer> map = null;
        try {
            conn = ConnectionDB.getConnection();
            callableStatement= conn.prepareCall("{call departmentWithMostEmployee()}");
            ResultSet rs = callableStatement.executeQuery();
            map = new HashMap<>();
            while (rs.next()){
                String name = rs.getString("name");
                int count = rs.getInt("total");
                map.put(name,count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public Map<String, Integer> departmentWithhighestSalary() {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = ConnectionDB.getConnection();
            callableStatement= conn.prepareCall("{call departmentWithhighestSalary()}");
            ResultSet rs = callableStatement.executeQuery();
            Map<String,Integer> map = new HashMap<>();
            while (rs.next()){
                String name = rs.getString("name");
                int count = rs.getInt("total");
                map.put(name,count);
            }
            return map;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
