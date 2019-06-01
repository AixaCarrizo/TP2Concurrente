public class Main{

    private static Monitor monitor = new Monitor();

    public static void main(String[] args) {

        Thread[]prod= new Thread[5];
        Thread[]cons = new Thread[8];

        for(int i=0; i<5; i++)
        {
            Thread c =  new Thread(new Productor(i, monitor)); //Creo un productor y un consumidor
           prod[i] =  c;
           c.start();
        }

        for(int j=6 ;j<14; j++)
        {
            Thread p =  new Thread(new Consumidor(j, monitor)); //Creo un productor y un consumidor
            cons[j-6] = p;
           p.start();
        }

       // Thread log=new Thread(new Log( cons ,prod ));
        //log.start();

        try {

            for(int i=0; i<5; i++)
            {
                prod[i].join();

            }

            for(int i=0; i<8; i++)
            {
                cons[i].join();

            }
            //log.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
