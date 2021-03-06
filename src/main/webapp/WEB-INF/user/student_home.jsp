<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">
    <div class="upperText">
        <span class="upper-text">Welcome <s:property value="role" />&nbsp;<span class="user"><s:property value="username" /></span></span>
    </div>

    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the choices to proceed:</label></p>
    <div class="ui-widget ui-widget-content ui-corner-all top_margin_small top_bottom_padding add_gradient add_shadow">
        <div class="top margin">
            <table class="box_width_full">
                <tr>
                    <td  width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/housingOption.action" id="housingNavigator" class="big_button top_margin">Housing Options</a>
                        </div>
                    </td>
                    <td  width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/parkingOption.action" id="parkingNavigator" class="big_button top_margin">Parking Option</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/maintenance.action" id="maintenanceNavigator" class="big_button top_margin">Maintenance</a>
                        </div>
                    </td>
                    <td width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/profile.action" id="profileNavigator" class="big_button top_margin">Profile</a>
                        </div>
                    </td>
                </tr>

            </table>
        </div>
    </div>




</div>
