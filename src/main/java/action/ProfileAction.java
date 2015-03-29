package action;

import db.view.GuestView;
import db.view.StudentView;
import pojo.Guest;
import pojo.Student;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class ProfileAction extends UHAction {

    private Student student;
    private Guest guest;

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

        if(role == "guest"){
            GuestView guestView = new GuestView();
            guest = guestView.selectOne(conn, username);
            return "guest";
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
}
