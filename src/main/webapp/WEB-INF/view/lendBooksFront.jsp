<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="../../GorgeousContrast.css">
    <!-- google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300&display=swap" rel="stylesheet">


</head>
<body id="bodyGradient">

<div class="container">
    <div class="box">
        <div>
            <table>
                <tr>
                    <td>
                        <form:form method="POST" action="/app/library/book_confirmation" modelAttribute="book">
                            <form:label path="bookid">Enter barcode </form:label>
                            <form:input path="bookid"/>

                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="button" type="submit" value="Lend"/>
                        </form:form>

                    </td>
                </tr>
            </table>
        </div>

        <form action="/app/person/profile" method="get">
            <button class="button" type="submit">Back to Profile</button>
        </form>

    </div>
</div>
</body>
</html>
