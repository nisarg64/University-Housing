<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {

        $('.approveBtn').click(function(event){
            event.preventDefault();
            var value = $(this).attr('rowid');
            var url = "/uhousing/ajax.approveTerminationLeaseRequest.action?leaseNumber="+value;
            $.post( url, function( data ) {
                $( ".container" ).html( data );
            });

        });

    });
</script>

<div class="container">

    <div class="upperText">
        <span class="upper-text">View All Termination Lease Requests</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the leases to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Request Number</b></td>
            <td><b>Lease Number</b></td>
            <td><b>Status</b></td>
            <td><b>Leave Date</b></td>
            <td><b>Inspection Date</b></td>
        </tr>
        <s:iterator value="allTerminationLeases" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/editLeaseTerminationRequestToApprove.action?requestNumber=<s:property value='requestNumber' />" ><s:property value="requestNumber" /></a></td>
                <td><s:property value="lease.leaseNumber" /></td>
                <td><s:property value="status" /></td>
                <td><s:property value="leaveDate" /></td>
                <td><s:date name="inspectionDate" format="MM/dd/yy"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
