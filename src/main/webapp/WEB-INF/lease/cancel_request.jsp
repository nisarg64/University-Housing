<%@ taglib prefix="s" uri="/struts-tags"%>

    <div class="container">

    <div class="upperText">
        <span class="upper-text">Cancel Request</span>
    </div>
     <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <s:form action="saveCancelRequest" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Enter Request Number:</th>
            <td><input type="text" name="requestNumber"
            value="<s:property value="requestNumber"/>" required /></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />
    </s:form>
</div>
