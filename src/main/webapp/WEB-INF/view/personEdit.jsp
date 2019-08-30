<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="/../../stylesheet.css">
    <!-- google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Exo:800|Montserrat:300&display=swap" rel="stylesheet">


</head>

<body>
<img src="https://seeklogo.com/images/T/tieto-logo-5D4F5D3319-seeklogo.com.png">
<div class="row">
    <div class="col-md-4 spaceAround">
        <div>
            <table>
                <tr>
                    <td align="center">${person2.firstName} ${person2.surname}</td>
                </tr>
                <tr>
                    <td align="center">${person2.email}</td>
                </tr>
            </table>
        </div>

        <div>
            <form action="/app/person/lend" method="get">
                <button class="button" type="submit">Lending</button>
            </form>
        </div>
        <div>
            <form action="/app/book/return" method="get">
                <button class="button" type="submit">Returning</button>
            </form>
        </div>
        <div>
            <form action="/app/library/books" method="get">
                <button class="button" type="submit">Books</button>
            </form>
        </div>
        <c:if test="${person.role=='admin'}">
            <div>
                <form action="/app/allPersons" method="get">
                    <button class="button" type="submit">Persons</button>
                </form>
            </div>
        </c:if>
        <div>
            <form action="/app/history" method="get">
                <button class="button" type="submit">History</button>
            </form>
        </div>
        <div>
            <form action="/" method="get">
                <button class="button" type="submit">Log Out</button>
            </form>
        </div>
    </div>

    <div class="col-md-8 center margin">
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