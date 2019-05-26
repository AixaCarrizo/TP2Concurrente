public class Consumidor extends Thread {

    private int id;
    private Monitor monitor;
    private Buffer buffer1;
    private Buffer buffer2;

    public Consumidor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2){
        this.id = id;
        this.monitor = monitor;
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
    }

    @Override
    public void run() {
        super.run();

        /**
         * Primero intenta entrar al Buffer1. Si no puede hacerlo, el monitor hace que lo intente con el Buffer2 automaticamente.
         *
         * Intenta hacer T4 (consumir del buffer1). El monitor devuelve:
         *
         * 1: Puedes consumir del buffer 1, sigue ese camino y has T5 al 'salir'.
         * 2: El Buffer1 no esta disponible pero el dos si. Has T6 al salir.
         * -1: Ya no quedan Productores generando elementos ni nada que consumir. Muere.
         *
         * Cada vez que termina de consumir duerme 100 milisegundos.
         */

        while(true) {

            switch (monitor.shoot(4)) { //Intento hacer T4 y consumir lo que har en el buffer1. Que me devuele?.

                case 1: //Logré usar el buffer 1. Consumo, y hago T5 para que el Monitor sepa que me fuí.

                    //buffer1.remove();
                   System.out.println("Consumi del buffer 1 "+buffer1.remove());
                    monitor.shoot(5);
                    System.out.println("Devolvi el buffer 1, soy el consumidor "+id);

                    break;
                case 2: //Tuvé que usar el buffer2 (Hice T0). Consumo y luego hago T6.
                    System.out.println("Consumi del buffer 2 "+buffer2.remove());
                    monitor.shoot(6);
                    System.out.println("Devolvi el buffer 2, soy el consumidor "+id);
                    break;
                case -1:    //Ya no queda nada que consumir ni nadie que producir.
                    System.out.println("Soy un consumidor y TERMINE " + id);
                    return;
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
