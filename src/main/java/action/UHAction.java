package action;

import com.opensymphony.xwork2.ActionSupport;
import pojo.UserInfo;

/**
 * Author : abhishek
 * Created on 3/15/15.
 */
public class UHAction extends ActionSupport{

    private String appName = "uhousing";
    private UserInfo userInfo;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
