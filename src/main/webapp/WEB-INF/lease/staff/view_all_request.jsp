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
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the lease request to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Lease Request Number</b></td>
            <td><b>Resident ID</b></td>
            <td><b>Start Date</b></td>
            <td><b>End Date</b></td>
            <td><b>Enter Date</b></td>
            <td><b>Payment Option</b></td>
            <td><b>Status</b></td>
        </tr>
        <s:iterator value="allLeases" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/viewLeaseToApprove.action?requestNumber=<s:property value='leaseRequest.requestNumber' />" ><s:property value="leaseRequest.requestNumber" /></a></td>
                <td><s:property value="leaseRequest.residentId" /></td>
                <td><s:property value="startDate" /></td>
                <td><s:property value="endDate" /></td>
                <td><s:property value="leaseRequest.enterDate" /></td>
                <td><s:property value="leaseRequest.paymentOption" /></td>
                <td class="info"><s:property value="leaseRequest.status" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
