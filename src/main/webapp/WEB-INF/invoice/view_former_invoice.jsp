<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">Former Invoices</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
   <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the parking spots to view details:</label></p>

  <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
    <tbody>
    <tr>
      <td><b>Invoice ID </b></td>
      <td><b>Billed Amount</b></td>
      <td><b>Due Date</b></td>
    </tr>
    <s:iterator value="formerInvoices" status="stat">
      <tr>
        <td><a href="/<s:property value='appName'/>/submitFormerInvoice.action?invoiceId=<s:property value='invoiceId' />" ><s:property value="invoiceId" /></a></td>
        <td><s:property value="totalAmount" /></td>
        <td><s:property value="dueDate" /></td>
      </tr>
    </s:iterator>
    </tbody>
  </table>

</div>