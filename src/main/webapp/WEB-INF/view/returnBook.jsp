<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <!--<script src="quagga.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/quagga/0.12.1/quagga.min.js"></script>

</head>

<body id="bodySolid">



<div class="row">
    <div class="col-sm-4" id="left">
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
                <button class="button-active" type="submit">Returning</button>
            </form>
        </div>
        <div>
            <form action="/app/profile" method="get">
                <button class="button" type="submit">Books</button>
            </form>
        </div>
        <div>
            <form action="/" method="get">
                <button class="button" type="submit">Log Out</button>
            </form>
        </div>

    </div>

    <div class="col-sm-4" id="center">
        <div id="interactive" class="viewport">
            <video class="videoCamera" autoplay="true" preload="auto" src="" muted="true"
                   playsinline="true"></video>
            <canvas class="drawingBuffer"></canvas>
        </div>
    </div>

    <div class="col-sm-4" id="right">
        <table>
            <tr>
                <td>
                    <form:form method="POST" action="/app/book/return" modelAttribute="book">
                    <form:label path="code">Enter barcode </form:label>
                </td>
            </tr>
            <tr>
                <td><form:input id="triip" path="code"/></td>
            </tr>
            <tr>
                <td><form:errors path="code" class="errors" /></td>
            </tr>
            <tr>
                <td>
                    <input class="button" type="submit" value="Return"/>
                    </form:form>
                </td>
            </tr>
        </table>



    </div>

    <script>
        Quagga.init({
            inputStream: {
                name: "Live",
                type: "LiveStream",
                target: document.querySelector('#camera')    // Or '#yourElement' (optional)
            },
            decoder: {
                readers: ["code_128_reader", "ean_reader",
                    "ean_8_reader",
                    "code_39_reader",
                    "code_39_vin_reader"
                ]

            },

        }, function (err) {
            if (err) {
                console.log(err);
                return
            }
            console.log("Initialization finished. Ready to start");
            Quagga.start();
        });


        Quagga.onDetected(function (data) {


            <!--console.log(data.codeResult.code);-->
            var x =document.getElementById("triip");
            x.value=data.codeResult.code;


        });


    </script>
</body>
</html>