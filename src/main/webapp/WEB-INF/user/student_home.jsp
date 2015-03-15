<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
<div class="container">

    <strong><a id="logout" href="/<s:property value='appName'/>/logout.action"> Log out</a></strong>

    <h4>
        Hello Student
        <s:property value="username" /><br/>
        <s:property value="role" />
    </h4>
</div>

</body>
</html>