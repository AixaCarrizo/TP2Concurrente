public class Main{

    private static Monitor monitor = new Monitor();

    public static void main(String[] args) {

        Buffer buffer1 = new Buffer();
        Buffer buffer2 = new Buffer();

        Thread hilo1 =  new Thread(new Productor(1, monitor, buffer1, buffer2)); //Creo un productor y un consumidor
        Thread hilo2 =  new Thread(new Productor(2, monitor, buffer1, buffer2)); //Creo un productor y un consumidor
        Thread hilo3 =  new Thread(new Productor(3, monitor, buffer1, buffer2)); //Creo un productor y un consumidor
        Thread hilo4 =  new Thread(new Productor(4, monitor, buffer1, buffer2)); //Creo un productor y un consumidor
        Thread hilo5 =  new Thread(new Productor(5, monitor, buffer1, buffer2)); //Creo un productor y un consumidor
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        Thread cons1 = new Thread(new Consumidor(6,monitor, buffer1, buffer2));
        Thread cons2 = new Thread(new Consumidor(7,monitor, buffer1, buffer2));
        Thread cons3 = new Thread(new Consumidor(8,monitor, buffer1, buffer2));
        Thread cons4 = new Thread(new Consumidor(9,monitor, buffer1, buffer2));
        Thread cons5 = new Thread(new Consumidor(10,monitor, buffer1, buffer2));
        Thread cons6 = new Thread(new Consumidor(11,monitor, buffer1, buffer2));
        Thread cons7 = new Thread(new Consumidor(12,monitor, buffer1, buffer2));
        Thread cons8 = new Thread(new Consumidor(13,monitor, buffer1, buffer2));
        cons1.start();
        cons2.start();
        cons3.start();
        cons4.start();
        cons5.start();
        cons6.start();
        cons7.start();
        cons8.start();

        try {
            cons1.join();
            cons2.join();
            cons3.join();
            cons4.join();
            cons5.join();
            cons6.join();
            cons7.join();
            cons8.join();
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
