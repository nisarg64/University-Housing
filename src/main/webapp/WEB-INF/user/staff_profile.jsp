<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upper">
        <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
        <span class="upper-text" style="margin-left:32%">Profile Page</span>
        <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>
        <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
            <tbody>
            <tr>
                <th>First Name:</th>
                <td data-field="fname"><input type="text" name="staff.fname" value="<s:property value="staff.fname"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Last Name:</th>
                <td data-field="lname"><input type="text" name="staff.lname" value="<s:property value="staff.lname"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Date of Birth:</th>
                <td data-field="dob"><input type="text" name="staff.dob" value="<s:property value="staff.dob"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Gender:</th>
                <td data-field="gender"><input type="text" name="staff.gender" value="<s:property value="staff.gender"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Address Street:</th>
                <td data-field="address_street"><input type="text" name="staff.addrStreet" value="<s:property value="staff.addrStreet"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Address City:</th>
                <td data-field="address_city"><input type="text" name="staff.addrCity" value="<s:property value="staff.addrCity"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Postal Code:</th>
                <td data-field="postal"><input type="text" name="staff.postalCode" value="<s:property value="staff.postalCode"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Phone number:</th>
                <td data-field="p_phone"><input type="text" name="staff.primaryPhone" value="<s:property value="staff.primaryPhone"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Special Needs:</th>
                <td data-field="splNeed"><input type="text" name="staff.spclNeeds" value="<s:property value="staff.spclNeeds"/>" readonly autofocus/></td>
            </tr>
            </tbody>
        </table>

</div>
