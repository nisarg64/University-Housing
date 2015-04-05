<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">Request Parking Page</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
  <s:form action="submitParkingRequest" namespace="/" >
  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
      <th>Vehicle Type:</th>
      <td><s:select name="parkingRequest.vehicle" headerKey="-1" headerValue="Select Vehicle Type" list="vehicleType" value="selectedVehicle"/></td>
    </tr>
    <tr>
      <th>Handicapped?:</th>
      <td><s:radio name = "parkingRequest.handicapped" list="isHandicapped"/></td>
    </tr>
    <tr>
      <th>Nearby Spot?:</th>
      <td><s:radio name="parkingRequest.nearSpot" list="nearbySpot"/></td>
    </tr>
    </tbody>

  </table>
    <s:submit cssClass="btn btn-primary btn-large" value="submit" name="submit" />

  </s:form>

</div>