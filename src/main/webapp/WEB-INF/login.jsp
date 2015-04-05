<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
    $( document ).ready(function() {
        $('.upper').hide();
    });


</script>

<div class="container">

    <label class="errorLabel"><strong><s:property value="errorMsg" /></strong></label>
    <s:form action="login" cssClass="form-signin" theme="simple">
        <h3 cssClass="form-signin-heading">Please Login here..</h3><br/>

        <input type="text" name="username" class=input-block-level" value="<s:property value="username"/>"  placeholder="Username" required autofocus />
        <input type="password" name="password" value="<s:property value="password"/>" placeholder="Password" required autofocus />
        <label><strong>Login as :</strong></label>
        <s:radio label="role" cssClass="radio-inline" name="role" list="#{'student':'Student','guest':'Guest', 'staff':'Staff'}" value="3" required="true"/>

        <div cssClass="wrapper">
              <span cssClass="group-btn">
                    <s:submit cssClass="btn btn-primary btn-large" value="Login"/>
              </span>
        </div>
    </s:form>

</div>
