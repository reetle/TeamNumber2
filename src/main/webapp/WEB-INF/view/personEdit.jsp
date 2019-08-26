<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title></title>


</head>
<body id="bodySolid">

<div class="container">
    <div class="box">
        <div>
            <form:form method="POST" action="/app/savePersonEdit" modelAttribute="person">
            <table>
                <tr>
                    <td><form:label path="firstName">First name</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="surname">Surname</form:label></td>
                    <td><form:input path="surname"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td><form:label path="role">Role</form:label></td>
                    <td><form:input path="role"/></td>
                </tr>
                <input type="hidden" name="id" value=${person.id}>

            </table>

        </div>
        <div>
            <input class="button" type="submit" value="Save"/>
        </div>
        </form:form>
    </div>
</div>
</body>
</html>