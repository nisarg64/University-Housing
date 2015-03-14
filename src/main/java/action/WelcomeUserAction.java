package action;

/**
 * Author : abhishek
 * Created on 3/11/15.
 */
public class WelcomeUserAction {

    private String username;
    private String errorMsg;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // all struts logic here
    public String execute() {


        //if invalids set ErrorMsg("Invalid Credintskd);
        // check in database
        return "SUCCESS";

    }
}
