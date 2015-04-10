<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Lease Requests</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Lease Requests:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Lease Request Number</b></td>
            <td><b>Start Date</b></td>
            <td><b>Leave Date</b></td>
            <td><b>Enter Date</b></td>
            <td><b>Payment Option</b></td>
            <td><b>Security Deposit</b></td>
            <td><b>Status</b></td>
        </tr>
        <s:iterator value="leases" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/viewCurrentLeaseRequest.action?requestNumber=<s:property value='leaseRequest.requestNumber' />" ><s:property value="leaseRequest.requestNumber" /></a></td>
                <td><s:property value="startDate" /></td>
                <td><s:property value="endDate" /></td>
                <td><s:property value="leaseRequest.enterDate" /></td>
                <td><s:property value="leaseRequest.paymentOption" /></td>
                <td><s:property value="securityDeposit" /></td>
                <td class="info"><s:property value="leaseRequest.status" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>

    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Termination Lease Requests:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Lease Number</b></td>
            <td><b>Termination Request Number</b></td>
            <td><b>Leave Date</b></td>
            <td><b>Termination Date</b></td>
            <td><b>Payment Option</b></td>
            <td><b>Security Deposit</b></td>
            <td><b>Status</b></td>
        </tr>
        <s:iterator value="terminateLeases" status="stat">
            <tr>
                <td><a href="/<s:property value='appName'/>/viewCurrentLease.action?leaseNumber=<s:property value='leaseNumber' />" ><s:property value="leaseNumber" /></a></td>
                <td><s:property value="requestNumber" /></td>
                <td><s:property value="leaveDate" /></td>
                <td><s:property value="terminationDate" /></td>
                <td><s:property value="lease.leaseRequest.paymentOption" /></td>
                <td><s:property value="securityDeposit" /></td>
                <td class="info"><s:property value="status" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
