import java.util.ArrayDeque;

public class TSP {

    static private ArrayDeque<Integer> ruta;
    static private ArrayDeque<Integer> rutaMin;
    static private int costMin;
    static private int costAct;
    static private int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };

    public static void main(String[] args) {
        ruta = new ArrayDeque<Integer>();
        rutaMin = new ArrayDeque<Integer>();
        costMin = Integer.MAX_VALUE;
        costAct = 0;

        ruta.add(0);
        _minRouteBnB(0);

        System.out.println(costMin);
        System.out.println(rutaMin);
    }

    static void _minRouteBnB(int ini) {
        if (ruta.size() == graph.length) {
            if (graph[ini][0] != 0) {
                costAct += graph[ini][0];

                if (costMin > costAct) {
                    costMin = costAct;
                    rutaMin = new ArrayDeque<>(ruta);
                    rutaMin.add(0);
                }
                costAct -= graph[ini][0];

            }
        } else {
            for (int i = 0; i < graph.length; i++) {
                if(graph[ini][i] != 0 && !ruta.contains(i) && costAct + graph[ini][i] < costMin){
                    costAct += graph[ini][i];
                    ruta.add(i);
                    _minRouteBnB(i);

                    ruta.remove(i);
                    costAct -= graph[ini][i];
                }
            }
        }
    }
}

/*
 * https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/
 * Given a set of cities and distance between every pair of cities, the problem is to find the shortest possible route
 * that visits every city exactly once and returns to the starting point.
 */