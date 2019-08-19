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
    <link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">
    <div id="resultado"></div>

    <div id="camera"></div>

    <!--<script src="quagga.min.js"></script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/quagga/0.12.1/quagga.min.js"></script>
</head>

<body>
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

</head>
<body id="bodyGradient">

<div class="container">
    <div class="box">
        <div>
            <table>
                <tr>
                    <td>
                        <form:form method="POST" action="/app/book/return" modelAttribute="book">
                            <form:label path="bookid">Enter barcode </form:label>
                            <form:input path="bookid"/>
                    </td>
                </tr>
                <tr>
                    <td>
                            <input class="button" type="submit" value="Return"/>
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
