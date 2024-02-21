<!DOCTYPE html>
<html lang="en">

<head>
    <title>Votaci&oacute;n mejor jugador liga ACB</title>
    <link href="estilos.css" rel="stylesheet" type="text/css" />
</head>

<body class="resultado">

    Votaci&oacute;n al mejor jugador de la liga ACB
    <hr>
    <% ArrayList<Object[]> inventoryList= (ArrayList<Object[]>)session.getAttribute("jugadores"); %>
        <% for(Object[] inventoryListObject: inventoryList) {
            System.out.println(inventoryListObject);
        } %>
            <br>
            <br> <a href="index.html"> Ir al comienzo</a>
</body>

</html>