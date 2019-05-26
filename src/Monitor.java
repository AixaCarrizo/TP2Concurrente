

public class Monitor {

    private int contProd = 0;
    private int contCons=0;

    private PN pn = new PN();

    public synchronized int shoot(int index){  //Dispara una transicion (index)

        /**
         * Unico metodo de la clase Monitor que asegura la exclucion mutua entre los hilos Productor y Consumidor.
         *
         * Cuando un Productor, libre un buffer llevara a cabo las trancciones 3 y 7. Esto aumenta el valor de contProd y
         * permite al monitor saber cuantos elementos han sido agregados a los buffer en total.
         *
         * Lo mismo sucede con los Consumidores, al salir de un buffer lo haran disparando las transsiones 5 y 6.
         * El monitor lleva la cuenta de cuantos elementos han sido consumidos en contCons.
         *
         * El Consumidor o Productor dirá que transicion quiere hacer y el monitor vera si es posible.
         * Si lo es se salta el contenido del While, despierta a los posibles hilos dormidos y salen del monitor.
         * Cuando salen hay 2 opciones: O agrega/quita un elemento del buffer o se van a dormir porque ya agrego/quito algo del buffer.
         *
         * En el primer caso, Productor/Consumidor siempre intentan mirar el Buffer1 (index = 2 ó 4). En caso de no poder usar ese, miraran el Buffer2.
         * Si era un Consumidor tiene que hacer T4 para usar el Buffer2 sino es un productor (Siempre será posible hacer T3/T7/T5/T6).
         * Si fue posible usar el Buffer2 para agregar/consumir, devuele un 2.
         * Si contProd=contCons=10 000 entonces ya no queda nada que hacer, debe morir. Devuelve un -1
         * En caso de que no se cumpla ninguna de las condiciones anterior el hilo entrara a la cola del Monitor. (wait).
         */

        if(index==3 || index==7) contProd++;
        if(index==5 || index==6) contCons++;

        while (!pn.isPos(index)){

            if (index == 4) { //Si fue un consumidor el que no pudo entrar al Buffer1....

                if(pn.isPos(0)){    //Si es posible usar el Buffer2. Disparo T0.
                    notifyAll();
                    return 2;
                }
                else {
                    try {

                        if (contProd == 1000 && contCons == 1000) { //Esto solo le interesa al Consumidor. El Productor muere solo.
                            notifyAll();
                            return -1;
                        }

                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else{ //Si fue un Productor el que lo intento.

                if(pn.isPos(1)){ //Si es posible usar el Buffer2. Disparo T1.
                    notifyAll();
                    return 2;
                }
                else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //Si llego acá es porque pude hacer alguna trancicion...
        notifyAll();
        return 1;
    }
}
