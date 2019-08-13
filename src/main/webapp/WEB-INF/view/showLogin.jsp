<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-13
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<div style="margin:10px">

    <form action="/app/person/load" method="post">

        Email : <input type="text" id="email" name="userName"/><br/>

        <input type="submit" value="Login" />

    </form>
</div>

</body>
</html>
