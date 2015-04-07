<%@ taglib prefix="s" uri="/struts-tags"%>
    <div class="container">

    <div class="upperText">
        <span class="upper-text">Profile Page</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <s:form action="updateProfile" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Student Id:</th>
            <td data-field="srudent_id"><input type="text" name="resident.resId" value="<s:property value="resident.resId"/>" readonly/></td>
        </tr>
        <tr>
            <th>First Name:</th>
            <td data-field="fname"><input type="text" name="resident.fname" value="<s:property value="resident.fname"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Last Name:</th>
            <td data-field="lname"><input type="text" name="resident.lname" value="<s:property value="resident.lname"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Date of Birth:</th>
            <td data-field="dob"><input type="text" name="resident.dob" value="<s:property value="resident.dob"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Gender:</th>
            <td data-field="gender"><input type="text" name="resident.gender" value="<s:property value="resident.gender"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Course:</th>
            <td data-field="Course"><input type="text" name="resident.course" value="<s:property value="resident.course"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Category:</th>
            <td data-field="category"><input type="text" name="resident.category" value="<s:property value="resident.category"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Status:</th>
            <td data-field="status"><input type="text" name="resident.status" value="<s:property value="resident.status"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Address Street:</th>
            <td data-field="address_street"><input type="text" name="resident.addrStreet" value="<s:property value="resident.addrStreet"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Address City:</th>
            <td data-field="address_city"><input type="text" name="resident.addrCity" value="<s:property value="resident.addrCity"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Postal Code:</th>
            <td data-field="postal"><input type="text" name="resident.postalCode" value="<s:property value="resident.postalCode"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Address Country:</th>
            <td data-field="address_city"><input type="text" name="resident.addrCountry" value="<s:property value="resident.addrCountry"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Primary Phone number:</th>
            <td data-field="p_phone"><input type="text" name="resident.primaryPhone" value="<s:property value="resident.primaryPhone"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Alternate Phone number:</th>
            <td data-field="a_phone"><input type="text" name="resident.alternatePhone" value="<s:property value="resident.alternatePhone"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Special Needs:</th>
            <td data-field="splNeed"><input type="text" name="resident.spclNeeds" value="<s:property value="resident.spclNeeds"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Comments:</th>
            <td data-field="comments"><input type="text" name="resident.comments" value="<s:property value="resident.comments"/>" autofocus/></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />

    </s:form>

</div>
