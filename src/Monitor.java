

public class Monitor {

    private int contProd = 0;
    private int contCons=0;

    private Buffer buffer1 = new Buffer();
    private Buffer buffer2 = new Buffer();

    private PN pn = new PN();

    public synchronized int shoot(int index){  //Dispara una transicion (index)

        while (!pn.isPos(index)){

            if (index == 0) { //Si fue un consumidor el que no pudo entrar al Buffer1....

                if(pn.isPos(3)){    //Si es posible usar el Buffer2. Disparo T0.

                    //notifyAll();
                    return 2;
                }
                else {
                    try {

                        if (contProd == 5000 && contCons == 5000) { //Esto solo le interesa al Consumidor. El Productor muere solo.

                            notifyAll();
                            return -1;
                        }

                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else{ //Si fue un Productor el que lo intento.

                if(pn.isPos(2)){ //Si es posible usar el Buffer2. Disparo T1.

                    //notifyAll();
                    return 2;
                }
                else {

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        notifyAll();
        return 1;
    }

    public synchronized void agregar(String id, int idBuffer){

       contProd++;

        if(idBuffer==1) {

            buffer1.add(id);
            pn.isPos(6);
        }
        else{

            buffer2.add(id);
            pn.isPos(7);
        }

        notifyAll();
    }

    public synchronized void quitar(int idBuffer){

        contCons++;

        if(idBuffer==1) {

            buffer1.remove();
            pn.isPos(5);
        }
        else {

            buffer2.remove();
            pn.isPos(4);
        }
        notifyAll();
    }
}
