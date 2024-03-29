
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class Acb extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = (String) req.getParameter("txtNombre");
        String nombre = (String) req.getParameter("R1");
        String vote0 = (String) req.getParameter("B3");
        String showVotes = (String) req.getParameter("B4");
        String j = (String) bd.getData();

        if (vote0 != null) {
            if (vote0.equals("Poner votos a 0")) {
                bd.votesTo0();
            }
        } else if (showVotes != null) {
            j = bd.getData();
        } else {
            if (nombre.equals("Otros")) {
                nombre = (String) req.getParameter("txtOtros");
            }
            if (bd.existeJugador(nombre)) {
                bd.actualizarJugador(nombre);
            } else {
                bd.insertarJugador(nombre);
            }
        }

        if(showVotes != null){
            j = bd.getData();
            s.setAttribute("nombreCliente", j);
            res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));
        } else {

            s.setAttribute("nombreCliente", nombreP);
            // Llamada a la página jsp que nos da las gracias
            res.sendRedirect(res.encodeRedirectURL("TablaVotos.jsp"));
        }

    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
