import java.util.Comparator;

/**
 * Created by Ankur on 1/30/2016.
 */
public class Customer implements Comparable{
    private int arrivalTime, waitTime, totalWaitTime;

    public Customer(int at, int wt, int twt) {
        arrivalTime = at;
        waitTime = wt;
        totalWaitTime = twt;
    }
    public Customer(int at, int wt, int twt, int p) {
        arrivalTime = at;
        waitTime = wt;
        totalWaitTime = twt;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int a) {
        arrivalTime = a;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int a) {
        waitTime = a;
    }

    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    public void setTotalWaitTime(int a) {
        totalWaitTime = a;
    }


    @Override
    public String toString() {
        return "arrivalTime=" + arrivalTime +
                ", waitTime=" + waitTime +
                ", totalWaitTime=" + totalWaitTime;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
