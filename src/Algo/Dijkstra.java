package Algo;

/**
 * Created by 鬼才 on 2015/12/12 0012.
 */
public class Dijkstra {
    public static int[] pi; //predecessor
    //    public static int[][] G;
//    public static int[] dis;
    public static int[] heap;
    public static int[] heapIndex;
    public static int heapLen;

    public static void main(String[] args) throws Exception {
//        System.out.print("\uff65");
//        System.out.print("\u2022");
        Edge[][] map = Map.makeMap();
//        System.out.println(dijAlgo(map, 'B' - 'A', 1, 'Q' - 'A'));
        //exchange source with tgt
//        System.out.println(parsePi(map, dijAlgo1(map, 'E' - 'A', 1), 1, 'E' - 'A', 'Q' - 'A', true));
//        System.out.println(dijAlgo(map, 'A' - 'A', 2, 'B' - 'A'));
//        AllPairs.allPairDij(map, 0);
//        AllPairs.allPairDij(map, 1);
//        AllPairs.allPairDij(map, 2);
        TargetFixed.targetFixed(map, 2, 'Q'-'A');
    }

    public static int[] dijAlgo1(Edge[][] G, int tgt, int mode) {
        /**
         * notice!
         * this function serves the method 2
         * the next fun to call is parsePi with the argument true
         */

        int n = G.length;
        pi = new int[n];
        heapIndex = new int[n];
        heap = new int[n];
        int i = 0;
        for (; i < n; i++) {
            heap[i] = Integer.MAX_VALUE;
            pi[i] = -1;                         //initial value
            heapIndex[i] = i;
        }
        compPi(G, mode, tgt, true);
        System.out.print("algo1 exit");
        /**
         * !important  true: tgt as the target
         * need not to reverse the string
         */
//        parsePi(G, pi, )
        return pi;
    }

    public static int[] dijAlgo(Edge[][] G, int src, int mode) {
        int n = G.length;
        pi = new int[n];
        heapIndex = new int[n];
        heap = new int[n];
        int i = 0;
        for (; i < n; i++) {
            heap[i] = Integer.MAX_VALUE;
            pi[i] = -1;                         //initial value
            heapIndex[i] = i;
        }

        compPi(G, mode, src, false);
        return pi;
//        for (i = 0; i < n; i++) {
//            System.out.print("\n" + heapIndex[i] + ":" + heap[i]);
//        }
//        if (pi[tgt] == -1) {
//            return "Can not reach " + (char) ('A' + tgt);
//        }
//        return parsePi(G, pi, mode, src, tgt, false);
    }

    //    public static void compPiReverse(Edge[][] G, int mode, int src) {
//
//    }
    public static void compPi(Edge[][] G, int mode, int src, boolean isReverse) {   //reverse(default) is false
        int i;
        int n = G.length;
        heap[src] = 0;
        heapLen = n;
        //initialize the Min_Heap
        buildMinHeap();
        while (heapLen > 0) {
            //TODO bug is here!
            int u = extractMin();   //len-- u is the index
//            System.out.print("u=" + u + "\n");

            for (i = 0; i < n; i++) {
                int index = heapIndex[i];
                Edge edge;
                if (isReverse) {
                    edge = G[index][u];
                } else {
                    edge = G[u][index];    // V[u]-->V[index]
                }
                if (edge != null) {
                    /** mode
                     * 0: walk
                     * 1: bus
                     * 2: drive
                     */
                    switch (mode) {
                        case 0:
                            if (edge.busOnly || edge.carOnly) {
                                // A<-->B<-->C  or  K<-->T  or  R<-->Z
                                break;
                            }
                            if (edge.distance != 0 & edge.distance + heap[heapLen] < heap[i]) {
                                heap[i] = heap[heapLen] + edge.distance;
                                pi[index] = u;
                                // maintain the Min_Heap
                                buildMinHeap();
                            }
                            break;
                        case 1:
                            /**
                             * ## bus ##
                             * time is the metrics
                             * can't walk through the roads which can only pass car or bus
                             *
                             */
                            if (edge.carOnly && !edge.canBus) {
                                break;
                            }
                            /*TODO*/
                            if (edge.canBus) {
                                //contain the cases that onlyBus is true
                                //the speed of bus is certainly larger than walking
                                int dis = edge.busTime*70;  //m
                                if (dis + heap[heapLen] < heap[i]) {
                                    heap[i] = heap[heapLen] + dis;
                                    pi[index] = u;
                                    buildMinHeap();
                                }
                            } else {
                                //can only walk
//                                int time = edge.distance;  //minutes
                                if (edge.distance + heap[heapLen] < heap[i]) {
                                    heap[i] = heap[heapLen] + edge.distance;
                                    pi[index] = u;
                                    buildMinHeap();
                                }
                            }
                            break;
                        default:
                            /**
                             * ## car ##
                             * metrics: distance
                             * can't pass through walkOnly road
                             */
                            if (edge.walkOnly || edge.busOnly) {
                                break;
                            }
                            if (edge.distance + heap[heapLen] < heap[i]) {
                                heap[i] = heap[heapLen] + edge.distance;
                                pi[index] = u;
                                // maintain the Min_Heap
                                buildMinHeap();
                            }
                            break;
                    }
                }
            }
        }
    }

