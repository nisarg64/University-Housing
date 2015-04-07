<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {

        $('.approveBtn').click(function(event){
            event.preventDefault();
            var value = $(this).attr('rowid');
            var url = "/uhousing/ajax.approveLeaseRequest.action?leaseNumber="+value;
            $.post( url, function( data ) {
                $( ".container" ).html( data );
            });

        });

    });
</script>

<div class="container">

    <div class="upperText">
        <span class="upper-text">View All Lease Requests</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the leases to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Lease Number</b></td>
            <td><b>Resident ID</b></td>
            <td><b>Enter Date</b></td>
            <td><b>Leave Date</b></td>
            <td><b>Duration</b></td>
            <td><b>Payment Option</b></td>
            <td><b>Security Deposit</b></td>
            <td><b>Cut Off Date</b></td>
            <td><b>Status</b></td>
        </tr>
        <s:iterator value="allLeases" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/viewLeaseToApprove.action?leaseNumber=<s:property value='leaseNumber' />" ><s:property value="leaseNumber" /></a></td>
                <td><s:property value="residentId" /></td>
                <td><s:property value="enterDate" /></td>
                <td><s:property value="leaveDate" /></td>
                <td><s:property value="duration" /></td>
                <td><s:property value="paymentOption" /></td>
                <td><s:property value="securityDeposit" /></td>
                <td><s:property value="cutoffDate" /></td>
                <td class="info"><s:property value="status" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
