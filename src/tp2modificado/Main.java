package tp2modificado;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main{
	static Lock lock=new ReentrantLock();
    private final static Condition notFull1 = lock.newCondition();
    private final static Condition notEmpty1 = lock.newCondition();
    private final static Condition notFull2 = lock.newCondition();
    private final static Condition notEmpty2 = lock.newCondition();
    private static Monitor monitor = new Monitor(lock, notFull1, notEmpty1, notFull2, notEmpty2);


    public static void main(String[] args) {

        Buffer buffer1 = new Buffer();
        Buffer buffer2 = new Buffer();
       
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);

        Thread[]prod= new Thread[5];
        Thread[]cons = new Thread[8];

        for(int i=0; i<5; i++)
        {
        	prod[i] =new Productor(i, monitor, buffer1, buffer2, lock, semaphore1, semaphore2); //Creo un productor y un consumidor
        	//prod[i] =new Productor(i, monitor, buffer1, buffer2, lock);
        	prod[i].start();
        }

        for(int j=6 ;j<14; j++)
        {
        	 cons[j-6] =new Consumidor(j, monitor, buffer1, buffer2, lock, semaphore1, semaphore2); //Creo un productor y un consumidor
        	// cons[j-6] =new Consumidor(j, monitor, buffer1, buffer2, lock);
        	 cons[j-6].start();
        }

        Thread log=new Thread(new Log(buffer1, buffer2, cons  ));
        log.start();

        try {

            for(int i=0; i<5; i++)
            {
                prod[i].join();

            }

            for(int i=0; i<8; i++)
            {
                cons[i].join();

            }
            log.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}