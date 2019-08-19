<!DOCTYPE html>
<html>
<head>

    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="stylesheet.css">
    <!-- google fonts-->
  <%--  <link href="https://fonts.googleapis.com/css?family=Montserrat:300&display=swap" rel="stylesheet">--%>
    <link href="https://fonts.googleapis.com/css?family=Exo:800|Montserrat:300&display=swap" rel="stylesheet">



</head>
<body id="bodySolid">

<div class="row">
    <div class="col-sm-4">
        <div>
            <form action="/app/person/login" method="get">
                <button class="button" type="submit">Login</button>
            </form>
        </div>
        <div>
            <form action="/app/addPerson" method="GET">
                <button class="button" type="submit">Create Account</button>
            </form>
        </div>
    </div>
    <div class="col-sm-8">
        <div>
            <h1>
                <span>BOOK</span>
                <span>SELF</span>
            </h1>
        </div>

    </div>
</div>
</body>
</html>