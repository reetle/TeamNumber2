<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="../../buttons.css">
    <!-- google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">



</head>
<body>

<div class="container">
    <div class="box">
        <div>
            <form:form method="POST" action="/app/person/save" modelAttribute="person">
            <table>
                <tr>
                    <td><form:label path="firstName">Firstname</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="surname">Lastname</form:label></td>
                    <td><form:input path="surname"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
            </table>

        </div>
        <div>
            <input class="button" type="submit" value="Create Account"/>
        </div>
        </form:form>
    </div>
</div>
</body>
</html>