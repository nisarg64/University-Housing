<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View leaseRequests</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Request Number:</th>
                <td><s:text name="leaseRequest.requestNumber"/></td>
            </tr>
            <tr>
                <th>Enter Date</th>
                <td><s:date name="leaseRequest.enterDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Leave Date</th>
                <td><s:date name="leaseRequest.leaveDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:text name="leaseRequest.paymentOption"/></td>
            </tr>
            <tr>
                <th>Lease Duration:</th>
                <td><s:text name="leaseRequest.duration"/></td>
            </tr>
            <tr>
                <th>Preference 1:</th>
                <td><s:text name="leaseRequest.preference1.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leaseRequest.preference1.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 2:</th>
                <td><s:text name="leaseRequest.preference2.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leaseRequest.preference2.hallName"/></td>
            </tr>
            <tr>
                <th>Preference 3:</th>
                <td><s:text name="leaseRequest.preference3.type"/></td>
            </tr>
            <tr>
                <th>Hall Name:</th>
                <td><s:text name="leaseRequest.preference3.hallName"/></td>
            </tr>
            <tr>
                <th>Housing Type:</th>
                <td><s:text name="leaseRequest.proposedHousing.proposedHousingType"/></td>
            </tr>
            <tr>
                <th>Name:</th>
                <td><s:text name="leaseRequest.proposedHousing.proposedHousingName"/></td>
            </tr>
            <tr>
                <th>Housing Id:</th>
                <td><s:text name="leaseRequest.proposedHousing.proposedHousingId"/></td>
            </tr>
            <tr>
                <th>Location Number:</th>
                <td><s:text name="leaseRequest.proposedHousing.proposedLocationNumber"/></td>
            </tr>
            <tr>
                <th>Click to Approve:</th>
                <td><a href="/<s:property value='appName'/>/approveleaseRequest.action?requestNumber=<s:property value='requestNumber' />"/>Approve</a></td>
            </tr>
        </tbody>

        </table>
</div>
