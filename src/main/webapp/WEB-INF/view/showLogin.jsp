<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-13
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<body>


<h3>Log in</h3>

<form:form method="POST" action="/app/person/load" modelAttribute="person">
    <table>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="Salvesta"/></td>
        </tr>
    </table>
</form:form>





</body>
</html>