import java.util.ArrayDeque;
import java.util.Queue;

public class Buffer {

    private Queue<String> cola;
    private int cont;

    public Buffer() {

        cola = new ArrayDeque<>();
        cont = 0;
        //Se le podria pasar como parametro el tamaño que quiero que tenga la cola
        //Por defecto es de 16.
    }

    public void add(String dato) {
        cola.add(dato);
    }

    public String remove() {
        return cola.remove();
    }

    public  int size() {
        return cola.size();
    }

}