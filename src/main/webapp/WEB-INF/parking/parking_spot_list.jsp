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
        <span class="upper-text">All Parking Spots</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the parking spots to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Spot ID </b></td>
            <td><b>Lot ID</b></td>
            <td><b>Permit ID</b></td>
        </tr>
        <s:iterator value="allSpots" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/viewParkingSpotDetail.action?spotId=<s:property value='spotId' />" ><s:property value="spotId" /></a></td>
                <td><s:property value="lotId" /></td>
                <td><s:property value="permitId" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
