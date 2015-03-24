package action;

import org.apache.struts2.ServletActionContext;
import pojo.UserInfo;

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

    public String execute() throws Exception {

            System.out.println(username + "   ====   " + password);
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("logined","true");
            session.setAttribute("context", new Date());

            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setRole(role);
            super.setUserInfo(userInfo);

            if(role.equalsIgnoreCase("student")){
                return "student";
            }

            if(role.equalsIgnoreCase("guest")){
                return "guest";
            }

            if(role.equalsIgnoreCase("staff")){
                return "staff";
            }

        return ERROR;
    }

    public String logout() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("logined");
        session.removeAttribute("context");
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
}
