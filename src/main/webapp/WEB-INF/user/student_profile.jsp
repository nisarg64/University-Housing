<%@ taglib prefix="s" uri="/struts-tags"%>
    <div class="container">

    <div class="upper">
        <span><button type="button" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
        <span class="upper-text" style="margin-left:32%">Profile Page</span>
        <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
    </div>

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Name:</th>
            <td data-field="name"><s:textfield name="student.name"/></td>
        </tr>
        <tr>
            <th>Date of Birth:</th>
            <td data-field="dob"><s:textfield name="student.dob"/></td>
        </tr>
        <tr>
            <th>Gender:</th>
            <td data-field="gender"><s:textfield name="student.gender"/></td>
        </tr>
        <tr>
            <th>Major:</th>
            <td data-field="major"><s:textfield name="student.course"/></td>
        </tr>
        <tr>
            <th>Category:</th>
            <td data-field="category"><s:textfield name="student.category"/></td>
        </tr>
        <tr>
            <th>Address Street:</th>
            <td data-field="address_street"><s:textfield name="student.addrStreet"/></td>
        </tr>
        <tr>
            <th>Address City:</th>
            <td data-field="address_city"><s:textfield name="student.addrCity"/></td>
        </tr>
        <tr>
            <th>Postal Code:</th>
            <td data-field="postal"><s:textfield name="student.postalCode"/></td>
        </tr>
        <tr>
            <th>Phone number:</th>
            <td data-field="p_phone"><s:textfield name="student.primaryPhone"/></td>
        </tr>
        <tr>
            <th>Special Needs:</th>
            <td data-field="splNeed"><s:textfield name="student.spclNeeds"/></td>
        </tr>
        </tbody>
    </table>

</div>
