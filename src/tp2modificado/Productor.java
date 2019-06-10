package tp2modificado;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Productor extends Thread {

    private int id;
    private Monitor monitor;
    private Buffer buffer1;
    private Buffer buffer2;
    private Lock lock;
//    Condition notFull1;
//    Condition notEmpty1;
//    Condition notFull2;
//    Condition notEmpty2;
    private Semaphore semaphore1;
    private Semaphore semaphore2;

    //public Productor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Semaphore semaphore1, Semaphore semaphore2){
    public Productor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Lock lock, Semaphore semaphore1,  Semaphore semaphore2){
        this.id = id;
        this.monitor = monitor;
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
        this.lock=lock;
//        this.notFull1=notFull1;
//        this.notEmpty1=notEmpty1;
//        this.notFull2=notFull2;
//        this.notEmpty2=notEmpty2;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        super.run();

        int cont = 0;
        int cont2 = 0;
        while(cont<100) {

            double choose = Math.random()*100 +1;
            int index = 0;

            if(choose<50) index = 1;
            else index = 2;
//            lock.lock();
            
            switch (monitor.shoot(index)){
                case 1:
//                	lock.unlock();
                    try {
                        sleep(50);
//                        semaphore1.acquire();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    lock.lock();
                    buffer1.add(Integer.toString(id));
//                    semaphore1.release();
                    monitor.agregar(1);
//                    lock.unlock();
                    break;

                case 2:
//                	lock.unlock();
                    try {
                        sleep(50);
//                        semaphore2.acquire();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    lock.lock();
                    buffer2.add(Integer.toString(id));
//                    semaphore2.release();
                    monitor.agregar(2);
//                    lock.unlock();
                    break;
            }

            cont++;
            cont2++;

            if(cont2==500) {
                System.out.println("El prductor " + id + " ya lleva: " + cont);
                cont2=0;
            }
        }
        System.out.println("Soy un productor y TERMINE: " + id);
    }
}