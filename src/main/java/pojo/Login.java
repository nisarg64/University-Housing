package pojo;

import db.table.LoginTable;

import java.sql.Connection;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class Login {

    String username;
    String password;
    String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin(Connection conn) {

        try{
            LoginTable loginTable = new LoginTable();
            return loginTable.checkLogin(this, conn);

        }catch (Exception ex){
            System.err.println("Error Occurred During Login " + ex.getMessage());
        }
        return false;
    }
}
