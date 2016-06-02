import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by amishra on 1/23/16.
 */
public class McRonaldPQ {
    private static Queue<Student> line;
    private static Queue<Student>[] lines;
    private static int
            time = 0,
            longestQueue = 0,
            numberOfFreshman = 0, freshmanTotalWaitTime =0, freshmanLongestQueue =0,freshmanLongestWaitTime,
            numberOfSophmore = 0, sophmoreTotalWaitTime =0, sophmoreLongestQueue =0,sophmoreLongestWaitTime,
            numberOfJuniors = 0,  juniorTotalWaitTime =0,   juniorLongestQueue =0,juniorLongestWaitTime,
            numberofSeniors = 0,  seniorTotalWaitTime =0, seniorLongestQueue =0, seniorLongestWaitTime;

    public static void main(String[] args) {
        PriorityQueue<Student>[] lines;
        lines = new PriorityQueue[3];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new PriorityQueue<Student>();
        }
        int time = 0;
        for (int j = 0; j < lines.length; j++) {
            serve(lines[j]);
        }
        for (int j = 0; j < lines.length; j++) {

            while (!lines[j].isEmpty()) {
                Student student = lines[j].poll();
                addTimeToCorrespondingYear(student);
                j++;
            }
        }
        System.out.println("Student\t\tTotal Served \tLongest Wait\tAverage Wait");
        System.out.println("Senior\t\t\t" + numberofSeniors + "\t\t\t" + seniorLongestWaitTime + "\t\t\t\t" + (double)seniorTotalWaitTime/numberofSeniors);
        System.out.println("Junior\t\t\t" + numberOfJuniors + "\t\t\t" + juniorLongestWaitTime + "\t\t\t\t" + (double)juniorTotalWaitTime/numberOfJuniors);
        System.out.println("Sophomore\t\t"+ numberOfSophmore + "\t\t\t" + sophmoreLongestWaitTime + "\t\t\t\t" + (double)sophmoreTotalWaitTime/numberOfSophmore);
        System.out.println("Freshman\t\t" + numberOfFreshman + "\t\t\t" + freshmanLongestWaitTime + "\t\t\t\t" + (double)freshmanTotalWaitTime/numberOfFreshman);
    }

    private static void addTimeToCorrespondingYear(Student student) {
        System.out.println(student.getTotalWaitTime() + " YEAR " +student.getGradYear() );
        if(student.getGradYear() == 2016){
            seniorTotalWaitTime += student.getTotalWaitTime();
            if (student.getTotalWaitTime() > seniorLongestWaitTime) {
                seniorLongestWaitTime = student.getTotalWaitTime();
            }
        }
        else if(student.getGradYear() == 2017) {
            juniorTotalWaitTime += student.getTotalWaitTime();
            if (student.getTotalWaitTime() > juniorLongestWaitTime) {
                juniorLongestWaitTime = student.getTotalWaitTime();
            }
        }
        else if(student.getGradYear() == 2018) {
            sophmoreTotalWaitTime += student.getTotalWaitTime();
            if (student.getTotalWaitTime() > sophmoreLongestWaitTime) {
                sophmoreLongestWaitTime = student.getTotalWaitTime();
            }
        }
        else if(student.getGradYear() == 2019) {
            freshmanTotalWaitTime += student.getTotalWaitTime();
            if (student.getTotalWaitTime() > freshmanLongestWaitTime) {
                freshmanLongestWaitTime = student.getTotalWaitTime();
            }
        }
        else{
        }
    }

    public static void emptyTheLine(Queue<Student> line) {
        while (!line.isEmpty()) {
            Student student = line.poll();
            addTimeToCorrespondingYear(student);
        }
    }

    public static void serve(Queue<Student> line) {
        Student previous = new Student(0, 0, 0, 0);
        Student student;
        while (time < 1440) {
             if (Math.random() * 5 <= 1) {
                //System.out.println("Student Time" + time);
                int waitTime = (int) (Math.random() * 6) + 2;
                if (line.isEmpty()) {
                    student = new Student(time, waitTime, waitTime, (int)(Math.random()*4) + 2016);
                    previous = student;
                    line.add(student);
                    if(student.getGradYear() == 2016){
                        numberofSeniors ++;
                    }
                    else if(student.getGradYear() == 2017) {
                        numberOfJuniors++;
                    }
                    else if(student.getGradYear() == 2018) {
                        numberOfSophmore++;
                    }
                    else if(student.getGradYear() == 2019) {
                        numberOfFreshman++;
                    }
                    else{
                        System.out.println("Error");
                    }
                }
                else {
                    previous = processingConsecutiveCustomer(previous, waitTime, line);
                }
            }
            time++;
        }
    }

    private static Student processingConsecutiveCustomer(Student previous, int waitTime, Queue<Student> line) {
        int timeGap = time - previous.getArrivalTime();
        int customersTotalWaitTime = waitTime + previous.getTotalWaitTime() - timeGap;
        int gradYear = (int) (Math.random() * 4) + 2016;
        if (timeGap < previous.getTotalWaitTime() && previous.getGradYear() <= gradYear) {
            Student student = new Student(time, waitTime, customersTotalWaitTime, gradYear);
            previous = student;
            line.add(student);
        } else {
            if (line.size() > longestQueue) {
                longestQueue = line.size();
            }
            emptyTheLine(line);
            Student student = new Student(time, waitTime, waitTime, (int) (Math.random() * 4) + 2016);
            line.add(student);
            previous = student;
            if(student.getGradYear() == 2016){
                numberofSeniors ++;
            }
            else if(student.getGradYear() == 2017) {
                numberOfJuniors++;
            }
            else if(student.getGradYear() == 2018) {
                numberOfSophmore++;
            }
            else if(student.getGradYear() == 2019) {
                numberOfFreshman++;
            }
        }
        return previous;
    }
}
class Student implements Comparable<Student>{
    private int arrivalTime;
    private int waitTime;
    private int totalWaitTime;
    private int gradYear;

    public Student(int at, int wt, int twt, int gYear) {
        arrivalTime = at;
        waitTime = wt;
        totalWaitTime = twt;
        gradYear = gYear;
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

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }
    @Override
    public String toString() {
        return "arrivalTime=" + arrivalTime +
                ", waitTime=" + waitTime +
                ", totalWaitTime=" + totalWaitTime;
    }

    public int compareTo(Student s) {
        System.out.println(gradYear +"-"+ s.gradYear + "= " + (gradYear-s.getGradYear()));
        return gradYear - s.getGradYear();
    }
}
