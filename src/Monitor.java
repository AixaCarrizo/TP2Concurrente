import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private int contProd = 0;
    private int contCons=0;

    private PN pn = new PN();

    private final Lock lock = new ReentrantLock();

    private final Condition noLleno = lock.newCondition();
    private final Condition noVacio = lock.newCondition();

    public int meter(int index){

        lock.lock();

        try{

            while (!pn.isPos(index)){

                //System.out.println("Prod noLleno.await " + index);

                noLleno.await();

//                switch (index) {
//
//                    case 1:
//                        if (pn.isPos(2)) index = 2;
//                        break;
//
//                    case 2:
//                        if (pn.isPos(1)) index = 1;
//                        break;
//                }
            }

           // System.out.println("Prod noVacio.signal " + index);
//
            noVacio.signal();

        }finally {

            if(index==6 || index==7) contProd++;

            if (contProd == 50000 && contCons == 50000) { //Esto solo le interesa al Consumidor. El Productor muere solo.

                noVacio.signal();

            }

            //System.out.println("Prod unlock" +  index);

            //System.out.println("Contprod: " + contProd);

            lock.unlock();
            return index;
        }


    }

    public int consumir(int index) {

        lock.lock();

        try{
            while (!pn.isPos(index)){

               // System.out.println("Consumidor noVacio.await " + index);

                noVacio.await();

//                switch (index) {
//
//                    case 1:
//                        if (pn.isPos(2)) index = 2;
//                        break;
//
//                    case 2:
//                        if (pn.isPos(1)) index = 1;
//                        break;
//                }
            }

            if (contProd == 50000 && contCons == 50000) { //Esto solo le interesa al Consumidor. El Productor muere solo.

                noVacio.signal();
                index = -1;
            }

           // System.out.println("Consumidor noLleno.signal " + index);

            noLleno.signal();

        }finally {

            if(index==5 || index==4) contCons++;

            //System.out.println("consumidor unlock  " + index);

            //System.out.println("Contcons " + contCons);

            lock.unlock();
            return index;
        }
    }



//
//
//
//    public synchronized int shoot(int index){  //Dispara una transicion (index)
//
//        while (!pn.isPos(index)) {
//
//            switch (index) {
//
//                case 0:
//                    if (pn.isPos(3)) return 2;
//                    break;
//
//                case 3:
//                    if (pn.isPos(0)) return 1;
//                    break;
//
//                case 1:
//                    if (pn.isPos(2)) return 2;
//                    break;
//
//                case 2:
//                    if (pn.isPos(1)) return 1;
//                    break;
//            }
//
//            try {
//
//                if (contProd == 50000 && contCons == 50000) { //Esto solo le interesa al Consumidor. El Productor muere solo.
//                    notifyAll();
//                    return -1;
//                }
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        notifyAll();
//
//        return index; //Logro agregar en el buffer que intentó incialmente
//    }
//
//    public synchronized void agregar(int idBuffer){
//
//       contProd++;
//
//        if(idBuffer==1) pn.isPos(6);
//        else pn.isPos(7);
//
//        notifyAll();
//    }
//
//    public synchronized void quitar(int idBuffer){
//
//        contCons++;
//
//        if(idBuffer==1) pn.isPos(5);
//        else pn.isPos(4);
//
//        notifyAll();
//    }
}