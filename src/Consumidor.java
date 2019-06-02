import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {

    private int id;
    private Monitor monitor;
    private Buffer buffer1;
    private Buffer buffer2;
    private Semaphore semaphore1;
    private Semaphore semaphore2;

    public Consumidor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2, Semaphore semaphore1, Semaphore semaphore2){
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

        while(true) {

            double choose = Math.random()*100 +1;
            int index = 0;

            if(choose<50) index = 0;
            else index = 3;

            switch (monitor.shoot(index)) {

                case 1:
                case 0:

                    try {
                        sleep(50);

                        semaphore1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    buffer1.remove();

                    semaphore1.release();

                    monitor.quitar(1);
                    break;

                case 2:
                case 3:

                    try {
                        sleep(50);

                        semaphore2.acquire();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    buffer2.remove();

                    semaphore2.release();

                    monitor.quitar(2);
                    break;

                case -1:
                    System.out.println("Soy un consumidor y TERMINE " + id);
                    return;
            }
        }
    }
}
