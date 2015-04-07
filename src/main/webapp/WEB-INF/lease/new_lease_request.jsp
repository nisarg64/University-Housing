<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">New Lease Request</span>
    </div>
    <s:form action="createLeaseRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Lease Duration:</th>
            <td><s:select name="lease.duration" headerKey="-1" headerValue="Select Lease Duration"
            list="leaseDurations" value="lease.duration"/></td>
        </tr>
        <tr>
            <th>Enter Date:</th>
            <s:date name="lease.enterDate" var="enterDateId" format="MM/dd/yy"/>
            <td><s:textfield name="lease.enterDate" value="%{enterDateId}" placeholder="MM/DD/YY"/></td>
        </tr>
        <tr>
            <th>Payment Option:</th>
            <td><s:select name="lease.paymentOption" headerKey="-1" headerValue="Select Payment Option"
            list="paymentOptions" value="lease.paymentOption"/></td>
        </tr>
        <tr>
            <th>Preference 1:</th>
            <td><s:select name="lease.preference1.type" headerKey="-1" headerValue="Select Preference 1"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="lease.preference1.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        <tr>
            <th>Preference 2:</th>
            <td><s:select name="lease.preference2.type" headerKey="-1" headerValue="Select Preference 2"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="lease.preference2.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        <tr>
            <th>Preference 3:</th>
            <td><s:select name="lease.preference3.type" headerKey="-1" headerValue="Select Preference 3"
            list="preferenceTypes"/></td>
        </tr>
        <tr>
            <th>Preferred Hall:</th>
            <td><s:select name="lease.preference3.hallId" headerKey="-1" headerValue="Select Hall"
            list="halls" listKey="key" listValue="value"/></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
