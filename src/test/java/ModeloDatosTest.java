import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloDatosTest {

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

    @Test
    public void testActualizarJugador() {
        System.out.println("Prueba de actualizarJugador");
        String nombre = "Llull";
        ModeloDatos instance = new ModeloDatos();
        instance.insertTestData();
        int before = instance.getJugador(nombre);
        System.out.println("Before" + before);
        instance.actualizarJugador(nombre);
        int after = instance.getJugador(nombre);
        assertEquals(before + 1, after);
    }
}
