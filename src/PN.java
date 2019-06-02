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

        this.m = new int[]{8, 0, 15, 10, 0, 0, 0, 0, 5, 0}; //Vector de marcado inicial

        /**
         * m0: consumidores esperando
         * m1: consumidores en b1
         * m2: lugar libre en B2
         * m3: lugar libre B1
         * m4: consumiendo en B2
         * m5: produciendo en B2
         * m6: BUFFER2
         * m7: produciendo en B1
         * m8: productor esperando
         * m9: BUFFER1
         */

        this.S = new int[]{0, 0, 0, 0, 0, 0, 0, 0}; //Vector de disparo

        /**
         * Buffer1 (10 lugares):
         *
         * T0: Empieza a consumir en b1
         * T1: Empieza a producir en b1
         * T5: Terminó de consumir en b1
         * T6: Producí en b1
         *
         * Buffer2 (15 lugares):
         *
         * T2 : Empieza a producir en b2
         * T3: Empieza a consumir en b2
         * T4: Terminó de consumir en b2
         * T7: Producí en b2
         *
         */

        this.w = new int[][]{{-1, 0, 0, -1, 1, 1, 0, 0},   // 1
                             {1, 0, 0, 0, 0, -1, 0, 0},    // 2
                             {0, 0, -1, 0, 1, 0, 0, 0},    // 3
                             {0, -1, 0, 0, 0, 1, 0, 0},     // 4
                             {0, 0, 0, 1, -1, 0, 0, 0},
                             {0, 0, 1, 0, 0, 0, 0, -1},
                             {0, 0, 0, -1, 0, 0, 0, 1},     // 6
                             {0, 1, 0, 0, 0, 0, -1, 0},     // 7
                             {0, -1, -1, 0, 0, 0, 1, 1},     // 8
                             {-1, 0, 0, 0, 0, 0, 1, 0}};     // 9
    }

    public boolean isPos(int index) {   //Mediante la ecuacion de la PN devuelve un boolean que indica si se puede disparar la transicion

        this.S = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        S[index] = 1;

        int[] mPrima = new int[m.length];

        for (int i = 0; i < m.length; i++) {   //Si algun numero del nuevo vector de marcado es negativo, no puedo dispararla
            mPrima[i] = m[i] + w[i][index];    //Sumo para obtener el nuevo vector de marcado

            if (mPrima[i] == -1) return false;
        }

        this.m=mPrima;

        return true;   //Si ninguno es negativo, puedo dispararla
    }
}
