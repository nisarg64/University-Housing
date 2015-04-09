<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">New Lease Request</span>
    </div>
    <s:form action="createLeaseRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Lease Duration (Till Semester):</th>
            <td><s:select name="leaseRequest.duration" headerKey="-1" headerValue="Select Lease Duration"
            list="leaseDurations" value="leaseRequest.duration"/></td>
        </tr>
        <tr>
            <th>Payment Option:</th>
            <td><s:select name="leaseRequest.paymentOption" headerKey="-1" headerValue="Select Payment Option"
            list="paymentOptions" value="leaseRequest.paymentOption"/></td>
        </tr>
        <tr>
            <th>Preference 1:</th>
            <td><s:select name="leasePreference1.type" headerKey="-1" headerValue="Select Preference 1"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="leasePreference1.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        <tr>
            <th>Preference 2:</th>
            <td><s:select name="leasePreference2.type" headerKey="-1" headerValue="Select Preference 2"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="leasePreference2.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        <tr>
            <th>Preference 3:</th>
            <td><s:select name="leasePreference3.type" headerKey="-1" headerValue="Select Preference 3"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="leasePreference3.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
