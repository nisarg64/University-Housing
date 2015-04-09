<%@ taglib prefix="s" uri="/struts-tags"%>
<script>

    $( document ).ready(function() {

        $('input[type="radio"]').change(function(){

            var check = $('input:radio[name="leaseRequest.usePrivateAccommodation"]:checked').val();

            if(check == "false"){
                $(".preference").show();
            }else{
                $('.preference').hide();
            }

        });
    });

</script>


<div class="container">

    <div class="upperText">
        <span class="upper-text">New Lease Request</span>
    </div>
    <s:form action="createLeaseRequest" namespace="/" >

        <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
            <tbody>
            <tr>
                <th>Lease Duration (For Semester):</th>
                <td><s:select name="leaseRequest.duration" headerKey="-1" headerValue="Select Lease Duration"
                              list="leaseDurations" value="leaseRequest.duration"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:select name="leaseRequest.paymentOption" headerKey="-1" headerValue="Select Payment Option"
                              list="paymentOptions" value="leaseRequest.paymentOption"/></td>
            </tr>
            <tr>
                <th>Private Accommodation:</th>
                <td><s:radio label="private_acco" cssClass="radio-inline" name="leaseRequest.usePrivateAccommodation" list="#{ true:'Yes', false:'No'}" value="2" required="true"/></td>
            </tr>
            <tr>
                <th>Enter Date</th>
                <s:date name="leaseRequest.enterDate" var="leaveDateId" format="MM/dd/yy"/>
                <td><s:textfield name="leaseRequest.enterDate" value="%{leaveDateId}" placeholder="MM/DD/YY" /></td>
            </tr>
            <tr class="preference">
                <th>Preference 1:</th>
                <td><s:select name="leasePreference1.type" headerKey="-1" headerValue="Select Preference 1"
                              list="preferenceTypes"/></td>
            </tr>
            <tr class="preference">
                <th>Preferred Hall:</th>
                <td><s:select name="leasePreference1.hallId" headerKey="-1" headerValue="Select Hall"
                              list="halls" listKey="key" listValue="value"/></td>
            </tr>
            <tr class="preference">
                <th>Preference 2:</th>
                <td><s:select name="leasePreference2.type" headerKey="-1" headerValue="Select Preference 2"
                              list="preferenceTypes"/></td>
            </tr>
            <tr class="preference">
                <th>Preferred Hall:</th>
                <td><s:select name="leasePreference2.hallId" headerKey="-1" headerValue="Select Hall"
                              list="halls" listKey="key" listValue="value"/></td>
            </tr>
            <tr class="preference">
                <th>Preference 3:</th>
                <td><s:select name="leasePreference3.type" headerKey="-1" headerValue="Select Preference 3"
                              list="preferenceTypes"/></td>
            </tr>
            <tr class="preference">
                <th>Preferred Hall:</th>
                <td><s:select name="leasePreference3.hallId" headerKey="-1" headerValue="Select Hall"
                              list="halls" listKey="key" listValue="value"/></td>
            </tr>
            </tbody>
        </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>