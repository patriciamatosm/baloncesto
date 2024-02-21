public class Jugadores {
    private String nombre;
    private int voto;

    public Jugadores(String nombre, int voto){
        this.nombre = nombre;
        this.voto = voto;
    }

    public String getNombre() {
        return this.nombre;
    }


    public int getVoto() {
        return this.voto;
    }
}
