

public class Monitor {

    private int contProd = 0;
    private int contCons=0;

    private PN pn = new PN();

    public synchronized int shoot(int index){  //Dispara una transicion (index)

        while (!pn.isPos(index)){

            if (index == 0) {

                if(pn.isPos(3)) return 2;
                else {
                    try {

                        if (contProd == 50000 && contCons == 50000) { //Esto solo le interesa al Consumidor. El Productor muere solo.
                            notifyAll();
                            return -1;
                        }

                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else{

                if(pn.isPos(2)) return 2;
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

    public synchronized void agregar(int idBuffer){

       contProd++;

        if(idBuffer==1) pn.isPos(6);
        else pn.isPos(7);

        notifyAll();
    }

    public synchronized void quitar(int idBuffer){

        contCons++;

        if(idBuffer==1) pn.isPos(5);
        else pn.isPos(4);

        notifyAll();
    }
}
