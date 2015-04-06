package action;

import db.table.ResidentTable;
import db.table.StaffTable;
import pojo.Resident;
import pojo.Staff;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class ProfileAction extends UHAction {

    private Resident resident;
    private Staff staff;
    private String message;

    public String execute(){

        String username = (String) sessionMap.get("username");
        String role = (String) sessionMap.get("role");
        if(username == null || role == null){
            return ERROR;
        }
        role = role.trim();

        if(role.equalsIgnoreCase("student") || role.equalsIgnoreCase("guest")){
            ResidentTable residentTable = new ResidentTable();
            resident = residentTable.selectOne(conn, username);
        }

        if(role.equalsIgnoreCase("staff")){
            StaffTable staffTable = new StaffTable();
            staff = staffTable.selectOne(conn, username);
        }
        return role;
    }

    public String updateProfile(){

        String username = (String) sessionMap.get("username");
        username = username.trim();
        String role = (String) sessionMap.get("role");
        if(username == null || role == null){
            return ERROR;
        }
        role = role.trim();

        message = "Profile Updated Successfully !!";
        if(role.equalsIgnoreCase("student") || role.equalsIgnoreCase("guest")){
            ResidentTable residentTable = new ResidentTable();
            resident.setResId(username);
            residentTable.update(conn, resident);
        }

        if(role.equalsIgnoreCase("staff")){
            StaffTable staffTable = new StaffTable();
            staff.setStaffNum(username);
        }

        return role;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


}
