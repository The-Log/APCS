import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by amishra on 1/23/16.
 */
public class McRonald {
    private static Queue<Customer> line;

    public static void main(String[] args) throws FileNotFoundException {
        line = new LinkedList<Customer>();
        Customer previous = new Customer(0, 0, 0);
        int numberOfCustomers = 0;
        int time = 0,
                totalWaitTime = 0,
                waitTime = 0,
                longestWaitTime = 0,
                longestQueue = 0;
        while (time < 1440) {
            if (Math.random() * 5 <= 1) {
                //System.out.println("Time :" + time + "  ");
                waitTime = (int) (Math.random() * 6) + 2;
                if (line.isEmpty()) {
                    Customer customer = new Customer(time, waitTime, waitTime);
                    previous = customer;
                    //System.out.println("customersTotalWaitTimeStr:" + waitTime);
                    line.add(customer);
                } else {
                    int timeGap = time - previous.getArrivalTime();
                    int customersTotalWaitTime = waitTime + previous.getTotalWaitTime() - timeGap;
                    String customersTotalWaitTimeStr = waitTime + "+" + previous.getWaitTime() + "-" + timeGap;
                    //System.out.println("customersTotalWaitTimeStr:" + customersTotalWaitTimeStr + "= " +customersTotalWaitTime);
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
                            //System.out.println("Student " + numberOfCustomers + " " + customer1.toString());
                        }

                        Customer customer = new Customer(time, waitTime, waitTime);
                        line.add(customer);
                        previous = customer;

                    }
                }
                numberOfCustomers++;
            }
            time++;
        }
        //System.out.println();
        int i = 0;
        while (!line.isEmpty()) {
            Customer customer = line.poll();
            totalWaitTime += customer.getTotalWaitTime();
            if (customer.getTotalWaitTime() > longestWaitTime) {
                longestWaitTime = customer.getTotalWaitTime();
            }
            //System.out.println("Student " + i + " " + customer.toString());
            i++;
        }

        System.out.println("Total number of customers served: " + numberOfCustomers);
        System.out.println("Average Wait Time: " + (double) totalWaitTime / numberOfCustomers);
        System.out.println("Longest Wait Time: " + longestWaitTime);
        System.out.println("Longest queue: " + longestQueue);
    }
}

class Customer1 {
    private int arrivalTime, waitTime, totalWaitTime;

    public Customer1(int at, int wt, int twt) {
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