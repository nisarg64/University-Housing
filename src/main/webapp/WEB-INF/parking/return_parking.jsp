<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">Return Parking Page</span>
  </div>

  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
  <s:form action="submitParkingReturnRequest" namespace="/" >
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

      <tbody>
      <tr>
        <th>Vehicle Type:</th>
        <td><s:select name="spotId" headerKey="-1" headerValue="Select Parking Spot" list="parkingSpots" value="selectedParkingSpot"/></td>
      </tr>

    </table>
    <s:submit cssClass="btn btn-primary btn-large" value="submit" name="submit" />

  </s:form>

</div>
