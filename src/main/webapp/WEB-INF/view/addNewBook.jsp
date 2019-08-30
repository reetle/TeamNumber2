<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="../../stylesheet.css">
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

                    <td align="center">${person.firstName} ${person.surname}</td>
                </tr>
                <tr>

                    <td align="center">${person.email}</td>
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
            <form action="/app/profile" method="get">
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

    <div class="col-md-8 center margin" >
        <div>
            <table>
                <form:form method="post" action="/app/saveNewBook" modelAttribute="book">
                <tr>
                    <td>Name</td>
                    <td><form:input path="name"  autofocus="true" /></td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td><form:input path="author" /></td>
                </tr>
                    <tr>
                        <td>Genre</td>
                        <td><form:input path="genre"/></td>
                    </tr>
                    <tr>
                        <td>Barcode</td>
                        <td><form:input path="code"/></td>
                    </tr>
            </table>
            <div>
                <input class="button" type="submit" value="Save"/>
            </div>
                    </form:form>

        </div>
    </div>
</body>
</html>