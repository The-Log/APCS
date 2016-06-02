///name:    date:
//first program on queues.
import java.io.*;
import java.util.*;
import java.io.File;


public class SongQueue
{
    private static Scanner infile;
    private static Queue<String> songQueue;

    public static void main(String[] args) throws Exception
    {
        fillPlayList();
        printSongList();
        infile = new Scanner(System.in);
        String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
        System.out.print(prompt);
        String str = infile.next().toUpperCase();
        infile.nextLine();
        while(!str.equalsIgnoreCase("Q"))
        {

            processRequest( str );
            printSongList();
            System.out.print(prompt);
            str = infile.next().toUpperCase();
            infile.nextLine();
        }
        System.out.println();
        System.out.println("No more music for you today.  Goodbye!");
        infile.close();
    }
    public static void fillPlayList()throws IOException
    {
        songQueue = new LinkedList<String>();
        
        Scanner infile2 = new Scanner(new File("songs.txt"));
        int size = 0;
        while (infile2.hasNextLine()) {
            size++;
            infile2.nextLine();
        }
        Scanner infile1 = new Scanner(new File("songs.txt"));
        for (int k = 0; k < size; k++){
            String temp = infile1.nextLine();
            temp = temp.substring(0, temp.indexOf('-') - 1);
            songQueue.add(temp);
        }
    }
    public static void processRequest(String str)
    {
        if(str.equalsIgnoreCase("A")) {
            System.out.print("\tEnter song to add (exact match): " );
            str = infile.nextLine();
            add(str);
        }
        if(str.equalsIgnoreCase("P")) {
            play();
        }
        if(str.equalsIgnoreCase("D")){
            System.out.print("\tEnter song to delete (exact match): ");
            str = infile.nextLine();
            delete(str);
        }
    }
    public static void add(String newSong)
    {
        songQueue.add(newSong);
    }
    public static void play()
    {
        if(songQueue.isEmpty()){
            System.out.println("Queue is empty. Please add more songs to the queue.");
        }
        else {
            String str = songQueue.poll();
            System.out.println("Now playing: " + str);
        }
    }
    public static void delete(String song)//need to edit //put output on handout
    {

        String first = songQueue.poll();
        add(first);
        while (!songQueue.peek().equals(song) && songQueue.peek() != first){
            songQueue.add(songQueue.poll());
        }
        if(songQueue.peek().equals(song)){
            songQueue.poll();
        }
        else
            System.out.println("ERROR, song could not be found. Perhaps you mistyped it?");
    }
    public static void printSongList()
    {
        if(songQueue.isEmpty()){
            //System.out.println("Queue is empty. Please add more songs to the queue.");
        }
        else {
            System.out.println("Your music queue:" + songQueue.toString());
        }
    }
}