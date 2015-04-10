<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">Approve Lease Termination Request</span>
    </div>
    <s:form action="terminateLease" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Request Number:</th>
            <td><input name="requestNumber" readonly value="<s:property value="requestNumber"/>"</td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
