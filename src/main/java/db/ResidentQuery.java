package db;

/**
 * Author : abhishek
 * Created on 3/12/15.
 */
public class ResidentQuery {


    public void createResidentTable(){

        String query = " CREATE TABLE RESIDENT (" +
                       " res_id CHAR(10), " +
                       " fname VARCHAR(32), " +
                       " lname VARCHAR(32) NOT NULL, " +
                       " sex CHAR(1), " +
                       " dob DATE, " +
                       " address_street VARCHAR(100), " +
                       " address_city VARCHAR(32), " +
                       " address_postcode VARCHAR(10), " +
                       " nationality VARCHAR(32), " +
                       " primary_phone VARCHAR(12), " +
                       " secondary_phone VARCHAR(12), " +
                       " smoker CHAR(2), " +
                       " comments VARCHAR(32), " +
                       " spl_needs VARCHAR(32), " +
                       " PRIMARY KEY (res_id) " +
                       ")";

    }

    public void createStudentTable(){

        String query = " CREATE TABLE STUDENT (" +
                " student_id CHAR(10), " +
                " category VARCHAR(6), " +
                " status VARCHAR(10), " +
                " course VARCHAR(32) " +
                " PRIMARY KEY (student_id) " +
                ")";
    }

    public void createGuestTable(){

        String query = " CREATE TABLE GUEST(" +
                " approval_id CHAR(10), " +
                " status VARCHAR(10), " +
                " PRIMARY KEY (approval_id) " +
                ")";
    }


    public void createFamilyTable(){

        String query = " CREATE TABLE FAMILY(" +
                " name VARCHAR(32), " +
                " dob DATE, " +
                " relation VARCHAR(12), " +
                " student_id CHAR(10), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";

    }

    public void createKinTable(){

        String query = " CREATE TABLE KIN(" +
                " name VARCHAR(32), " +
                " relation VARCHAR(12), " +
                " address_street VARCHAR(100), " +
                " address_city VARCHAR(32), " +
                " address_postcode VARCHAR(10), " +
                " contact_no VARCHAR(12), " +
                " student_id CHAR(10), " +
                " PRIMARY KEY (name, student_id), " +
                " FOREIGN KEY (student_id) REFERENCES STUDENT " +
                " ON DELETE CASCADE " +
                ")";
    }


}
