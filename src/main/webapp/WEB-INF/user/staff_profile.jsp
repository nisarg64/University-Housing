<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">Profile Page</span>
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
                <th>Position:</th>
                <td data-field="position"><input type="text" name="staff.position" value="<s:property value="staff.position"/>" readonly autofocus/></td>
            </tr>
            <tr>
                <th>Work Location:</th>
                <td data-field="location"><input type="text" name="staff.workLocation" value="<s:property value="staff.workLocation"/>" readonly autofocus/></td>
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
                <th>Address Country:</th>
                <td data-field="address_country"><input type="text" name="staff.addrCountry" value="<s:property value="staff.addrCountry"/>" readonly autofocus/></td>
            </tr>

            <tr>
                <th>Postal Code:</th>
                <td data-field="postal"><input type="text" name="staff.postalCode" value="<s:property value="staff.postalCode"/>" readonly autofocus/></td>
            </tr>
            </tbody>
        </table>

</div>
