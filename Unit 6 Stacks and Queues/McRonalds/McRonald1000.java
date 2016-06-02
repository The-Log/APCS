import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by amishra on 1/23/16.
 */
public class McRonald1000 {
    private static Queue<Customer> line;
    private static int totalNumberOfCustomers = 0, highestNumberOfCustomers =0, i =0 , longestQueue, longestWaitTime, totalWaitTime;

    public static void main(String[] args) throws FileNotFoundException {
        line = new LinkedList<Customer>();
        int time = 0,
                totalWaitTime = 0,
                longestWaitTime = 0,
                longestQueue = 0;
        for (i = 0; i < 1000; i++) {
            serve(line, time);
        }
    }
    public static void serve(Queue<Customer> line, int time){
        Customer previous = new Customer(0, 0, 0);
        int numOfCustomers = 0;
        while (time < 1440) {
            if (Math.random() * 5 <= 1) {
                //System.out.println("Time :" + time + "  ");
                int waitTime = (int) (Math.random() * 6) + 2;
                if (line.isEmpty()) {
                    Customer customer = new Customer(time, waitTime, waitTime);
                    previous = customer;
                    line.add(customer);
                } else {
                    int timeGap = time - previous.getArrivalTime();
                    int customersTotalWaitTime = waitTime + previous.getTotalWaitTime() - timeGap;
                    String customersTotalWaitTimeStr = waitTime + "+" + previous.getWaitTime() + "-" + timeGap;
                    if (timeGap < previous.getTotalWaitTime()) {
                        Customer customer = new Customer(time, waitTime, customersTotalWaitTime);
                        previous = customer;
                        line.add(customer);
                    } else {
                        if (line.size() > longestQueue) {
                            longestQueue = line.size();
                        }
                        while (!line.isEmpty()) {
                            Customer customer1 = line.poll();
                            totalWaitTime += customer1.getTotalWaitTime();
                            if (customer1.getTotalWaitTime() > longestWaitTime) {
                                longestWaitTime = customer1.getTotalWaitTime();
                            }
                        }

                        Customer customer = new Customer(time, waitTime, waitTime);
                        line.add(customer);
                        previous = customer;

                    }
                }
                numOfCustomers++;
                totalNumberOfCustomers++;
            }
            time++;
        }
        if(numOfCustomers > highestNumberOfCustomers){
            highestNumberOfCustomers = numOfCustomers;
        }
        int x = 0;
        while (!line.isEmpty()) {
            Customer customer = line.poll();
            totalWaitTime += customer.getTotalWaitTime();
            if (customer.getTotalWaitTime() > longestWaitTime) {
                longestWaitTime = customer.getTotalWaitTime();
            }

            x++;
        }
        if(i >= 999) {
            System.out.println("Total number of customers served: " + totalNumberOfCustomers);
            System.out.println("Average Wait Time: " + (double) totalWaitTime / totalNumberOfCustomers);
            System.out.println("Longest Wait Time: " + longestWaitTime);
            System.out.println("Longest queue: " + longestQueue);
            System.out.println("Record number of customers: " + highestNumberOfCustomers);

        }
    }
}
class Customer {
    private int arrivalTime, waitTime, totalWaitTime;

    public Customer(int at, int wt, int twt) {
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
}
