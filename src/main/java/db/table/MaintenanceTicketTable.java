package db.table;



import pojo.TicketRequest;
import util.DBAccessor;
import util.Utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.LinkedList;
import java.util.List;

import static util.DBAccessor.executeQuery;

/**
 * Author : Anand
 * Created on 4/1/15.
 */
public class MaintenanceTicketTable extends Table{

    @Override
    public String getTableName() {
        return "MAINTENANCE_TICKET";
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
        String query = " CREATE TABLE " + getTableName() + " (" +
                " ticket_no NUMBER, " +
                " ticket_type VARCHAR(20), " +
                " ticket_date timestamp, " +
                " student_id VARCHAR2(20), " +
                " location_id VARCHAR(20), " + // room_id or family_apt_id
                " location_type VARCHAR(20), " + // Room or Apt
                " status VARCHAR(20), " + // status - InProgress, Resolved
                " description VARCHAR(1000), " + // status - InProgress, Resolved
                " PRIMARY KEY (ticket_no), " +
                " FOREIGN KEY (ticket_type) REFERENCES TICKET_SEVERITY, " +
                " FOREIGN KEY (student_id) REFERENCES RESIDENT " +
                ")";
        executeQuery(conn, query);
    }

    @Override
    public void insertIntoTable(Connection conn) throws SQLException {
        List<String> queries = new LinkedList<>();
        String query1 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Water',  CURRENT_TIMESTAMP, '200045256', 'HID1_O1', 'Room', 'InProgress', 'Water leakage')"; // HID1_O1 from Room table.
        String query2 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Electricity', CURRENT_TIMESTAMP - 10, '200045251', 'HID1_O2', 'Room', 'InProgress' , 'Shot circuit')"; // HID1_O1 from Room table.
        String query3 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Appliances', CURRENT_TIMESTAMP - 1, '200045253', 'F1', 'Apt', 'InProgress', 'Oven freezing stuff')"; // F1 from family apt table.
        String query4 = "INSERT INTO " + getTableName() + " VALUES(ticket_sequence.NEXTVAL, 'Internet',  CURRENT_TIMESTAMP - 20, '200045254', 'F2', 'Apt', 'InProgress', 'Internet gets hacked')"; // F2 from family apt table.

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        DBAccessor.executeBatchQuery(conn, queries);
    }

    public void insertRequest(Connection conn, String resident_id, TicketRequest ticketRequest) {

        String query = "INSERT INTO "
                + getTableName()
                + " VALUES(ticket_sequence.NEXTVAL, "
                + "'" + ticketRequest.getTicket() + "'" + ", "
                + "CURRENT_TIMESTAMP" + ", "
                + "'" + resident_id + "'" + ", "
                + "'" + "F2" + "'" + ", "
                + "'" + "Apt" + "'" + ", " +
                "'InProgress', "
                + "'" + ticketRequest.getDescription() + "'"
                + ")";

        System.out.println(query);

        try {
            executeQuery(conn, query);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        getTickets(conn, resident_id);
    }

    public List<TicketRequest> getTickets(Connection conn, String resident_id) {
        List<TicketRequest> tickets = new LinkedList<>();

        String query = "SELECT * FROM "
                + getTableName()
                + " WHERE "
                + "STUDENT_ID = '"
                + resident_id
                + "'";

        System.out.println(query);

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                TicketRequest ticketRequest = new TicketRequest();

                ticketRequest.setTicket(resultSet.getString("ticket_type"));
                ticketRequest.setStatus(resultSet.getString("status"));
                ticketRequest.setDescription(resultSet.getString("description"));
                ticketRequest.setDate(resultSet.getDate("ticket_date"));

                tickets.add(ticketRequest);
//                System.out.println(ticketRequest);
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During get tickets query " + ex.getMessage());
        }

        return tickets;
    }

    public List<TicketRequest> getTicketsToResolve(Connection conn) {
        List<TicketRequest> tickets = new LinkedList<>();

//        SELECT R.FNAME, R.LNAME, M.* FROM MAINTENANCE_TICKET M, RESIDENT R
//        WHERE STATUS = 'InProgress'
//        and M.STUDENT_ID = R.RES_ID

//        SELECT R.FNAME, R.LNAME, M.* FROM MAINTENANCE_TICKET MRESIDENT R WHERE STUDENT_ID = 'kogan'AND STATUS = 'InProgress'

        String query = "SELECT R.FNAME as FNAME, R.LNAME as LNAME," +
                " M.ticket_type as ticket_type, M.ticket_date as ticket_date, M.status as status, M.ticket_no as ticket_no, M.description as description" +
                " FROM "
                + getTableName() + " M, "
                + new ResidentTable().getTableName() + " R"
                + " WHERE "
                + "STUDENT_ID = RES_ID"
                + " AND STATUS = 'InProgress'";

        System.out.println(query);

        try (ResultSet resultSet = DBAccessor.selectQuery(conn, query)) {
            while(resultSet.next()){
                TicketRequest ticketRequest = new TicketRequest();

                ticketRequest.setTicket(resultSet.getString("ticket_type"));
                ticketRequest.setStatus(resultSet.getString("status"));
                ticketRequest.setDescription(resultSet.getString("description"));
                ticketRequest.setDate(resultSet.getDate("ticket_date"));

                ticketRequest.setStudent_id(resultSet.getString("FNAME") + resultSet.getString("LNAME"));
                ticketRequest.setTicket_no(resultSet.getInt("ticket_no"));
                ticketRequest.setAddress("todo - set correct address - roomno , apt or hall no.");

                tickets.add(ticketRequest);
                System.out.println("Ticket -----------------------------------------");
                System.out.println(ticketRequest);
            }
        }catch (SQLException ex){
            System.err.println("Error Occurred During get tickets for resolve query " + ex.getMessage());
        }

        return tickets;
    }

    public List<TicketRequest> resolve(Connection conn, String ticket_no) {
//        UPDATE MAINTENANCE_TICKET
//        SET STATUS = 'Resolved'
//        WHERE TICKET_NO = 1001
        String query = "UPDATE " + getTableName()
                + " SET STATUS = 'Resolved'"
                + " WHERE TICKET_NO = " + ticket_no;
        System.out.println(query);

        try {
            DBAccessor.executeQuery(conn, query);
            conn.commit();
            System.out.println("Ticket Status set to resolved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getTicketsToResolve(conn);
    }
}