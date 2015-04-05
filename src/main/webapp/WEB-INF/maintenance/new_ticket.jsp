<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">New Ticket</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
  <s:form action="submitMaintenanceTicket" namespace="/" >
  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
        <th>Ticket Type:</th>
        <td><s:select name="ticketRequest.ticket" headerKey="-1" headerValue="Select Ticket Type" list="ticketType" value="selectedTicket"/></td>
    </tr>
    <tr>
        <th>Description:</th>
        <td><s:textfield name="ticketRequest.description" /></td>
    </tr>
    </tbody>

  </table>
    <s:submit cssClass="btn btn-primary btn-large" value="submit" name="submit" />

  </s:form>

</div>