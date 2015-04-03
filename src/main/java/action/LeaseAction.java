package action;

import db.view.LeaseView;
import pojo.Lease;

/**
 * User: Nikhil
 * Date: 02-04-15
 */
public class LeaseAction extends UHAction {

    private Lease lease;

    public LeaseAction() {
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    @Override
    public String execute() throws Exception {
        LeaseView view = new LeaseView();

        String username = (String) sessionMap.get("username");
        if (username == null) {
            return ERROR;
        }
        lease = view.viewCurrentLease(conn, username);
        return SUCCESS;
    }

    public String viewLease() {
        LeaseView view = new LeaseView();

        String username = (String) sessionMap.get("username");
        if (username == null) {
            return ERROR;
        }
        lease = view.viewCurrentLease(conn, username);
        return SUCCESS;
    }

}
