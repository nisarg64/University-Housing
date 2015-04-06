package db.table;

import pojo.Resident;
import util.DBAccessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : abhishek
 * Created on 3/26/15.
 */
public class ResidentTable extends Table {

    @Override
    public String getTableName() {
        return "RESIDENT";
    }

    @Override
    public void createTable(Connection conn) throws SQLException{

        String query = " CREATE TABLE " + getTableName() + " (" +
                " res_id " + ColumnTypes.ID_TYPE + " ," +
                " fname " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " ," +
                " lname " + ColumnTypes.VARCHAR2_SIZE_50_TYPE + " NOT NULL ," +
                " sex " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " dob " + ColumnTypes.DATE_TYPE + " ," +
                " address_street " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " ," +
                " address_city " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " address_postcode " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                " address_country " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " nationality " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " primary_phone " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " alternate_phone " + ColumnTypes.VARCHAR2_SIZE_20_TYPE + " ," +
                " smoker " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                " comments " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " ," +
                " spl_needs  " + ColumnTypes.VARCHAR2_SIZE_200_TYPE + " ," +
                " category  " +   ColumnTypes.VARCHAR2_SIZE_20_TYPE + " NOT NULL ," +
                " status  " + ColumnTypes.VARCHAR2_SIZE_10_TYPE + " ," +
                " course  " +   ColumnTypes.VARCHAR2_SIZE_50_TYPE + " NOT NULL ," +
                " family_student  " +   ColumnTypes.VARCHAR2_SIZE_10_TYPE + " NOT NULL ," +
                " PRIMARY KEY (res_id) " +
                ")";
        DBAccessor.executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {

        List<String> queries = new LinkedList<>();

        // residents
        String query1 = "INSERT INTO " + getTableName() + " VALUES('100540001', 'Harry', 'Potter', 'Male', '21-Jul-1991','31 B, Privet Drive'," +
                "'London','27605','England','British','9189327078','9176326078','NO','','None', 'Freshman', 'Placed','Defence Against Dark Arts','NO')";

        String query2 = "INSERT INTO " + getTableName() + " VALUES('100540002', 'Draco', 'Malfoy', 'Male', '05-Jun-1990','31 B, Malfoy Drive'," +
                "'New York','27506', 'USA', 'American','9189327067','N/A','YES','','None', 'Freshman', 'Placed','Muggle Studies','NO')";

        String query3 = "INSERT INTO " + getTableName() + " VALUES('100540003', 'Ron', 'Weasley', 'Male', '01-Mar-1986','31 B, Weasley Road'," +
                "'Rome','27456', 'Italy', 'Italian','9189567067','N/A','NO','','None', 'Graduate', 'Placed','Care of Magical Creatures','NO')";

        String query4 = "INSERT INTO " + getTableName() + " VALUES('100540004', 'Hermione', 'Granger', 'Female', '19-Sep-1986','32 A, Granger Road'," +
                "'Edinburg','25556', 'Scotland', 'Italian','9189568567','N/A','NO','','None', 'Graduate', 'Placed','Muggle Studies','NO')";

        String query5 = "INSERT INTO " + getTableName() + " VALUES('100540005', 'Fred', 'Weasley', 'Male', '20-Oct-1982','31 B, Weasley Road'," +
                "'Rome','27456', 'Italy', 'Italian','9133368567','9133368511','NO','','None', 'Senior', 'Placed','Muggle Studies','NO')";

        String query6 = "INSERT INTO " + getTableName() + " VALUES('100540006', 'George', 'Weasley', 'Male', '20-Oct-1982','31 B, Weasley Road'," +
                "'Rome','27456', 'Italy', 'Italian','9133368568','N/A','NO','','None' , 'Senior', 'Placed','Muggle Studies','NO')";

        String query7 = "INSERT INTO " + getTableName() + " VALUES('100540007', 'Bill', 'Weasley', 'Male', '29-Nov-1982','32 A, William Road'," +
                "'Brussels','2535', 'Belgium', 'Belgian','9198568567','N/A','NO','','Difficulty in walking' , 'Graduate', 'Placed','Potions','YES')";

        String query8 = "INSERT INTO " + getTableName() + " VALUES('100540008', 'Vernon', 'Dursley', 'Male', '29-Nov-1975','32 A, Dursley Road'," +
                "'Raleigh','27606', 'USA', 'American','9198954357','9198954786','YES','','None' , 'Graduate', 'Placed','Muggle Studies','YES')";

        //Guests

        String query9 = "INSERT INTO " + getTableName() + " VALUES('200540001', 'Viktor', 'Krum', 'Male', '29-Nov-1982','32 A, Krum Road'," +
                "'Sofia','2221', 'Bulgaria', 'Bulgarian','9198333567','9198332233','YES','','None' , 'Visitor', 'Placed','Coaching - Triwizard Cup','NO')";

        String query10 = "INSERT INTO " + getTableName() + " VALUES('200540002', 'Olympe', 'Maxime', 'Female', '19-May-1970','32 A, Maxime Road'," +
                "'Paris','2331', 'France', 'French','9197773567','N/A','YES','','None' , 'Visitor', 'Placed','Guest Lecturer - Triwizard Cup','NO')";


        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
        queries.add(query6);
        queries.add(query7);
        queries.add(query8);
        queries.add(query9);
        queries.add(query10);

        DBAccessor.executeBatchQuery(conn, queries);
    }

    public List<Resident> selectAll(Connection conn) throws SQLException{

        String query = "SELECT * from " + getTableName();
        List<Resident> residents = new LinkedList<>();
        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                Resident resident = new Resident();
                resident.setResId(resultSet.getString("res_id"));
                resident.setFname(resultSet.getString("fname"));
                resident.setLname(resultSet.getString("lname"));

                System.out.println(resident);
                residents.add(resident);
            }
        }
        return residents;
    }

    public Resident selectOne(Connection conn, String residentId)  {

        Resident resident = null;
        String query = "SELECT * FROM " + getTableName() + " where res_id = '" + residentId + "'";

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                resident = new Resident();
                resident.setResId(resultSet.getString("res_id"));
                resident.setFname(resultSet.getString("fname"));
                resident.setLname(resultSet.getString("lname"));
                resident.setAddrStreet(resultSet.getString("address_street"));
                resident.setAddrCity(resultSet.getString("address_city"));
                resident.setAddrCountry(resultSet.getString("address_country"));
                resident.setPostalCode(resultSet.getString("address_postcode"));
                resident.setCategory(resultSet.getString("category"));
                resident.setStatus(resultSet.getString("status"));
                resident.setDob(resultSet.getDate("dob").toString());
                resident.setCourse(resultSet.getString("course"));
                resident.setGender(resultSet.getString("sex"));
                resident.setPrimaryPhone(resultSet.getString("primary_phone"));
                resident.setAlternatePhone(resultSet.getString("alternate_phone"));
                resident.setSpclNeeds(resultSet.getString("spl_needs"));
                resident.setComments(resultSet.getString("comments"));
                resident.setIsSmoker(resultSet.getString("smoker"));

            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During resident Profile View " + ex.getMessage());
        }
        return resident;
    }

    public void update(Connection conn, Resident resident) {

        String query = "UPDATE RESIDENT SET " +
                "fname = '" + resident.getFname()+ "', " +
                "lname = '" + resident.getLname() + "', " +
                "SEX = '" + resident.getGender() + "', " +
                "ADDRESS_STREET = '" + resident.getAddrStreet() + "', " +
                "ADDRESS_CITY = '" + resident.getAddrCity() + "', " +
                "ADDRESS_POSTCODE = '" + resident.getPostalCode() + "', " +
                "PRIMARY_PHONE = '" + resident.getPrimaryPhone() + "', " +
                "ALTERNATE_PHONE = '" + resident.getAlternatePhone() + "', " +
                "SPL_NEEDS = '" + resident.getSpclNeeds() + "', " +
                "CATEGORY = '" + resident.getCategory() +"', " +
                "COURSE = '" + resident.getCourse() + "', " +
                "NATIONALITY = '" + resident.getNationality() + "', " +
                "SMOKER = '" + resident.getIsSmoker() + "', " +
                "COMMENTS = '" + resident.getComments() + "', " +
                "STATUS = '" + resident.getStatus() + "', " +

                "WHERE RES_ID = '" + resident.getResId() + "'";

        try{
            DBAccessor.executeQuery(conn, query);
        }catch (SQLException ex){
            System.err.println("Error Occurred During  profile Update :    " + resident + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
