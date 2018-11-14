public class Futbol {

    // TODO: path

    public static void main(String[] args) {
        double[][] P = new double[12][12];
        P[0][1] = 0.9;
        P[0][2] = 0.7;
        P[1][2] = 0.6;
        P[1][4] = 0.1;
        P[2][3] = 0.5;
        P[2][5] = 0.1;
        P[2][11] = 0.2;
        P[3][0] = 0.9;
        P[3][2] = 1;
        P[3][5] = 0.8;
        P[4][11] = 0.7;
        P[5][4] = 0.3;
        P[5][11] = 0.6;


//        double[][] P = new double[5][5];
//        P[0][3] = 1;
//        P[1][4] = 1;
//        P[2][1] = 1;
//        P[3][2] = .1;

//        double[][] P = {
//                { 0,  0,  0,  1,  0},
//                { 0,  0,  0,  0,  1},
//                { 0,  1,  0,  0,  0},
//                { 0,  0,  1,  0,  0},
//                {0,0,0,0,0}
//        };

//        double[][] P = {
//                { 0,  0,  0,  1,  0},
//                { 0,  0,  1,  0,  1},
//                { 0,  0,  0,  0,  1},
//                { 0,  1,  0,  0,  0},
//                {0,0,0,0,0}
//        };

//        double[][] P = {
//                { 0, .9, .1, .0, .0},
//                {.0,  0, .9, .1, .0},
//                {.1, .0,  0, .9, .1},
//                {.0, .0, .0,  0, .9},
//                {0,0,0,0,0}
//        };

//        double[][] P = {
//                { 1, .5, .4, .0, .0},
//                {.0,  1, .5, .1, .1},
//                {.4,  0,  1, .9, .5},
//                {.0, .0, .0,  1, .1},
//                {0,0,0,0,1}
//        };

        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P.length; j++) {
                if(i==j)P[i][j] = 1;
            }

        }
        //System.out.println(optima(P));
        System.out.println(floydWarshall(P));
    }

    static double optima(double[][] P){
        double[][] K = new double[P.length+1][P.length+1];
        for (int i = 0; i <= P.length; i++) {
            K[i][0] = 0;
        }

        K[0][1] = 1;  // la jugada se inicia en el portero (desde fora del camp, soloes se pot pasar al portero)

        for (int i = 1; i < P.length+1; i++) {
            for (int j = 1; j < P.length+1; j++) {
                double max = Double.NEGATIVE_INFINITY;
                for (int k = 0; k < i; k++) {
                    double prob = K[i-1][k+1] * P[k][j-1];
                    if (prob > max) {
                        max = prob;
                    }
                }
                K[i][j] = max;
            }
        }
        Util.printMatrix(K);
        return K[P.length][P.length];
    }


    static double floydWarshall(double graph[][]) {
        double dist[][] = new double[graph.length][graph.length];
        int i, j, k;

        for (i = 0; i < graph.length; i++)
            for (j = 0; j < graph.length; j++)
                dist[i][j] = graph[i][j];

        for (k = 0; k < graph.length; k++) {
            for (i = 0; i < graph.length; i++) {
                for (j = 0; j < graph.length; j++) {
                    if (dist[i][k] * dist[k][j] > dist[i][j])
                        dist[i][j] = dist[i][k] * dist[k][j];
                }
            }
        }

        return dist[0][graph.length-1];
    }
}

/*
http://dis.um.es/~ginesgm/files/doc/aed/ejerc4-1.pdf  [4.57]

 ¡El mundial se acerca y la selección española te necesita! Tenemos un
esquema del equipo con 12 posiciones, una por cada jugador (el portero es el
número 1) más una para la portería contraria (que es el número 12). Para cada par de
jugadores (a, b), tenemos la probabilidad, P[a, b] ∈ (0, 1), de que un pase desde a
hasta b salga bien, es decir, que no lo intercepte el equipo contrario. La matriz no es
simétrica, y P[a, 12] indica la probabilidad de que a marque un gol al chutar. A
partir de esas probabilidades básicas, se puede calcular la probabilidad de una
secuencia de pases, mediante el producto. La probabilidad de que la secuencia de
pases a Æ b Æ c salga bien será: P[a, b]*P[b, c], y así sucesivamente. Una
estrategia de juego es una secuencia de pases que empieza en nuestro portero y
acaba en gol en la portería contraria.
Escribir un algoritmo eficiente que encuentre la estrategia de juego óptima, es
decir, la secuencia de pases entre 1 y 12 que maximice la probabilidad de salir bien.
Aplicar el algoritmo al ejemplo de abajo, indicando la estrategia óptima y la
probabilidad asociada.


 */