<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">Lease Termination Request Successfully Approved</span>
    </div>

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Inspection Date:</th>
            <td><s:date name="leaseTerminationRequest.inspectionDate" format="MM/dd/yy"/></td>
        </tr>
        <tr>
            <th>Reason:</th>
            <td><s:property value="leaseTerminationRequest.reason"/></td>
        </tr>
        <tr>
            <th>Status:</th>
            <td><s:property value="leaseTerminationRequest.status"/></td>
        </tr>
        </tbody>
    </table>
</div>
