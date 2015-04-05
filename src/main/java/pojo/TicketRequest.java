package pojo;

import java.util.Date;

/**
 * Created by anand on 4/2/15.
 */
public class TicketRequest {

    private String ticket;
    private String description;
    private String status;
    private Date date;
    // ---- for staff:
    private Integer ticket_no;
    private String student_id;
    private String address;
    private String severity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(Integer ticket_no) {
        this.ticket_no = ticket_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAddress() {
        return address;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "TicketRequest{" +
                "ticket='" + ticket + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
