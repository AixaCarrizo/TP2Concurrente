public class Consumidor extends Thread {

    private int id;
    private Monitor monitor;

    public Consumidor(int id, Monitor monitor){
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        super.run();

        while(true) {

            switch (monitor.shoot(0)) { //Intento hacer T4 y consumir lo que har en el buffer1. Que me devuele?.

                case 1: //Logré usar el buffer 1. Consumo, y hago T5 para que el Monitor sepa que me fuí.

                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    monitor.quitar(1);
                    break;
                case 2: //Tuvé que usar el buffer2 (Hice T0). Consumo y luego hago T6.

                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    monitor.quitar(2);
                    break;
                case -1:    //Ya no queda nada que consumir ni nadie que producir.
                    System.out.println("Soy un consumidor y TERMINE " + id);
                    return;
            }
        }
    }
}
