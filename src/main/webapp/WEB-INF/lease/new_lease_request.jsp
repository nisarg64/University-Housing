<%@ taglib prefix="s" uri="/struts-tags"%>
    <div class="container">

    <div class="upper">
        <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
        <span class="upper-text" style="margin-left:32%">New Lease Request</span>
        <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>
    <s:form action="createLeaseRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Enter Date:</th>
            <s:date name="lease.enterDate" var="enterDateId" format="MM/dd/yy"/>
            <td><s:textfield name="lease.enterDate" value="%{enterDateId}" /></td>
        </tr>
        <tr>
            <th>Leave Date:</th>
            <s:date name="lease.leaveDate" var="leaveDateId" format="MM/dd/yy"/>
            <td><s:textfield name="lease.leaveDate" value="%{leaveDateId}" /></td>
        </tr>
        <tr>
            <th>Payment Option:</th>
            <td><s:select name="lease.paymentOption" headerKey="-1" headerValue="Select Payment Option"
            list="paymentOptions" value="lease.paymentOption"/></td>
        </tr>
        <tr>
            <th>Lease Duration:</th>
            <td><s:select name="lease.duration" headerKey="-1" headerValue="Select Lease Duration"
            list="leaseDurations" value="lease.duration"/></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