    public static String parsePi(Edge[][] G, int[] pI, int mode, int src, int tgt, boolean isReverse) {
        String ret = " ";
        int sum = 0;
        int sumTime = 0;
        int a;
        switch (mode) {

            case 1:
                while (tgt != src) {
/**modified*/       ret += (char) ('A' + tgt) + (!isReverse ? " >-- " : " --> ");
                    a = pI[tgt];
                    Edge x = G[a][tgt];
                    if (x.canBus) {
                        sumTime += x.busTime;
                    } else {
                        sumTime += x.distance / 70;
                    }
                    tgt = a;
                }
                ret += (char) (tgt + 'A');
/**modified*/   if (!isReverse) {
                    ret = reverse(ret);
                }
                ret += "\n\tSum:" + sumTime + "min.";
                break;
            case 0:     // fall through
            default:
                while (tgt != src) {
/**modified*/       ret += (char) ('A' + tgt) + (!isReverse ? " >-- " : " --> ");
                    a = pI[tgt];
                    sum += G[a][tgt].distance;
                    tgt = a;
                }
                ret += (char) (tgt + 'A');
/**modified*/   if (!isReverse) {
                    ret = reverse(ret);
                }
                ret += "\n\tSum:" + sum + "m.";
                break;
        }
        return ret;
    }

    //change the string to a reverse order
    public static String reverse(String toReverse) {
        char[] array = toReverse.toCharArray();
        int h = 0;  //head
        int t = array.length - 1; //tail
        char tmp;
        while (h < t) {
            tmp = array[h];
            array[h++] = array[t];
            array[t--] = tmp;
        }
        String ret = new String(array);
        return ret;
    }

    //the following methods maintain a Min_Heap
    public static void buildMinHeap() {
        int n = heapLen;
        for (int i = n - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public static int extractMin() {
        int rs = heapIndex[0];
        int tmp = heap[0];
        heap[0] = heap[heapLen - 1];
        heap[heapLen - 1] = tmp;
        tmp = heapIndex[0];
        heapIndex[0] = heapIndex[heapLen - 1];
        heapIndex[heapLen - 1] = tmp;
        heapLen--;
        minHeapify(0);
        return rs;  //rs is the index of the least vertex
    }

    public static void minHeapify(int i) {
        //每次把最小的换上来 到i   保证i的子树满足优先级规则
        int l = left(i);
        int r = right(i);
        int n = heapLen;
        int least = i;
        if (l < n && heap[l] < heap[i]) {
            least = l;
        }
        if (r < n && heap[r] < heap[least]) {
            least = r;
        }
        if (least != i) {
            //exchange
            int tmp = heap[i];
            heap[i] = heap[least];
            heap[least] = tmp;
            tmp = heapIndex[i];
            heapIndex[i] = heapIndex[least];
            heapIndex[least] = tmp;

            minHeapify(least);
        }
    }

//    public static int parent(int i) {   //seems not used
//        return (i - 1) / 2;
//    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }
}
