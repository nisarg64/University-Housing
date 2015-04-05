<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">View Leases</span>
    </div>

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
            <tr>
                <th>Lease Number:</th>
                <td data-field="name"><s:text name="lease.leaseNumber"/></td>
            </tr>
            <tr>
                <th>Resident Id:</th>
                <td data-field="dob"><s:text name="lease.residentId"/></td>
            </tr>
        </tbody>
        </table>
</div>
