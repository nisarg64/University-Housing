<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Lease Request</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Request Number:</th>
                <td><s:text name="leaseRequest.RequestNumber"/></td>
            </tr>
            <tr>
                <th>Lease Request Status:</th>
                <td><s:text name="leaseRequest.status"/></td>
            </tr>
            <tr>
                <th>Start Date</th>
                <td><s:date name="lease.startDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>End Date</th>
                <td><s:date name="lease.endDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Enter Date</th>
                <td><s:date name="leaseRequest.enterDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:text name="leaseRequest.paymentOption"/></td>
            </tr>
            <tr>
                <th>Preference 1:</th>
                <td><s:text name="leasePreference1.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leasePreference1.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 2:</th>
                <td><s:text name="leasePreference2.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leasePreference2.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 3:</th>
                <td><s:text name="leasePreference3.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leasePreference3.hallName"/></td>
            </tr>
        </tbody>
        </table>
</div>
