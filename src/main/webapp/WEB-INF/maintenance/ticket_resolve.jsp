<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {

        $('.resolveBtn').click(function(event){
            event.preventDefault();
            var value = $(this).attr('rowid');
            var url = "/uhousing/ajax.resolveTicket.action?ticket_no="+value;
            $.post( url, function( data ) {
                $( ".container" ).html( data );
            });

        });

        //Write the functions here

    });
</script>

<div class="container">

  <div class="upperText">
    <span class="upper-text" style="margin-left:32%">Ticket status</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

    <s:form action="viewTicketStatus">
        <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
            <tr>
                <td><b>Ticket number</b></td>

                <td><b>Ticket type</b></td>
                <td><b>Ticket severity</b></td>
                <td><b>Status</b></td>
                <td><b>Description</b></td>
                <td><b>Date created</b></td>

                <td><b>Created by</b></td>
                <td><b>Address</b></td>
            </tr>
            <s:iterator value="tickets" status="stat">
                <tr>
                    <td><s:property value="ticket_no" /></td>

                    <td><s:property value="ticket" /></td>
                    <td><s:property value="severity" /></td>
                    <td><s:property value="status" /></td>
                    <td><s:property value="description" /></td>
                    <td><s:property value="date" /></td>

                    <td><s:property value="student_id" /></td>
                    <td><s:property value="address" /></td>

                    <td><span><a class="resolveBtn btn btn-info" rowid="<s:property value="ticket_no" />" href="#"> Resolve Ticket</a></span></td>
                </tr>
            </s:iterator>
        </table>
    </s:form>

</div>
