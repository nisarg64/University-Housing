<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript">

</script>

</head>
<body>
<div class="container">

    <s:form action="login" cssClass="form-signin" theme="simple">
        <h3 cssClass="form-signin-heading">Please Login here..</h3><br/>

        <input type="text" name="username" class=input-block-level" value="<s:property value="username"/>"  placeholder="Username" required autofocus />
        <input type="password" name="password" value="<s:property value="password"/>" placeholder="Password" required autofocus />
        <label><strong>Login as :</strong></label>
        <s:radio label="role" cssClass="radio-inline" name="role" list="#{'Student':'Student','Guest':'Guest', 'Staff':'Staff'}" value="3" required="true"/>

        <div cssClass="wrapper">
              <span cssClass="group-btn">
                    <s:submit cssClass="btn btn-primary btn-large" value="Login"/>
              </span>
        </div>
    </s:form>

</div>
</body>
</html>