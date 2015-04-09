<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Lease Requests</span>
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
                <th>Lease Request Duration:</th>
                <td><s:text name="leaseRequest.duration"/></td>
            </tr>
            <tr>
                <th>Start Date</th>
                <td><s:date name="leaseRequest.enterDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:text name="leaseRequest.paymentOption"/></td>
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
        </tbody>
        </table>
</div>
