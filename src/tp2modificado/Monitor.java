package tp2modificado;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class Monitor {

    private int contProd = 0;
    private int contCons=0;
    private Lock lock;
    private PN pn = new PN();
    Condition notFull1;
    Condition notEmpty1;
    Condition notFull2;
    Condition notEmpty2;
    
    public Monitor(Lock lock,Condition notFull1,Condition notEmpty1,Condition notFull2,Condition notEmpty2) {
    	 this.lock=lock;
         this.notFull1=notFull1;
         this.notEmpty1=notEmpty1;
         this.notFull2=notFull2;
         this.notEmpty2=notEmpty2;
    }

    public int shoot(int index){  //Dispara una transicion (index)
    	lock.lock();
        while (!pn.isPos(index)) {

            switch (index) {

                case 0:
                	try {
    					notEmpty1.await();
    				} catch (InterruptedException e4) {
    					// TODO Auto-generated catch block
    					e4.printStackTrace();
    				}
                	
                	
                    if (pn.isPos(3)) {
                    	lock.unlock();
                    	return 2;
                    }
                    else {
                    	try {
							notEmpty2.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        lock.unlock();
                    }
                    break;

                case 3:
    				try {
    					notEmpty2.await();
    				} catch (InterruptedException e3) {
    					// TODO Auto-generated catch block
    					e3.printStackTrace();
    				}
    				
			
                    if (pn.isPos(0)) {
                    	lock.unlock();
                    	return 1;
                    }
                    else {
                    	try {
							notEmpty1.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	lock.unlock();
                    }

                    break;

                case 1:
        			try {
    					notFull1.await();
    				} catch (InterruptedException e2) {
    					// TODO Auto-generated catch block
    					e2.printStackTrace();
    				}
                    if (pn.isPos(2)) {
                    	lock.unlock();
                    	return 2;
                    }
                    else {
            			try {
        					notFull2.await();
        				} catch (InterruptedException e2) {
        					// TODO Auto-generated catch block
        					e2.printStackTrace();
        				}
                      lock.unlock();
                    }
	

                    break;

                case 2:
                	try {
						notFull2.await();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                    if (pn.isPos(1)) {
                    	lock.unlock();
                    	return 1;
                    }
					else {
						try {
							notFull1.await();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						lock.unlock();
					}

                    break;
            }

         //   try {
                if (contProd == 500 && contCons == 500) { //Esto solo le interesa al Consumidor. El Productor muere solo.
                    
//                	notifyAll();
                    lock.unlock();
                    return -1;
                }
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
           // lock.unlock();
        
        }

     //   notifyAll();
        lock.unlock();
        return index; //Logro agregar en el buffer que intentó incialmente
 
    }

    public void agregar(int idBuffer){
    	lock.lock();
       contProd++;

        if(idBuffer==1) {
        	notEmpty1.signal();
        	pn.isPos(6);
        }

        else { 
        	pn.isPos(7);
      	notEmpty2.signal();
        }
       // notifyAll();
        lock.unlock();
    }

    public void quitar(int idBuffer){
    	lock.lock();
        contCons++;

        if(idBuffer==1) {
         	notFull1.signal();
        	pn.isPos(5);
        }
        else {
        	notFull2.signal();
        	pn.isPos(4);
        }
    


      //  notifyAll();
        lock.unlock();
    }
}