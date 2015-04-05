<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upper">
        <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
        <span class="upper-text">View Leases</span>
        <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
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
        <s:iterator value="leases" status="stat">
            <tr>
                <td><s:property value="leaseNumber" /></td>
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
