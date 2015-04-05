<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {

        $('.approveBtn').click(function(event){
            event.preventDefault();
            var value = $(this).attr('rowid');
            var url = "/uhousing/ajax.approveParkingRequest.action?requestId="+value;
            $(".container").load(url);
        });

    });
</script>

<div class="container">

    <div class="upperText">
        <span class="upper-text">Parking Requests</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
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
                    <td><span><a class="approveBtn btn btn-info" rowid="<s:property value="requestID" />" href="#"> Approve</a></span></td>
                </tr>
            </s:iterator>
        </table>
    </div>