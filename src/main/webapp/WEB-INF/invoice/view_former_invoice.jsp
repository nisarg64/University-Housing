<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
    <span class="upper-text" style="margin-left:32%">View Former Invoice Page</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
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