import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class ModeloDatosTest {

    private ModeloDatos instance;

    @BeforeEach
    public void setUp() throws SQLException  {

        instance = new ModeloDatos();
        instance.abrirConexion();

    }

    @AfterEach
    public void destroy() throws SQLException  {

        instance.cerrarConexion();

    }

    @Test
    public void testExisteJugador() throws SQLException {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() throws SQLException {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Llull";
        int before = instance.getJugador(nombre);
        instance.actualizarJugador(nombre);
        int after = instance.getJugador(nombre);
        assertEquals(before + 1, after);
    }
}
