public class Nodo {
    int x, y;
    String etiqueta;
   Nodo izquierda, derecha, medioizq, medioder;

    public Nodo(int x, int y, String etiqueta) {
        this.x = x;
        this.y = y;
        this.etiqueta = etiqueta;
        this.izquierda = null;
        this.derecha = null;
        this.medioizq = null;
        this.medioder = null;
    }

    @Override
    public String toString() {
        return etiqueta + " (" + x + ", " + y + ")";
    }
}