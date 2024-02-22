import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.sql.Connection;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModeloDatosTest {

    private Connection connection;

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        //fail("Fallo forzado.");
    }


    @BeforeEach
    public void setUp() throws SQLException {
        // Establecer la conexión a la base de datos de prueba
        String dbHost = System.getenv().get("DATABASE_HOST");
        String dbPort = System.getenv().get("DATABASE_PORT");
        String dbName = System.getenv().get("DATABASE_NAME");
        String dbUser = System.getenv().get("DATABASE_USER");
        String dbPass = System.getenv().get("DATABASE_PASS");

        String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        // Insertar datos de prueba en la base de datos (simulación)
        insertTestData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Cerrar la conexión y limpiar los recursos
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testVote() throws SQLException {

        ModeloDatos modeloDatos = new ModeloDatos(connection);

        int votosAntes = getVotes("Llull");

        modeloDatos.actualizarJugador("Llull");

        int votosDespues = getVotes("Llull");

        assertEquals(votosAntes + 1, votosDespues, "Los votos del jugador no se han incrementado en 1");
    }

    private void insertTestData() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Jugadores (nombre, votos) VALUES (?, ?)")) {
            statement.setString(1, "Llul");
            statement.setInt(2, 0);
            statement.executeUpdate();
        }
    }

    private int getVotes(String nombreJugador) throws SQLException {
        int votos = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT votos FROM Jugadores WHERE nombre = ?")) {
            statement.setString(1, nombreJugador);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                votos = resultSet.getInt("votos");
            }
        }
        return votos;
    }
}
