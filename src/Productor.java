public class Productor extends Thread {

    private int id;
    private Monitor monitor;
    private Buffer buffer1;
    private Buffer buffer2;

    public Productor(int id, Monitor monitor, Buffer buffer1, Buffer buffer2){
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
         * Intenta hacer T2 (aregar al buffer1). El monitor devuelve:
         *
         * 1: Puedes consumir del buffer 1, sigue ese camino y has T3 al 'salir'.
         * Otro valor: El buffer1 estaba ocupado pero el buffer2 no. Has T7 al salir.
         *
         * Cuando haya producido todos los elementos que debe (cont=10 000), muere.
         *
         * Cada vez que termina de consumir duerme 200 milisegundos.
         */

        int cont = 0;
        while(cont<200) {

            if(monitor.shoot(2)==1){ //Puedo agregar al Buffer1? ...

                buffer1.add("Paquete: "+ cont+"Productot: "+id);
                System.out.println("Agregue el paquete "+cont + " al buffer 1, soy el productor "+id);
                monitor.shoot(3);
                System.out.println("Pude devolver el buffer 1 despues de meter el paquete "+ cont+ " soy el productor "+id);


            } else{         //Tengo que agregar al Buffer2 ....
                buffer2.add("Paquete: "+ cont+"Productot: "+id);
                System.out.println("Agregue el paquete "+cont + " al buffer 2, soy el productor "+ id);
                monitor.shoot(7);
                System.out.println("Pude devolver el buffer 2 despues de meter el paquete "+cont+ " soy el productor "+id);
            }

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cont++;
        }
        System.out.println("Soy un productor y TERMINE: " + id);
    }
}
