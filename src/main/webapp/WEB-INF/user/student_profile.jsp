<%@ taglib prefix="s" uri="/struts-tags"%>
    <div class="container">

    <div class="upperText">
        <span class="upper-text" style="margin-left:32%">Profile Page</span>
    </div>
    <label class="statusMessage"><strong><s:property value="message" /></strong></label>
    <s:form action="updateProfile" namespace="/" >

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>First Name:</th>
            <td data-field="fname"><input type="text" name="student.fname" value="<s:property value="student.fname"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Last Name:</th>
            <td data-field="lname"><input type="text" name="student.lname" value="<s:property value="student.lname"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Date of Birth:</th>
            <td data-field="dob"><input type="text" name="student.dob" value="<s:property value="student.dob"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Gender:</th>
            <td data-field="gender"><input type="text" name="student.gender" value="<s:property value="student.gender"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Major:</th>
            <td data-field="major"><input type="text" name="student.course" value="<s:property value="student.course"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Category:</th>
            <td data-field="category"><input type="text" name="student.category" value="<s:property value="student.category"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Address Street:</th>
            <td data-field="address_street"><input type="text" name="student.addrStreet" value="<s:property value="student.addrStreet"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Address City:</th>
            <td data-field="address_city"><input type="text" name="student.addrCity" value="<s:property value="student.addrCity"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Postal Code:</th>
            <td data-field="postal"><input type="text" name="student.postalCode" value="<s:property value="student.postalCode"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Phone number:</th>
            <td data-field="p_phone"><input type="text" name="student.primaryPhone" value="<s:property value="student.primaryPhone"/>" required autofocus/></td>
        </tr>
        <tr>
            <th>Special Needs:</th>
            <td data-field="splNeed"><input type="text" name="student.spclNeeds" value="<s:property value="student.spclNeeds"/>" required autofocus/></td>
        </tr>
        </tbody>
    </table>
        <s:submit value="submit" cssClass="btn btn-primary btn-large" name="submit" />

    </s:form>

</div>
