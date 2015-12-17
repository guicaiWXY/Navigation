package Algo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2015/12/15 0015.
 */
public class TargetFixed {
    public static void main(String[] args) throws Exception {
        Edge[][] G = Map.makeMap();
//        targetFixed(G, 0, 'E'-'A');
//        targetFixed(G, 1, 'E'-'A');
        targetFixed(G, 2, 'E'-'A');
    }
    public static void targetFixed(Edge[][] G, int mode, int tgt) throws Exception {
        /**
         *need to transpose the matrix G
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
        String fileName = "Mode" + add + "Method2To"+(char)('A'+tgt)+".txt";
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
         * start
         */
        int[] pi = Dijkstra.dijAlgo1(G, tgt, mode);
        int i;
        int n = G.length;
        for(i = 0; i < n; i++) {
            System.out.print((char)('A'+i)+" to "+(char)('A'+tgt)+":");
            if(i == tgt) {
                System.out.println("Sum: 0");
            } else if(pi[i] != -1) {
//                pi = Dijkstra.dijAlgo1(G, tgt, mode);
                System.out.println(Dijkstra.parsePi(G, pi, mode, tgt, i, true));
            } else {
                System.out.println("Can't reach.");
            }
        }

        out.close();
        System.setOut(console);
    }
}
