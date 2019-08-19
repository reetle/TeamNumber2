<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title></title>


</head>
<body id="bodyGradient">

<div class="container">
    <div class="box">
        <div>
            <form:form method="POST" action="/app/book/save" modelAttribute="book">
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