<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head></head>
    <body>
         <h3>Nimekiri</h3>
         <p>${person.firstName}</p>
         <h3>Uus isik</h3>
         <form:form method="POST" action="/app/person/save" modelAttribute="person">
            <table>
                <tr>
                    <td><form:label path="firstName">Eesnimi</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Salvesta"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>