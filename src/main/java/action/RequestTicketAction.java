package action;

import db.table.MaintenanceTicketTable;
import pojo.TicketRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anand on 4/2/15.
 */

public class RequestTicketAction extends UHAction {

    private ArrayList<String> ticketType;
    private String message = "";
    private TicketRequest ticketRequest;
    private ArrayList<TicketRequest> tickets;
    private String ticket_no;

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
        String status = prTable.insertRequest(conn, resident_id, ticketRequest);

        if(!"SUCCESS".equals(status)){
            ticketRequest = new TicketRequest();
            // todo - get room type and room id , if no room provided, can't create ticket.
            message = status;
            return SUCCESS;
        }

        //Reset create ticket form
        ticketRequest = new TicketRequest();
        // todo - get room type and room id , if no room provided, can't create ticket.

        message = "Ticket created!";

        return SUCCESS;
    }

    public String getTicketsList() {
        message = "";
        //Updating DB
        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        MaintenanceTicketTable prTable = new MaintenanceTicketTable();
        tickets = prTable.getTickets(conn, resident_id);
        return SUCCESS;
    }

    public String getTicketsToResolve() {
        message = "";
        MaintenanceTicketTable prTable = new MaintenanceTicketTable();
        tickets = prTable.getTicketsToResolve(conn, (String) sessionMap.get("username"));
        return SUCCESS;
    }

    public String resolve() {
        System.out.println("ticket no: " + ticket_no);
        String current_ticket_status = "";
        MaintenanceTicketTable prTable = new MaintenanceTicketTable();

        ArrayList<TicketRequest> ticketsList = prTable.getTicketsToResolve(conn, (String) sessionMap.get("username"));
        System.out.println("tickets list ---> " + ticketsList);

        for (TicketRequest ticket : ticketsList) {
            System.out.println(ticket.getTicket_no().equals(ticket_no));
            System.out.println(ticket.getTicket_no() + "---" + ticket_no);
            if (Integer.toString(ticket.getTicket_no()).equals(ticket_no)) {
                current_ticket_status = ticket.getStatus();
                System.out.println("ticket status: " + current_ticket_status);
                break;
            }
        }
        tickets = prTable.resolve(conn, ticket_no, sessionMap, current_ticket_status);
        message = "Status Changed!";
        return SUCCESS;
    }

    public void setTickets(ArrayList<TicketRequest> tickets) {
        this.tickets = tickets;
    }

    public String getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public ArrayList<TicketRequest> getTickets() {
        return tickets;
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

    public ArrayList<String> getTicketType() {
        return ticketType;
    }

    public void setTicketType(ArrayList<String> ticketType) {
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
