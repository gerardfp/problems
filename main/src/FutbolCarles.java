import java.util.ArrayDeque;
import java.util.HashMap;

public class FutbolCarles {

  // http://dis.um.es/~ginesgm/files/doc/aed/ejerc4-1.pdf - 4.57

  private final double[][] posicions;
  private final float provMax;
  private final ArrayDeque<Integer> ruta;
  private final ArrayDeque<Object> rutaMax;
  private int[][][] seleccionats;


  public FutbolCarles(double[][] posicions) {
    this.posicions = posicions;
    this.provMax = 0;
    this.ruta = new ArrayDeque<>();
    this.rutaMax = new ArrayDeque<>();
    this.seleccionats = new int[posicions.length+1][posicions.length+1][2];
  }

  public static void main(String[] args) {
    /*double[][] posicions = new double[5][5];

    for (int i = 0; i < posicions.length; i++) {
      for (int j = 0; j < posicions[i].length; j++) {
        posicions[i][j] = Math.random();
      }
    }*/

      double[][] P = {
              { 0,  0,  0,  1,  0},
              { 0,  0,  0,  0,  1},
              { 0,  1,  0,  0,  0},
              { 0,  0,  1,  0,  0},
              {0,0,0,0,0}
      };



    FutbolCarles graf = new FutbolCarles(P);
//    System.out.println(graf.maximitza());
//    System.out.println(graf.ruta);

    System.out.println(graf.maximitzaIter());
    graf.seleccionats();

  }

  private double maximitzaIter() {
    double[][] val = new double[posicions.length + 1][posicions.length + 1];

    for (int i = 0; i < val[1].length; i++) {
      val[1][i] = 1;
    }

    for (int i = 2; i <= posicions.length; i++) {
      for (int j = 1; j <= posicions.length; j++) {
        double entra = 0;
        double noEntra = val[i][j - 1];

        if (posicions[j - 1][i - 1] != 0) {
          entra = posicions[j - 1][i - 1] * val[j][j - 1];
        }

        if (entra> noEntra){
          val[i][j] = entra;
          seleccionats[i][j][0] = j;
          seleccionats[i][j][1] = j-1;
        } else {
          seleccionats[i][j][0] = i;
          seleccionats[i][j][1] = j-1;

          val[i][j] = noEntra;
        }


      }

    }
    Util.printMatrix(val);

    return val[posicions.length][posicions.length];
  }

  void seleccionats() {
    int i = seleccionats[posicions.length][posicions.length][0];
    int j = seleccionats[posicions.length][posicions.length][1];

    while(i!=0 && j!=0){
      int i_aux = seleccionats[i][j][0];
      int j_aux = seleccionats[i][j][1];

      if(i_aux!=i){
        System.out.printf("Al jugador %d li ha passat el %d\n", i, j);
      }

      i = i_aux;
      j = j_aux;
    }
  }

  private float maximitza() {
    HashMap<Integer, Float> cache = new HashMap<>();
    ruta.add(0);
    return maximitza(0, posicions.length - 1, cache);
  }

  private float maximitza(int inici, int fin, HashMap<Integer, Float> cache) {
    if (inici == fin) {
      return 1;
    } else {
      if (!cache.containsKey(inici)) {
        float prov = 0;
        for (int i = 0; i < posicions[inici].length; i++) {
          float aux = (float) posicions[inici][i];

          if (aux != 0 && !ruta.contains(i) && aux >= prov) {

            ruta.add(i);
            float value = aux * maximitza(i, fin, cache);

            if (value > prov) {
              prov = value;

              rutaMax.clear();
              rutaMax.addAll(ruta);
            }

            ruta.remove(i);
          }
        }

        cache.put(inici, prov);
      }

      return cache.get(inici);
    }
  }
}
