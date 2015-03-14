<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
                <h4>Login Here... </h4>
                <s:form action="Welcome">
                    <s:textfield name="username" class="form-control input-sm chat-input" label="Username"  />
                    <br/>
                    <s:password name="password" class="form-control input-sm chat-input" label="Password" />
                    <br/>
                    <div class="wrapper">
                       <span class="group-btn">
                            <s:submit class="btn btn-primary btn-md" label="Login"/>
                        </span>
                    </div>
                </s:form>
        </div>
        </div>
    </div>

</div>

</body>
</html>