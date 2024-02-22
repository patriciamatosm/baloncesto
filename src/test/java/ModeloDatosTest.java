import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.sql.Connection;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModeloDatosTest {

    private ModeloDatos modeloDatos;

    private void insertarDatosDePrueba() {
        // Inserta datos de prueba en la base de datos (simulación)
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baloncesto", "usuario",
                "clave")) {
            String insertQuery = "INSERT INTO Jugadores (nombre, votos) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, "Llull");
                statement.setInt(2, 0);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar datos de prueba en la base de datos");
        }
    }

    private int getVotes(String nombreJugador) throws SQLException {
        int votos = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baloncesto", "usuario",
                "clave")) {

            String query = "SELECT votos FROM Jugadores WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "nombreJugador");
                statement.setInt(2, 0);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    votos = resultSet.getInt("votos");
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return votos;
    }

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        ModeloDatos instance = new ModeloDatos();
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @BeforeAll
    public void setUp() throws SQLException {
        // Crea una instancia de ModeloDatos antes de cada prueba
        modeloDatos = new ModeloDatos();

        // Abre la conexión a la base de datos de prueba
        modeloDatos.abrirConexion();

        // Inserta datos de prueba en la base de datos
        insertarDatosDePrueba();
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Cerrar la conexión y limpiar los recursos
        modeloDatos.cerrarConexion();
    }

    @Test
    public void testVote() throws SQLException {
        // Obtén el número de votos del jugador antes de llamar a actualizarJugador()
        int votosAntes = getVotes("Llull");

        // Llama al método actualizarJugador()
        modeloDatos.actualizarJugador("Llull");

        // Obtén el número de votos del jugador después de llamar a actualizarJugador()
        int votosDespues = getVotes("Llull");

        // Comprueba que los votos se han incrementado en 1
        assertEquals(votosAntes + 1, votosDespues, "Los votos del jugador no se han incrementado en 1");
    }

}
