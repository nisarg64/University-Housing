<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">

    <div class="upperText">
        <span class="upper-text">New Requests</span>
    </div>

    <p class="myTblHd ui-corner-all"><label class="left_margin_small contentHeader">Click one of the choices to proceed:</label></p>
    <div class="ui-widget ui-widget-content ui-corner-all top_margin_small top_bottom_padding add_gradient add_shadow">
        <div class="top margin">
            <table class="box_width_full">
                <tr>
                    <td  width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/newLeaseRequest.action" id="newLeaseRequestNavigator" class="big_button top_margin">New Lease Request</a>
                        </div>
                    </td>
                    <td  width="50%">
                        <div style="height:100px;">
                            <a href="/<s:property value='appName'/>/newLeaseTerminationRequest.action" id="newLeaseTerminationRequestNavigator" class="big_button top_margin">Terminate Lease</a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
