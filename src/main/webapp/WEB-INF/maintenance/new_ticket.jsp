<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
    <span class="upper-text" style="margin-left:32%">New Ticket</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
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