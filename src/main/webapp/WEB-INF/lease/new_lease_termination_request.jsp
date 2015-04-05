<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">Terminate Lease Request</span>
    </div>
    <s:form action="createLeaseTerminationRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Leave Date:</th>
            <s:date name="leaseTerminationRequest.leaveDate" var="leaveDateId" format="MM/dd/yy"/>
            <td><s:textfield name="leaseTerminationRequest.leaveDate" value="%{leaveDateId}" placeholder="MM/DD/YY" /></td>
        </tr>
        <tr>
            <th>Reason:</th>
            <td><input type="text" name="leaseTerminationRequest.reason"
            value="<s:property value="leaseTerminationRequest.reason"/>" required /></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
