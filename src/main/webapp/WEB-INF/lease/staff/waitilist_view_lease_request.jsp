<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Leases</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Number:</th>
                <td><s:text name="lease.leaseNumber"/></td>
            </tr>
            <tr>
                <th>Enter Date</th>
                <td><s:date name="lease.enterDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Leave Date</th>
                <td><s:date name="lease.leaveDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:text name="lease.paymentOption"/></td>
            </tr>
            <tr>
                <th>Lease Duration:</th>
                <td><s:text name="lease.duration"/></td>
            </tr>
            <tr>
                <th>Preference 1:</th>
                <td><s:text name="lease.preference1.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="lease.preference1.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 2:</th>
                <td><s:text name="lease.preference2.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="lease.preference2.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 3:</th>
                <td><s:text name="lease.preference3.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="lease.preference3.hallName"/></td>
            </tr>
            <tr>
                <th>Click to Approve:</th>
                <td><a href="/<s:property value='appName'/>/approveLeaseRequest.action?leaseNumber=<s:property value='leaseNumber' />"/>Approve</a></td>
            </tr>
        </tbody>

        </table>
</div>
