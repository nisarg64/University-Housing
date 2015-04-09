<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">All Available Vacancies</span>
    </div>

    <label class="statusMessage" style="text-decoration:solid"><s:property value="message" /></label>
    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the leases to view details:</label></p>

    <table class="table table-bordered" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <td><b>Housing Type</b></td>
            <td><b>Housing Name</b></td>
            <td><b>Location Number</b></td>
            <td><b>Apartment Id</b></td>
        </tr>
        <s:iterator value="allVacancies" status="stat">
            <tr>
                <td><s:property value="type" /></td>
                <td><s:property value="name" /></td>
                <td><s:property value="locationNumber" /></td>
                <td><s:property value="aptId" /></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
