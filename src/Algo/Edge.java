/**
 * Created by 鬼才 on 2015/12/9 0009.
 */

package Algo;

public class Edge {
    /* some attribute to describe the edge
     *  */
    public boolean canBus = false;
    public boolean walkOnly = false;
    public boolean carOnly = false;
    public boolean busOnly = false; //only between K & T; R & Z
//    public boolean oneDirec = false;
    public int start;
    public int end;
    public int distance;
    public int busTime; // minutes

//    public Edge (int start, int end) {}
    public Edge (int start, int end, int distance) {
        this.start=  start;
        this.end = end;
        this.distance = distance;
    }
    public Edge ( boolean canBus, boolean walkOnly, boolean carOnly,/* boolean oneDirec, */
                  int start,int end, int distance, int busTime) {
        this.canBus = canBus;
        this.walkOnly = walkOnly;
        this.carOnly = carOnly;
//        this.oneDirec = oneDirec;
        this.start = start;
        this.end = end;
        this.distance = distance;
        if (this.canBus) {
            this.busTime = busTime;
        }
    }
    public void setCanBus(int busTime) {
        this.canBus = true;
        this.busTime = busTime;
    }
    public void onlyBus(int busTime) {
        this.canBus = true;
        this.busOnly = true;
        this.busTime = busTime;
    }
    public void setWalkOnly() {
        this.walkOnly = true;
    }
    public void setCarOnly() {
        this.carOnly = true;
    }
}
