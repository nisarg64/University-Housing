<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {

        $('#approveBtn').click(function(event){
            event.preventDefault();
            var value = $(this).attr('rowid');
            var url = "/uhousing/approveParkingRequest.action?requestId="+value;
            $.post( url, function( data ) {
                $( "#container" ).html( data );
            });

        });

        //Write the functions here

    });
</script>

<div class="container">

    <div class="upper">
        <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
        <span class="upper-text" style="margin-left:32%">Parking Requests</span>
        <span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>

    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
        <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
            <tr>
                <td><b>Request ID</b></td>
                <td><b>Resident ID</b></td>
                <td><b>Vehicle Type</b></td>
                <td><b>Handicapped</b></td>
                <td><b>Nearby Spot Preference</b></td>
                <td><b>STATUS</b></td>
                <td><b>ACTION</b></td>
            </tr>
            <s:iterator value="parkingRequests" status="stat">
                <tr>
                    <td><s:property value="requestID" /></td>
                    <td><s:property value="residentID" /></td>
                    <td><s:property value="vehicle" /></td>
                    <td><s:property value="handicapped" /></td>
                    <td><s:property value="nearSpot" /></td>
                    <td class="info"><s:property value="requestStatus" /></td>
                    <td><span><a id="approveBtn" class="btn btn-info" rowid="<s:property value="requestID" />" href="/<s:property value='appName'/>/approveParkingRequest.action"> Approve</a></span></td>
                </tr>
            </s:iterator>
        </table>
    </div>