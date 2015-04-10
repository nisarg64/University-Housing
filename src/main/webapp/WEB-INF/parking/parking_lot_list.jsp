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
        <span class="upper-text">All Parking Lots</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the parking lots to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Lot ID </b></td>
            <td><b>Lot Type</b></td>
            <td><b>Nearby Housing</b></td>
            <td><b>Housing Type</b></td>
            <td><b>Address</b></td>
            <td><b>Vacant Spots</b></td>
        </tr>
        <s:iterator value="allLots" status="stat">
            <tr>
                <td><s:property value="lotId" /></td>
                <td><s:property value="lotType" /></td>
                <td><s:property value="nearbyHousing" /></td>
                <td><s:property value="housingType" /></td>
                <td><s:property value="address" /></td>
                <td><s:property value="vacancies" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
