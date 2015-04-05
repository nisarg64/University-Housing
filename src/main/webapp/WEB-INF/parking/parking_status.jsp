<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text" style="margin-left:32%">Parking status Page</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

      <tbody>
      <tr>
        <th>Request Status:</th>
        <td><s:textfield name="parkingRequest.requestStatus" readonly="true"/></td>
      </tr>
      <tr>
        <th>Vehicle Type:</th>
        <td><s:textfield name="parkingRequest.vehicle" readonly="true"/></td>
      </tr>
      <tr>
        <th>Handicapped?:</th>
        <td><s:textfield name= "parkingRequest.handicapped" readonly="true"/></td>
      </tr>
      <tr>
        <th>Nearby Spot?:</th>
        <td><s:textfield name="parkingRequest.nearSpot" readonly="true"/></td>
      </tr>
      </tbody>

    </table>

</div>
