<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upper">
        <button type="button" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button>
        <span class="upper-text">Profile Page</span>
        <a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a>
    </div>
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">
        <tbody>
        <tr>
            <th>Name:</th>
            <td data-field="name">Abhishek Agrawal</td>
        </tr>
        <tr>
            <th>Date of Birth:</th>
            <td data-field="dob">2 July 1990</td>
        </tr>
        <tr>
            <th>Major:</th>
            <td data-field="major">Computer Science</td>
        </tr>
        <tr>
            <th>Category:</th>
            <td data-field="category">Graduate Student</td>
        </tr>
        <tr>
            <th>Address:</th>
            <td data-field="address">XYZ, Raleigh</td>
        </tr>
        <tr>
            <th>Phone number:</th>
            <td data-field="p_phone">919-XXX-XXX</td>
        </tr>
        <tr>
            <th>Special Needs:</th>
            <td data-field="splNeed">ABCD...</td>
        </tr>
        </tbody>
    </table>


</div>
