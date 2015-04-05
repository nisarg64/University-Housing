<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">View Former Invoice Details Page</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
      <th>Invoice Id:</th>
      <td><s:textfield name="invoice.invoiceId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Resident Id:</th>
      <td><s:textfield name="invoice.residentId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Housing Rent:</th>
      <td><s:textfield name= "invoice.housingRent" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Rent:</th>
      <td><s:textfield name="invoice.parkingRent" readonly="true"/></td>
    </tr>
    <tr>
      <th>Lease No:</th>
      <td><s:textfield name="invoice.leaseNo" readonly="true"/></td>
    </tr>
    <tr>
      <th>Pending Charges:</th>
      <td><s:textfield name="invoice.pendingCharges" readonly="true"/></td>
    </tr>
    <tr>
      <th>Late Fees:</th>
      <td><s:textfield name="invoice.lateFees" readonly="true"/></td>
    </tr>
    <tr>
      <th>Due Date:</th>
      <td><s:textfield name="invoice.dueDate" readonly="true"/></td>
    </tr>
    <tr>
      <th>Payment Status:</th>
      <td><s:textfield name="invoice.paymentStatus" readonly="true"/></td>
    </tr>
    <tr>
      <th>Payment Id:</th>
      <td><s:textfield name="invoice.invoicePaymentId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Payment Date:</th>
      <td><s:textfield name="invoice.paymentDate" readonly="true"/></td>
    </tr>
    <tr>
      <th>Payment Amount:</th>
      <td><s:textfield name="invoice.paymentAmount" readonly="true"/></td>
    </tr>
    <tr>
      <th>Payment Method:</th>
      <td><s:textfield name="invoice.paymentMethod" readonly="true"/></td>
    </tr>
    </tbody>

  </table>

</div>
