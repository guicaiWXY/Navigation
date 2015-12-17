package Algo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2015/12/15 0015.
 */
public class AllPairs {
    public static int[][] routes;
    public static void allPairDij(Edge[][] G, int mode) throws Exception {
        /**
         * redirect the output to a file
         */
        String add;
        switch (mode) {
            case 0: add = "Walk";
                break;
            case 1: add = "Bus";
                break;
            default: add = "Drive";
                break;
        }
        String fileName = "Mode" + add + "Method3.txt";
        File file = new File(fileName);
        if(file.exists()) {
            return;
        }
        PrintStream console = System.out;
        PrintStream out = new PrintStream(new BufferedOutputStream(
                new FileOutputStream(fileName)));
        System.setErr(out);
        System.setOut(out);

        /**
         *start
         */
        int n = G.length;
        int[] pi;
        int i;
        int j;
        for(i = 0; i < n; i++) {
            /* fixed source */
            pi = Dijkstra.dijAlgo(G, i, mode);
            for (j = 0; j < n ; j++) {
                if(i == j){
                    continue;
                }
                System.out.print((char)('A'+i)+" to "+(char)('A'+j)+":");   //like:A to B:
                /**
                 * print the result of i-->j
                 */

                if (pi[j] == -1) {
                    System.out.println("Can not reach.");
                }else {
                    System.out.println(Dijkstra.parsePi(G, pi, mode, i, j, false));
                }
            }
        }
        out.close();
        System.setOut(console);
    }
}
