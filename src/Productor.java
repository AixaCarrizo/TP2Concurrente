import java.util.concurrent.Semaphore;

public class Productor extends Thread {

    private int id;
    private Monitor monitor;
    private Buffer buffer1;
    private Buffer buffer2;
    private Semaphore semaphore1;
    private Semaphore semaphore2;

    public Productor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Semaphore semaphore1, Semaphore semaphore2){
        this.id = id;
        this.monitor = monitor;
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        super.run();

        int cont = 0;
        int cont2 = 0;
        while(cont<10000) {

            double choose = Math.random()*100 +1;
            int index = 0;

            if(choose<50) index = 1;
            else index = 2;

            monitor.meter(1);
            monitor.meter(6);

/*
            switch (monitor.meter(index)){

                case 1:
//                    try {
//                        sleep(50);
//                        semaphore1.acquire();
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    buffer1.add(Integer.toString(id));
//                    semaphore1.release();
                    monitor.meter(6);
                    break;

                case 2:
//                    try {
//                        sleep(50);
//                        semaphore2.acquire();
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    buffer2.add(Integer.toString(id));
//                    semaphore2.release();
                    monitor.meter(7);
                    break;
            }
*/
            cont++;
            cont2++;

//            if(cont2==100) {
               // System.out.println("El prductor " + id + " ya lleva: " + cont);
//                cont2=0;
//            }
        }
        System.out.println("Soy un productor y TERMINE: " + id);
    }
}