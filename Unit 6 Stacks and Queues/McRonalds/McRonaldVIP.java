import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by amishra on 1/23/16.
 */
public class McRonaldVIP {
    private static Queue<Customer> line;
    private static Queue<Customer>[] lines;
    private static int
            i = 0,
            time = 0,
            numberOfCustomers = 0,
            numberOfVIPs =0,
            totalWaitTime = 0,
            longestWaitTime = 0,
            longestQueue = 0,
            totalVIPWaitTime = 0;



    public static void main(String[] args)   {
        Queue<Customer>[] lines;
        lines = new LinkedList[3];
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
        System.out.println();
        System.out.println("Total number of VIPS served: " + numberOfVIPs);
        System.out.println("Average Wait Time: " + (double) totalVIPWaitTime / numberOfVIPs);



    }
    public static void emptyTheLine(Queue<Customer> line){
        while (!line.isEmpty()) {
            Customer customer = line.poll();
            totalWaitTime += customer.getTotalWaitTime();
            if (customer.getTotalWaitTime() > longestWaitTime) {
                longestWaitTime = customer.getTotalWaitTime();
            }
        }
    }
    public static void serve(Queue<Customer> line) {
        Customer previous = new Customer(0, 0, 0);
        while (time < 1440){
            if (Math.random() * 1000 > 999 ) {
                processVIP(line);
                numberOfCustomers++;
            }
            else if (Math.random() * 5 <= 1) {
                //System.out.println("Student Time" + time);
                int waitTime = (int) (Math.random() * 6) + 2;
                if (line.isEmpty()) {
                    Customer customer = new Customer(time, waitTime, waitTime);
                    previous = customer;
                    line.add(customer);
                }
                else {
                    previous = processingConsectiveCustomer(previous, waitTime,line);
                }
                numberOfCustomers++;
            }
            time++;
        }
}

    private static Customer processingConsectiveCustomer(Customer previous, int waitTime, Queue<Customer> line) {
        int timeGap = time - previous.getArrivalTime();
        int customersTotalWaitTime = waitTime + previous.getTotalWaitTime() - timeGap;
        if (timeGap < previous.getTotalWaitTime()) {
            Customer customer = new Customer(time, waitTime, customersTotalWaitTime);
            previous = customer;
            line.add(customer);
        }
        else {
            if (line.size() > longestQueue) {
                longestQueue = line.size();
            }
            emptyTheLine( line);
            Customer customer = new Customer(time, waitTime, waitTime);
            line.add(customer);
            previous = customer;
        }
        return previous;
    }

    private static void processVIP(Queue<Customer> line) {
        int waitTime = (int) (Math.random() * 6) + 2;
        totalVIPWaitTime += waitTime;
        Customer vip = new Customer(time, waitTime, waitTime);
        Queue<Customer> temp = new LinkedList<Customer>();
        while (!line.isEmpty()) {
            Customer tempCustomer = line.poll();
            tempCustomer.setTotalWaitTime(tempCustomer.getTotalWaitTime() + waitTime);
            temp.add(tempCustomer);
        }
        line.add(vip);
        while (!temp.isEmpty()) {
            Customer tempCustomer = temp.poll();
            line.add(tempCustomer);
        }
        numberOfVIPs++;
    }

}