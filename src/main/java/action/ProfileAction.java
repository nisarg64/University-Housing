package action;

import db.view.GuestView;
import db.view.StudentView;
import pojo.Guest;
import pojo.Staff;
import pojo.Student;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class ProfileAction extends UHAction {

    private Student student;
    private Guest guest;
    private Staff staff;
    private String message;

    public String execute(){

        String username = (String) sessionMap.get("username");
        String role = (String) sessionMap.get("role");
        if(username == null || role == null){
            return ERROR;
        }
        role = role.trim();

        if(role.equalsIgnoreCase("student")){
            StudentView studentView = new StudentView();
            student = studentView.selectOne(conn, username);
            return "student";
        }

        if(role.equalsIgnoreCase("guest")){
            GuestView guestView = new GuestView();
            guest = guestView.selectOne(conn, username);
            return "guest";
        }
        return "student";
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
        if(role.equalsIgnoreCase("student")){
            StudentView studentView = new StudentView();
            student.setStudentId(username);
            studentView.update(conn, student);
            return "student";
        }

        if(role.equalsIgnoreCase("guest")){
            GuestView guestView = new GuestView();
            guest.setApprovalId(username);
            guestView.update(conn, guest);
            return "guest";
        }

        if(role.equalsIgnoreCase("staff")){
            staff.setStaffId(username);
            return "staff";
        }

        return "student";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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

    @Override
    public String toString() {
        return "ProfileAction{" +
                "student=" + student +
                ", guest=" + guest +
                ", message='" + message + '\'' +
                '}';
    }
}
