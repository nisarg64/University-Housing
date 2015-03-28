package db;

/**
 * Author : abhishek
 * Created on 3/12/15.
 */
public class HousingQuery {

    public void createResidentHallTable(){

        String query = " CREATE TABLE RESIDENT_HALL (" +
                " hall_id CHAR(5), " +
                " name VARCHAR(32), " +
                " address VARCHAR(100), " +
                " manager VARCHAR(32), " +
                " tel_number VARCHAR(12), " +
                " student_category VARCHAR(6), " +
                " PRIMARY KEY (hall_id) " +
                ")";
    }

    public void createHallRoomTable(){

        String query = " CREATE TABLE HALL_ROOM (" +
                " place_num CHAR(5), " +
                " room_num VARCHAR(5), " +
                " monthly_rent INTEGER, " +
                " PRIMARY KEY (place_num) " +
                ")";
    }

    public void createApartmentTable(){

        String query = " CREATE TABLE APARTMENT(" +
                " apartment_no CHAR(5), " +
                " address VARCHAR(32), " +
                " PRIMARY KEY (apartment_no) " +
                ")";
    }

    public void createGeneralAptTable(){

        String query = " CREATE TABLE GENERAL_APT(" +
                " g_apartment_no CHAR(5), " +
                " num_of_bedrooms INTEGER, " +
                " num_of_bathrooms INTEGER, " +
                " PRIMARY KEY (g_apartment_no) " +
                ")";

    }

    public void createAptRoomTable(){

        String query = " CREATE TABLE APT_ROOM(" +
                " place_num CHAR(5), " +
                " room_num VARCHAR(5), " +
                " monthly_rent INTEGER, " +
                " PRIMARY KEY (place_num) " +
                ")";

    }

    public void createFamilyAptTable(){

        String query = " CREATE TABLE FAMILY_APT(" +
                " f_apartment_no CHAR(5), " +
                " num_of_bedrooms INTEGER, " +
                " monthly_rent INTEGER, " +
                " PRIMARY KEY (f_apartment_no) " +
                ")";
    }

    public void createPrivateAccoTable(){

        String query = " CREATE TABLE PRIVATE_ACCO(" +
                " student_id CHAR(10), " +
                " student_category VARCHAR(6), " +
                " address VARCHAR(6), " +
                " phone_num VARCHAR(12), " +
                " PRIMARY KEY (student_id) " +
                ")";
    }


}
