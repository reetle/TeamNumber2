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

<div class="row">
    <div class="col-sm-4 spaceAround">
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
                <button class="button-active" type="submit">Books</button>
            </form>
        </div>
        <div>
            <form action="/" method="get">
                <button class="button" type="submit">Log Out</button>
            </form>
        </div>

    </div>

    <div class="col-sm-8 center margin">
        <div>
            <form:form method="POST" action="/app/saveBookEdit" modelAttribute="book">
            <table>
                <tr>
                    <td><form:label path="author">Author</form:label></td>
                    <td><form:input path="author"/></td>
                </tr>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>

                </tr>
                <input type="hidden" name="bookid" value=${book.bookid}>

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