<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
    <span class="upper-text" style="margin-left:32%">View Parking Spot Page</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
      <th>Parking Spot Id:</th>
      <td><s:textfield name="parkingSpot.spotId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Lot Id:</th>
      <td><s:textfield name="parkingSpot.lotId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Permit Id:</th>
      <td><s:textfield name= "parkingSpot.permitId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Spot Type:</th>
      <td><s:textfield name="parkingSpot.spotType" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Availability:</th>
      <td><s:textfield name="parkingSpot.availability" readonly="true"/></td>
    </tr>
    <tr>
      <th>Rental Fee:</th>
      <td><s:textfield name="parkingSpot.rentalFee" readonly="true"/></td>
    </tr>
    </tbody>

  </table>

</div>
