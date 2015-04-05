<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

  <div class="upperText">
    <span class="upper-text">Maintenance</span>
  </div>

  <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the choices to proceed:</label></p>
  <div class="ui-widget ui-widget-content ui-corner-all top_margin_small top_bottom_padding add_gradient add_shadow">
    <div class="top margin">
      <table class="box_width_full">
        <tr>
          <td  width="50%">
            <div style="height:100px;">
              <a href="/<s:property value='appName'/>/newTicket.action" id="newTicketNavigator" class="big_button top_margin">New Ticket</a>
            </div>
          </td>
          <td  width="50%">
            <div style="height:100px;">
              <a href="/<s:property value='appName'/>/viewTicketStatus.action" id=" viewTicketStatusNavigator" class="big_button top_margin">View Ticket Status</a>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>




</div>