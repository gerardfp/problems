public class Levenshtein {

    static int levenshtein(String m, String n){
        int[][] D = new int[m.length()+1][n.length()+1];

        for (int i = 1; i <= m.length(); i++) {
            D[i][0] = D[i-1][0] + 1;
        }

        for (int j = 1; j <= n.length(); j++) {
            D[0][j] = D[0][j-1]+1;
            for (int i = 1; i <= m.length(); i++) {
                D[i][j] = Math.min(D[i-1][j]+1, Math.min(D[i][j-1]+1, D[i-1][j-1] + (m.charAt(i-1) != n.charAt(j-1) ? 1 : 0)));
            }
        }

        return D[m.length()][n.length()];
    }

    public static void main(String[] args) {
        System.out.println(levenshtein("campo", "ejemplo"));
    }
}

/*

 */