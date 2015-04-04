package action;

import db.table.InvoiceTable;
import pojo.Invoice;

/**
 * Created by nisarg on 4/3/15.
 */
public class ViewCurrentInvoiceAction extends UHAction {

    private Invoice invoice;

    public String execute(){
        String username = (String) sessionMap.get("username");
        InvoiceTable invoiceTable = new InvoiceTable();
        invoice = invoiceTable.getCurrentInvoiceDetails(conn,username);
        return SUCCESS;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }


}
