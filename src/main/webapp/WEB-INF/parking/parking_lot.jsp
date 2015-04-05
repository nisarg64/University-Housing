<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text" style="margin-left:32%">View Parking Lot Page</span>
  </div>
  <label class="statusMessage"><strong><s:property value="message" /></strong></label>

  <table class="table table-hover" data-toggle="table"  data-cache="false" data-height="299">

    <tbody>
    <tr>
      <th>Parking Lot Id:</th>
      <td><s:textfield name="parkingLot.lotId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Permit Id:</th>
      <td><s:textfield name= "parkingLot.permitId" readonly="true"/></td>
    </tr>
    <tr>
      <th>Parking Lot Type:</th>
      <td><s:textfield name="parkingLot.LotType" readonly="true"/></td>
    </tr>
    </tbody>

  </table>

</div>
