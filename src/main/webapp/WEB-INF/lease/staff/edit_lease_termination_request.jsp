<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">View Lease Termination Request</span>
    </div>
    <s:form action="approveLeaseTerminationRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Request Number:</th>
            <td><input name="requestNumber" readonly value="<s:property value="requestNumber"/>"</td>
        </tr>
        <tr>
            <th>Inspection Date:</th>
            <s:date name="leaseTerminationRequest.inspectionDate" var="inspectionDate" format="MM/dd/yy"/>
            <td><s:textfield name="leaseTerminationRequest.inspectionDate" value="%{inspectionDate}" placeholder="MM/DD/YY" /></td>
        </tr>
        <tr>
            <th>Damage Fees:</th>
            <td><input type="text" name="damageFees"
            value="<s:property value="damageFees"/>" required /></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
