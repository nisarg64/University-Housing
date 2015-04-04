package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import pojo.Login;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Author : abhishek
 * Created on 3/15/15.
 */
public class UHAction extends ActionSupport implements SessionAware{

    private String appName = "uhousing";
    Map<String, Object> sessionMap;
    Login login;
    Connection conn = null;

    public UHAction(){
        try{
            conn = DBAccessor.getConnection();
            login = new Login();

        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public void setSession(Map<String, Object> session) {
       this.sessionMap = session;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String execute(){
        return SUCCESS;
    }
}
