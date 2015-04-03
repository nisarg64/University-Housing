<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" id="backButton" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">Back</span></button></span>
    <span class="upper-text" style="margin-left:32%">Renew Parking Page</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
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
