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
                student.setName(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                student.setAddrStreet(resultSet.getString("address_street"));
                student.setAddrCity(resultSet.getString("address_city"));
                student.setPostalCode(resultSet.getString("address_postcode"));
                student.setCategory(resultSet.getString("category"));
                student.setDob(resultSet.getDate("dob").toString());
                student.setCourse(resultSet.getString("course"));
                student.setGender(resultSet.getString("sex"));
                student.setPrimaryPhone(resultSet.getString("primary_phone"));
                student.setSpclNeeds(resultSet.getString("spl_needs"));

                System.out.println(student);

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During Login " + ex.getMessage());
        }
        return student;
    }


}
