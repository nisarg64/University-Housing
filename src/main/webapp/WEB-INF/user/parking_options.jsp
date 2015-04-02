<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upper">
    <span><button type="button" class="btn btn-primary navbar-btn"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>Back</button></span>
    <span class="upper-text">Parking Options</span>
    <span></span><a id="logout" class="btn btn-primary navbar-btn logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></span>
  </div>

  <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the choices to proceed:</label></p>
  <div class="ui-widget ui-widget-content ui-corner-all top_margin_small top_bottom_padding add_gradient add_shadow">
    <div class="top margin">
      <table class="box_width_full">
        <tr>
          <td  width="50%">
            <div style="height:100px;">
              <a href="/<s:property value='appName'/>/requestParking.action" id="requestParkingNavigator" class="big_button top_margin">Request Parking Spot</a>
            </div>
          </td>
          <td  width="50%">
            <div style="height:100px;">
              <a href="#" id="viewParkingLotNavigator" class="big_button top_margin">View Parking Lot Information</a>
            </div>
          </td>
        </tr>
        <tr>
          <td width="50%">
            <div style="height:100px;">
              <a href="#" id="viewParkingSpotNavigator" class="big_button top_margin">View Current Parking Spot Information</a>
            </div>
          </td>
          <td width="50%">
            <div style="height:100px;">
              <a href="#" id="renewParkingNavigator" class="big_button top_margin">Renew Parking Spot</a>
            </div>
          </td>
        </tr>
        <tr>
          <td  width="50%">
            <div style="height:100px;">
              <a href="#" id="returnParkingNavigator" class="big_button top_margin">Return Parking Spot</a>
            </div>
          </td>
          <td  width="50%">
            <div style="height:100px;">
              <a href="#" id="requestStatusNavigator" class="big_button top_margin">View Request Status</a>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>




</div>