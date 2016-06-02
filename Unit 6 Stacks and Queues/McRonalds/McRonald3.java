import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by amishra on 1/23/16.
 */
public class McRonald3 {
    //private static Queue<Student> line;
    private static int
            time = 0,
            numberOfCustomers = 0,
            totalWaitTime = 0,
            longestWaitTime = 0,
            longestQueue = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Queue<Customer>[] lines;
        lines = new Queue[3];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new LinkedList<Customer>();
        }
        int time  = 0;
        //while (time < 1440) {
        for (int j = 0; j < lines.length; j++) {
            serve(lines[j]);
        }
        //}
        for (int j = 0; j < lines.length; j++) {

            while (!lines[j].isEmpty()) {
                Customer customer = lines[j].poll();
                totalWaitTime += customer.getTotalWaitTime();
                if (customer.getTotalWaitTime() > longestWaitTime) {
                    longestWaitTime = customer.getTotalWaitTime();
                }
                //System.out.println("Student " + i + " " + customer.toString());
                j++;
            }
        }


        System.out.println("Total number of customers served: " + numberOfCustomers);
        System.out.println("Average Wait Time: " + (double) totalWaitTime / numberOfCustomers);
        System.out.println("Longest Wait Time: " + longestWaitTime);
        System.out.println("Longest queue: " + longestQueue);
    }

    private static void serve(Queue<Customer> line) {
        Customer previous = new Customer(0, 0, 0);
        int waitTime = 0;
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
                numberOfCustomers++;
            }
            time++;

        }
    }
}
