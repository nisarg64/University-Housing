<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">Renew Parking Page</span>
  </div>

  <label class="statusMessage"><strong><s:property value="message" /></strong></label>
  <s:form action="submitParkingRenewalRequest" namespace="/" >
    <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

      <tbody>
      <tr>
        <th>Enter Parking Spot ID:</th>
        <td><s:textfield name="parkingSpot.spotId" /></td>
      </tr>

    </table>
    <s:submit cssClass="btn btn-primary btn-large" value="submit" name="submit" />

  </s:form>

</div>
