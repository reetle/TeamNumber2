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
<body>
<video id="video" width="640" height="480" autoplay></video>

<button id="snap">Snap Photo</button>

<button id="save">Save Photo</button>
<canvas id="canvas" width="640" height="480"></canvas>



<div class="center margin">
   <div>
        <form:form method="POST" action="/app/addPerson" modelAttribute="person">
        <form:errors path="email" class="errors"/>
    </div>
    <div>
        <form:label path="email">Enter email</form:label>
    </div>
    <div>
        <form:input path="email"/>
    </div>
    <form:input  type="hidden" id= "web" path="image"/>
    <div>
        <input class="button" type="submit" value="Create Account"/>
        </form:form>
    </div>
</div>

<script>
    var video = document.getElementById('video');

    // Get access to the camera!
    if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        // Not adding `{ audio: true }` since we only want video now
        navigator.mediaDevices.getUserMedia({ video: true }).then(function(stream) {
            //video.src = window.URL.createObjectURL(stream);
            video.srcObject = stream;
            video.play();
        });
    }
    function convertImageToCanvas(image) {
        var canvas = document.createElement("canvas");

        canvas.width = image.width;
        canvas.height = image.height;
        canvas.getContext("2d").drawImage(image, 0, 0);

        return canvas;
    }
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var video = document.getElementById('video');

    // Trigger photo take
    document.getElementById("snap").addEventListener("click", function() {
        context.drawImage(video, 0, 0, 640, 480);
    });

    document.getElementById("save").addEventListener("click", function() {
        var dataURL = canvas.toDataURL();
        console.log(dataURL);
        var x  =document.getElementById("web");
        x.value = dataURL;

    });

</script>

</body>
</html>