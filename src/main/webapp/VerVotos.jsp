<!DOCTYPE html>
<html lang="en">

<head>
    <title>Votaci&oacute;n mejor jugador liga ACB</title>
    <link href="estilos.css" rel="stylesheet" type="text/css" />
</head>

<body class="resultado">

    Votaci&oacute;n al mejor jugador de la liga ACB
    <hr>
    <% ArrayList<Jugadores> j=(ArrayList<Jugadores>) session.getAttribute("jugadores"); %>
            <c:forEach var="j" items="${pageScope.variables}">
                <%=j.nombre%> : <%=j.voto%> <br> <br>
            </c:forEach>

            <br>
            <br> <a href="index.html"> Ir al comienzo</a>
</body>

</html>