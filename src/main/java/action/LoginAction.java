package action;

import db.table.LoginTable;

/**
 * Author : abhishek
 * Created on 3/15/15.
 */
public class LoginAction extends UHAction {

    private String username;
    private String password;
    private String role;
    private String errorMsg = "Login Incorrect";

    public String execute(){
        System.out.println(username + " ------- " + password );
        // check if the userName is already stored in the session
        if (sessionMap.containsKey("username")) {
            return (String) sessionMap.get("role");
        }
        System.out.println(sessionMap);
            if (username != null && role != null){
                login.setUsername(username);
                login.setPassword(password);
                login.setRole(role);

                LoginTable loginTable = new LoginTable();
                boolean valid = loginTable.checkLogin(conn, login);
                System.out.println(" VALID ------- " + valid);

                if(valid){
                    sessionMap.put("username", login.getUsername());
                    sessionMap.put("role", login.getRole());
                    return login.getRole();
                }
            }
        return ERROR;
    }

    public String logout() {

        if(sessionMap.containsKey("username")){
            sessionMap.remove("username");
            sessionMap.remove("role");
        }
        errorMsg = "";
        try{
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
