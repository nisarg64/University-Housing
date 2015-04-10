<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">No Available Housing option. You can put the request on waitlist.</span>
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
                <th>Click to put on waitlist:</th>
                <td><a href="/<s:property value='appName'/>/waitlistLeaseRequest.action?requestNumber=<s:property value='requestNumber' />"/>Put on Waitlist</a></td>
            </tr>
        </tbody>
    </table>

        <div class="upperText">
            <span class="upper-text">Potential Roommates</span>
        </div>

        <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
            <tbody>
            <tr>
                <td><b>Resident ID </b></td>
                <td><b>First Name</b></td>
                <td><b>Last Name</b></td>
                <td><b>Category</b></td>
                <td><b>Gender</b></td>
                <td><b>Smoker</b></td>
            </tr>
            <s:iterator value="potentialRoommates" status="stat">
                <tr>
                    <td><s:property value="residentId" /></td>
                    <td><s:property value="fname" /></td>
                    <td><s:property value="lname" /></td>
                    <td><s:property value="category" /></td>
                    <td><s:property value="gender" /></td>
                    <td><s:property value="smoker" /></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>


</div>
