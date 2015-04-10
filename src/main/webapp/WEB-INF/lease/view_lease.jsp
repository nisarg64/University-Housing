<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Lease</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Number:</th>
                <td><s:text name="lease.leaseNumber"/></td>
            </tr>
            <tr>
                <th>Lease Status:</th>
                <td><s:text name="lease.status"/></td>
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
                <td><s:date name="lease.leaseRequest.enterDate" format="MM/dd/yy"/></td>
            </tr>
            <tr>
                <th>Payment Option:</th>
                <td><s:text name="lease.paymentOption"/></td>
            </tr>
            <tr>
                <th>Housing Type:</th>
                <td><s:text name="lease.housingType"/></td>
            </tr>
            <tr>
                <th>Housing Name:</th>
                <td><s:text name="lease.housingName"/></td>
            </tr>
            <tr>
                <th>Housing Id:</th>
                <td><s:text name="lease.housingId"/></td>
            </tr>
            <tr>
                <th title="(Place Number for Residence Halls or General Student Apartments)/(Apartment Number for Family Apartments)">Location Number:</th>
                <td><s:text name="lease.locationNumber"/></td>
            </tr>
        </tbody>
        </table>
</div>
