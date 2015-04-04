package action;

import db.table.InvoiceTable;
import db.view.InvoiceView;
import pojo.Invoice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewFormerInvoiceAction extends UHAction {
    private List<String> formerInvoices;
    private Invoice invoice;

    public ViewFormerInvoiceAction(){
        //String resident_id = (String) sessionMap.get("username");
        //InvoiceTable invoiceTable = new InvoiceTable();
        formerInvoices = new ArrayList<String>();
        //formerInvoices = invoiceTable.getFormerInvoices(conn, resident_id);

        formerInvoices.add("Invoice1");
        formerInvoices.add("Invoice2");
        formerInvoices.add("Invoice3");
        formerInvoices.add("Invoice4");
        formerInvoices.add("Invoice5");
        formerInvoices.add("Invoice6");

    }


    public String execute() {

        return SUCCESS;
    }

    public String submit() {

        String resident_id = (String) sessionMap.get("username");
        resident_id = resident_id.trim();
        InvoiceView invoiceView = new InvoiceView();
        invoice = invoiceView.getFormerInvoiceDetails(conn, resident_id, invoice.getInvoiceId());

        return SUCCESS;
    }

    public List<String> getFormerInvoices() {
        return formerInvoices;
    }

    public void setFormerInvoices(List<String> formerInvoices) {
        this.formerInvoices = formerInvoices;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }


}
