<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
    <span class="upper-text" style="margin-left:32%">Request Parking Page</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
  </div>
  <s:form action="requestParkingForm.action" namespace="/" >
  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
      <th>Vehicle Type:</th>
      <td><s:select name="vehicle" headerKey="-1" headerValue="Select Vehicle Type" list="vehicleType" value="selectedVehicle"/></td>
    </tr>
    <tr>
      <th>Handicapped?:</th>
      <td><s:radio name = "handicapped" list="isHandicapped"/></td>
    </tr>
    <tr>
      <th>Nearby Spot?:</th>
      <td><s:radio name="nearSpot" list="nearbySpot"/></td>
    </tr>
    </tbody>

  </table>
    <s:submit value="submit" name="submit" />

  </s:form>

</div>