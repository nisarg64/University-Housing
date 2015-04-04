<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upper">
        <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
        <span class="upper-text">View Leases</span>
        <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>

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
        </tbody>
        </table>
</div>
