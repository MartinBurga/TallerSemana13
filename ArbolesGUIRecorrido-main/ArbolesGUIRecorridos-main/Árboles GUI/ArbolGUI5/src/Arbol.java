import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Arbol {
    private Nodo raiz;
    private ArrayList<Nodo> nodos;
    private int numNodos;

    public Arbol() {
        raiz = null;
        nodos = new ArrayList<>();
        numNodos = 0;
    }

    public void anadirNodo(Nodo nodo, Nodo padre, String posicion) {
        if (padre == null) {
            if (raiz == null) {
                raiz = nodo;
                nodos.add(nodo);
            } else {
                throw new IllegalArgumentException("La raíz ya existe");
            }
        } else {
            switch (posicion) {
                case "Izquierda":
                    if (padre.izquierda == null) {
                        padre.izquierda = nodo;
                    } else {
                        throw new IllegalArgumentException("Nodo izquierdo ya existe.");
                    }
                    break;
                case "Derecha":
                    if (padre.derecha == null) {
                        padre.derecha = nodo;
                    } else {
                        throw new IllegalArgumentException("Nodo derecho ya existe.");
                    }
                    break;
                case "Media-Izquierda":
                    if (padre.medioizq == null) {
                        padre.medioizq = nodo;
                    } else {
                        throw new IllegalArgumentException("Nodo media izquierda ya existe.");
                    }
                    break;
                case "Media-Derecha":
                    if (padre.medioder == null) {
                        padre.medioder = nodo;
                    } else {
                        throw new IllegalArgumentException("Nodo media derecha ya existe.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Posición inválida");
            }
            nodos.add(nodo);
        }
    }

    public ArrayList<Nodo> getNodos() {
            return nodos;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public String getEtiquetaNodoSiguiente() {
        return String.valueOf((char) ('A' + numNodos++));
    }

    public String bfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.izquierda != null) queue.add(nodo.izquierda );
            if (nodo.derecha != null) queue.add(nodo.derecha);
            if (nodo.medioizq != null) queue.add(nodo.medioizq);
            if (nodo.medioder != null) queue.add(nodo.medioder);
        }

        return resultado.toString().trim();
    }

    public String dfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.derecha != null) stack.push(nodo.derecha );
            if (nodo.izquierda != null) stack.push(nodo.izquierda);
            if (nodo.medioizq !=null) stack.push(nodo.medioizq);
            if(nodo.medioder!=null) stack.push(nodo.medioder);
        }

        return resultado.toString().trim();
    }


    public Object[][] getMatrizAdyacencia() {
        int tam = nodos.size();
        Object[][] matriz = new Object[tam][tam];
        Map<String, Integer> etiquetaAIndice = new HashMap<>();

        for (int i = 0; i < tam; i++) {
            etiquetaAIndice.put(nodos.get(i).etiqueta, i);
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Nodo nodo : nodos) {
            int desdeIndice = etiquetaAIndice.get(nodo.etiqueta);
            if (nodo.izquierda != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.izquierda.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.derecha!= null) {
                int hastaIndice = etiquetaAIndice.get(nodo.derecha.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.medioizq != null){
                int hastaIndice = etiquetaAIndice.get(nodo.medioizq.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.medioder != null){
                int hastaIndice = etiquetaAIndice.get(nodo.medioder.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
        }

        return matriz;
    }
}
