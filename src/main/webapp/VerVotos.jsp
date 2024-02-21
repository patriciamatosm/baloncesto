<!DOCTYPE html>
<html lang="en">

<head>
    <title>Votaci&oacute;n mejor jugador liga ACB</title>
    <link href="estilos.css" rel="stylesheet" type="text/css" />
</head>

<body class="resultado">

    Votaci&oacute;n al mejor jugador de la liga ACB
    <hr>
    <%@ page import="java.util.ArrayList" %>
    <% ArrayList<Jugadores> inventoryList= (ArrayList<Jugadores>)session.getAttribute("jugadores"); %>
            <% for(Jugadores jj: inventoryList) { System.out.println(jj); } %>
                <br>
                <br> <a href="index.html"> Ir al comienzo</a>
</body>

</html>