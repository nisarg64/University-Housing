package action;

import db.table.MaintenanceTicketTable;
import pojo.TicketRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anand on 4/2/15.
 */

public class RequestTicketAction extends UHAction {

    private List<String> ticketType;
    private String message = "";
    private TicketRequest ticketRequest;

    public RequestTicketAction(){
        ticketType = new ArrayList<>();
        ticketType.add("Water");
        ticketType.add("Electricity");
        ticketType.add("Appliances");
        ticketType.add("Internet");
        ticketType.add("Cleaning");
        ticketType.add("Miscellaneous");
    }

    public String execute() {
        message = "";
        return SUCCESS;
    }

    public String submit() {
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        MaintenanceTicketTable prTable = new MaintenanceTicketTable();
        prTable.insertRequest(conn, resident_id, ticketRequest);

        //Reset create ticket form
        ticketRequest = new TicketRequest();
        // todo - get room type and room id , if no room provided, can't create ticket.

        message = "Ticket created!";

        return SUCCESS;
    }

    public String getTickets() {
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        MaintenanceTicketTable prTable = new MaintenanceTicketTable();
        prTable.getTickets(conn, resident_id);

        //Reset create ticket form
        ticketRequest = new TicketRequest();
        // todo - get room type and room id , if no room provided, can't create ticket.

        message = "Ticket created!";

        return SUCCESS;
    }

    public String display() {
        return NONE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TicketRequest getTicketRequest() {
        return ticketRequest;
    }

    public void setTicketRequest(TicketRequest ticketRequest) {
        this.ticketRequest = ticketRequest;
    }

    public List<String> getTicketType() {
        return ticketType;
    }

    public void setTicketType(List<String> ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "RequestParkingAction{" +
                ", message='" + message + '\'' +
                ", ticketRequest=" + ticketRequest +
                '}';
    }
}
