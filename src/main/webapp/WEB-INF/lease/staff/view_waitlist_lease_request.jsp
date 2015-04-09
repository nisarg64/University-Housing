<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">Lease Request waitlisted Successfully</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Number:</th>
                <td><s:text name="lease.leaseNumber"/></td>
            </tr>
            <tr>
                <th>Place Number:</th>
                <td><s:text name="lease.placeNumber"/></td>
            </tr>
            <tr>
                <th>Apartment Number:</th>
                <td><s:text name="lease.apartmentNumber"/></td>
            </tr>
        </tbody>
        </table>
</div>
