package action;

import com.opensymphony.xwork2.ActionSupport;
import db.table.ResidentTable;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/15/15.
 */
public class UHAction extends ActionSupport{

    private String appName = "uhousing";
    Connection conn = null;

    public UHAction(){
        try{
            conn = DBAccessor.getConnection();
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

}
