
<html>
<head>
    <title>Bookshelf</title>
</head>
<body>
<h2>BOOKSHELF</h2>
<br>
<style>
    body {background-color: powderblue;}
    h2   {color: black;}
    h2   {position:absolute;
        transition: .5s ease;
        top: 5%;
    left: 40%}

    .button {
        background-color: #1c87c9;
        border: none;
        color: white;
        padding: 20px 34px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 20px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 8px;

        position:absolute;
        transition: .5s ease;
        top: 20%;
        left: 40%;
    }
    .button2 {
        background-color: #1c87c9;
        border: none;
        color: white;
        padding: 20px 34px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 20px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 8px;

        position:absolute;
        transition: .5s ease;
        top: 40%;
        left: 45%;
    }
</style>

<form action="/app/person/new" method="GET">
    <button class="button" type="submit">CREATE ACCOUNT</button>
</form>
<br>
<form action="/app/person/load" method="get">
    <button class="button2" type="submit">LOGIN</button>
</form>

<br>

<div>
    <h4>
        <a href="/app/person/load">Isikud</a>
    </h4>
</div>
</body>
</html>
