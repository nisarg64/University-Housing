package db.view;

import pojo.Student;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : abhishek
 * Created on 3/28/15.
 */
public class StudentView extends View{
    @Override
    public String getViewName() {
        return "STUDENT_VIEW";
    }

    @Override
    public void createView(Connection conn) throws SQLException {

        String query = "CREATE VIEW " + getViewName() + " as " +
                       " SELECT  " +
                       " s.student_id, s.category, s.course, " +
                       " r.fname, r.lname, r.dob, r.sex, r.address_street," +
                       " r.address_city, r.address_postcode, " +
                       " r.primary_phone, r.spl_needs "+
                       " FROM   RESIDENT r " +
                       " join " +
                       " STUDENT s " +
                       " on (s.student_id = r.res_id) ";
        DBAccessor.executeQuery(conn, query);
    }

    public Student selectOne(Connection conn, String studentId)  {

        Student student = null;
        String query = "SELECT * FROM " + getViewName() + " where student_id = '" + studentId + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setFname(resultSet.getString("fname"));
                student.setLname(resultSet.getString("lname"));
                student.setAddrStreet(resultSet.getString("address_street"));
                student.setAddrCity(resultSet.getString("address_city"));
                student.setPostalCode(resultSet.getString("address_postcode"));
                student.setCategory(resultSet.getString("category"));
                student.setDob(resultSet.getDate("dob").toString());
                student.setCourse(resultSet.getString("course"));
                student.setGender(resultSet.getString("sex"));
                student.setPrimaryPhone(resultSet.getString("primary_phone"));
                student.setSpclNeeds(resultSet.getString("spl_needs"));

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Student Profile View " + ex.getMessage());
        }
        return student;
    }


    public void update(Connection conn, Student student) {

        String query1 = "UPDATE RESIDENT SET " +
                        "fname = '" + student.getFname()+ "', " +
                        "lname = '" + student.getLname() + "', " +
                        "SEX = '" + student.getGender() + "', " +
                        "ADDRESS_STREET = '" + student.getAddrStreet() + "', " +
                        "ADDRESS_CITY = '" + student.getAddrCity() + "', " +
                        "ADDRESS_POSTCODE = '" + student.getPostalCode() + "', " +
                        "PRIMARY_PHONE = '" + student.getPrimaryPhone() + "', " +
                        "SPL_NEEDS = '" + student.getSpclNeeds() + "' " +
                        "WHERE RES_ID = '" + student.getStudentId() + "'";

        String query2 = "UPDATE STUDENT SET " +
                        " category = '" + student.getCategory() +"', " +
                        " course = '" + student.getCourse() + "' " +
                        " WHERE STUDENT_ID = '" + student.getStudentId() + "'";

        try{
             DBAccessor.executeQuery(conn, query1);
             DBAccessor.executeQuery(conn, query2);
        }catch (SQLException ex){
             System.err.println("Error Occurred During  profile Update :    " + student + ex.getMessage());
             ex.printStackTrace();
        }
    }
}
