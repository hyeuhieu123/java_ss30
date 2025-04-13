package business.dao.login;

import business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO {
    public boolean login(String email, String password) {
        Connection conn = null;
        CallableStatement callSt=null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("select * from admin where email = ? and password = ?");
            callSt.setString(1, email);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
             return  true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
