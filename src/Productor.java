public class Productor extends Thread {

    private int id;
    private Monitor monitor;

    public Productor(int id, Monitor monitor){
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        super.run();

        int cont = 0;
        while(cont<1000) {

            if(monitor.shoot(1)==1){ //Puedo agregar al Buffer1? ...

                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                monitor.agregar(Integer.toString(id), 1);


            } else{         //Tengo que agregar al Buffer2 ....

                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                monitor.agregar(Integer.toString(id), 2);
            }
            cont++;

        }
        System.out.println("Soy un productor y TERMINE: " + id);
    }
}