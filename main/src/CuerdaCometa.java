import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CuerdaCometa {

    //    static Scanner sc = new Scanner(System.in);
    static Scanner sc;

    static {
        try {
            sc = new Scanner(new File("p31"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(sc.hasNextInt()){
            int N = sc.nextInt();
            int L = sc.nextInt();

            int[] lengs = new int[N];
            int[] costs = new int[N];

            for (int i = 0; i < N; i++) {
                lengs[i] = sc.nextInt();
                costs[i] = sc.nextInt();
            }

            System.out.println(cuerdaCometaRecursiu(lengs, costs, N, L));
        }
    }

    static String cuerdaCometaRecursiu(int[] lengs, int[] costs, int N, int L){
        long numPosibles = numPosiblesRecursiu(lengs, 0, N, L);
        if(numPosibles <= 0){
            return "NO";
        } else {
            long[] cc = minCordelesYCosteRecursiu(lengs, costs, 0, N, L);
            return "SI " + numPosibles + " " + cc[0] + " " + cc[1];
        }
    }

    static long numPosiblesRecursiu(int[] a, int i, int n, int L){
        if(i == n){
            return 0;
        }

        if(a[i] == L){
            return 1 + numPosiblesRecursiu(a, i + 1, n, L);
        } else if(a[i] > L) {
            return numPosiblesRecursiu(a, i + 1, n, L);
        } else {
            return numPosiblesRecursiu(a, i + 1, n, L - a[i]) + numPosiblesRecursiu(a, i + 1, n, L);
        }
    }

    static long[] minCordelesYCosteRecursiu(int[] a, int[] c, int i, int n, int L){
        if(i == n){
            return new long[]{Long.MAX_VALUE, Long.MAX_VALUE};
        }

        if(a[i] == L){
            return new long[]{1, c[i]};
        } else if(a[i] > L) {
            return minCordelesYCosteRecursiu(a, c, i + 1, n, L);
        } else {
            long[] val1 = minCordelesYCosteRecursiu(a, c, i + 1, n, L - a[i]);
            long[] val2 = minCordelesYCosteRecursiu(a, c, i + 1, n, L);
            if(val1[1] == Long.MAX_VALUE){
                return val2;
            } else {
                return new long[]{ Math.min(val1[0] + 1, val2[0]), Math.min(val1[1] + c[i], val2[1])};
            }
        }
    }

//    static long minCordelesRecursiu(int[] a, int i, int n, int L){
//        if(i == n){
//            return Long.MAX_VALUE;
//        }
//
//        if(a[i] == L){
//            return 1;
//        } else if(a[i] > L) {
//            return minCordelesRecursiu(a, i + 1, n, L);
//        } else {
//            long min1 = minCordelesRecursiu(a, i + 1, n, L - a[i]);
//            long min2 = minCordelesRecursiu(a, i + 1, n, L);
//            if(min1 == Long.MAX_VALUE){
//                return min2;
//            } else {
//                return Math.min(min1 + 1, min2);
//            }
//        }
//    }
//
//    static long minCosteRecursiu(int[] a, int[] c, int i, int n, int L){
//        if(i == n){
//            return Long.MAX_VALUE;
//        }
//
//        if(a[i] == L){
//            return c[i];
//        } else if(a[i] > L) {
//            return minCosteRecursiu(a, c, i + 1, n, L);
//        } else {
//            long min1 = minCosteRecursiu(a, c, i + 1, n, L - a[i]);
//            long min2 = minCosteRecursiu(a, c, i + 1, n, L);
//            if(min1 == Long.MAX_VALUE){
//                return min2;
//            } else {
//                return Math.min(min1 + c[i], min2);
//            }
//        }
//    }



}


/*
https://www.aceptaelreto.com/problem/statement.php?id=294&cat=31

Para cada caso, aparece el número N de cordeles y la longitud L de la cuerda de la cometa a formar.
A continuación aparecen N líneas con la descripción de cada cordel: su longitud y su coste.

Para cada caso de prueba, si es posible formar la cuerda deseada, se escribirá SI seguido de:
- número total de maneras de conseguir esa cuerda,
- mínimo número posible de cuerdas a utilizar,
- mínimo coste necesario.
Se garantiza que ninguno de esos números será mayor de 1018.
Si no es posible formar la cuerda se escribirá, simplemente, NO.
En todos los casos, suponer que la realización de cada nudo no supone reducción alguna en la longitud de los cordeles.
Además, ten en cuenta que al contar el número de formas de conseguir la cuerda,
el orden en el que se unan los cordeles es irrelevante;
*/
