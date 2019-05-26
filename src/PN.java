public class PN {

    int[]m;
    int[][] w;
    int[] S;

    public PN(int[] m, int[][] w, int[] s) {
        this.m = m;
        this.w = w;
        S = s;
    }

    public  PN(){
        init();
    }

    public void init(){

        this.m = new int[]{1, 1, 8, 0, 10, 15, 0, 0, 0, 0, 5, 0}; //Vector de marcado inicial

        /**
         * m0=Disp. Buffer1
         * m1=Disp. Buffer2
         * m2=Consumidores
         * m4=Cap. Buffer1
         * m5=Cap. Buffer2
         * m10=Productores
         */

        this.S = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; //Vector de disparo

        /**
         * Buffer1 (10):
         *
         * Producir: T2
         * Term. Producir: T3
         * Consumir: T4
         * Term. Consumir: T5
         *
         * Buffer2 (15):
         *
         * Producir: T1
         * Term. Producir: T7
         * Consumir: T0
         * Term. C0nsumir: T6
         */


        this.w = new int[][]{{0, 0, -1, 1, -1, 1, 0, 0},   // 1
                             {-1, -1, 0, 0, 0, 0, 1, 1},    // 2
                             {-1, 0, 0, 0, -1, 1, 1, 0},    // 3
                             {1, 0, 0, 0, 0, 0, -1, 0},     // 4
                             {0, 0, -1, 0, 0, 1, 0, 0},
                             {0, -1, 0, 0, 0, 0, 1, 0},
                             {0, 0, 0, 0, 1, -1, 0, 0},     // 6
                             {0, 0, 1, -1, 0, 0, 0, 0},     // 7
                             {0, 0, 0, 1, -1, 0, 0, 0},     // 8
                             {0, 1, 0, 0, 0, 0, 0, -1},     // 9
                             {0, -1, -1, 1, 0, 0, 0, 1},    // 10
                             {-1, 0, 0, 0, 0, 0, 0, 1}};    //11
    }

    public void putShot(int index){   //Coloca la transicion a disparar en el vector de disparo. Dispara de a una

        this.S = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        S[index] = 1;
    }

    public boolean isPos(int index) {   //Mediante la ecuacion de la PN devuelve un boolean que indica si se puede disparar la transicion

        putShot(index);

        int[] auxVector = new int[m.length];

        for (int i = 0; i < w.length; i++) {       //Multiplica las matrices

            int auxFila = 0;
            auxFila += w[i][index] * S[index];
            auxVector[i] = auxFila;
        }

        int[] mPrima = new int[m.length];

        for (int i = 0; i < m.length; i++) {   //Si algun numero del nuevo vector de marcado es negativo, no puedo dispararla
            mPrima[i] = m[i] + auxVector[i];    //Sumo para obtener el nuevo vector de marcado

            if (mPrima[i] == -1) return false;
        }

        this.m=mPrima;

        return true;   //Si ninguno es negativo, puedo dispararla
    }
}
