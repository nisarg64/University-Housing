<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text" style="margin-left:32%">Ticket status</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

    <s:form action="viewTicketStatus">
        <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
            <tr>
                <td><b>Ticket type</b></td>
                <td><b>Status</b></td>
                <td><b>Description</b></td>
                <td><b>Date created</b></td>
            </tr>
            <s:iterator value="tickets" status="stat">
                <tr>
                    <td><s:property value="ticket" /></td>
                    <td><s:property value="status" /></td>
                    <td><s:property value="description" /></td>
                    <td><s:property value="date" /></td>
                </tr>
            </s:iterator>
        </table>
    </s:form>

</div>
