<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">Terminate Lease Request Submitted Successfully</span>
    </div>

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Leave Date:</th>
            <td><s:date name="leaseTerminationRequest.leaveDate" format="MM/dd/yy"/></td>
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
