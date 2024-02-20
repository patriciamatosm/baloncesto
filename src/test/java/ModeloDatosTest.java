import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class ModeloDatosTest {

    private ModeloDatos instance;

    @BeforeAll
    public void setUp() throws Exception {

        instance = new ModeloDatos();
        instance.abrirConexion();

    }

    @AfterAll
    public void destroy() throws Exception {

        instance.cerrarConexion();

    }

    @Test
    public void testExisteJugador() {
        System.out.println("Prueba de existeJugador");
        String nombre = "";
        boolean expResult = false;
        boolean result = instance.existeJugador(nombre);
        assertEquals(expResult, result);
        // fail("Fallo forzado.");
    }

    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Llull";
        int before = instance.getJugador(nombre);
        instance.actualizarJugador(nombre);
        int after = instance.getJugador(nombre);
        assertEquals(before + 1, after);
    }
}
