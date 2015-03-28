package action;

import org.apache.struts2.ServletActionContext;
import pojo.Login;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Author : abhishek
 * Created on 3/15/15.
 */
public class LoginAction extends action.UHAction {

    private String username;
    private String password;
    private String role;
    private String errorMsg = "Invalid Username or Password";

    public String execute() throws Exception {

            System.out.println(username + "   ====   " +  password);
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("logined","true");
            session.setAttribute("context", new Date());

            Login login = new Login();
            login.setUsername(username);
            login.setPassword(password);
            login.setRole(role);

            boolean valid = login.checkLogin(conn);
            if(valid){
                return login.getRole();
            }else{
                return ERROR;
            }
    }

    public String logout() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("logined");
        session.removeAttribute("context");
        errorMsg = "";
        conn.close();
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
