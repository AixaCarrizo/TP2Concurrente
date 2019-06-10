package tp2modificado;

import java.util.ArrayDeque;
import java.util.Queue;

public class Buffer {

    private Queue<String> cola;


    public Buffer() {

        cola = new ArrayDeque<>();
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