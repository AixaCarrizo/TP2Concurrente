package tp2modificado;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumidor extends Thread {

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

   // public Consumidor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Semaphore semaphore1, Semaphore semaphore2){
    public Consumidor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Lock lock,  Semaphore semaphore1,  Semaphore semaphore2){
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

        while(true) {

            double choose = Math.random()*100 +1;
            int index = 0;

            if(choose<50) index = 0;
            else index = 3;
            
//            lock.lock();
            
            switch (monitor.shoot(index)) {
                case 1:
//                	lock.unlock();
                	break;
                case 0:
                    try {
                        sleep(50);

//                        semaphore1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    lock.lock();

                    buffer1.remove();

//                    semaphore1.release();
                    monitor.quitar(1);
//                   lock.unlock();
                    break;

                case 2:
//                	lock.unlock();
                	break;
                case 3:
//                	lock.unlock();
                    try {
                        sleep(50);

//                        semaphore2.acquire();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    lock.lock();
                    buffer2.remove();

//                    semaphore2.release();

                    monitor.quitar(2);
//                    lock.unlock();
                    break;

                case -1:
//                	lock.unlock();
                    System.out.println("Soy un consumidor y TERMINE " + id);
                    return;
            }
        }
    }
}

