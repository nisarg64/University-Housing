<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">View Former Invoice Page</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
  <s:form action="submitFormerInvoice" >
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

      <tbody>
      <tr>
        <th>Select Former Invoice Id:</th>
        <td><s:select name="invoice.invoiceId" headerKey="-1" headerValue="Select Invoice Id" list="formerInvoices" value="selectedInvoice"/></td>
      </tr>

      </tbody>

    </table>
    <s:submit cssClass="btn btn-primary btn-large" value="submit" name="submit" />

  </s:form>

</div>