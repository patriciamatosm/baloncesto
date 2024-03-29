import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public ModeloDatos(Connection connection) {
        this.con = connection;
    }

    public ModeloDatos() {
    }

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
        return (existe);
    }

    public String getData() {

        String j = "Jugadores";
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT nombre, votos FROM Jugadores");
            j = j.concat(": ");
            while (rs.next()) {
                j = j.concat("n:" + rs.getString("Nombre").trim() + " v:" + rs.getString("Votos").trim() + "\n");
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
        return (j);
    }

    public int getVotes(String nombreJugador) throws SQLException {
        int votos = 0;
        String aux = "";
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT nombre, votos FROM Jugadores WHERE nombre = '" + nombreJugador + "'");

            if (rs.next()) {
                aux = rs.getString("Votos");
                votos = Integer.parseInt(aux);
            }
            rs.close();
            set.close();

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        
        }
        return votos;
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No inserta en la tabla
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
    }

    public void votesTo0() {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos = 0");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No inserta en la tabla
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "No lee de la tabla");
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(
                    ModeloDatos.class.getName());
            // No lee de la tabla
            logger.log(Level.INFO, "El error es: " + e.getMessage());
        }
    }

}
