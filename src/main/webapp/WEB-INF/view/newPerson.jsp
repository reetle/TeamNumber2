<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
<body id="bodySolid">

<div class="container">
    <div class="box" id="addMargin">
        <div>
            <form:form method="POST" action="/app/addPerson" modelAttribute="person">
                <form:errors path="email" class="errors"/>
        </div>
        <div>
                <form:label path="email">Email</form:label>
        </div>
        <div>
                    <form:input path="email"/>
        </div>
        <div>
            <input class="button" type="submit" value="Create Account"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>