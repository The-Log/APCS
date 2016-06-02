//Name:     Date:
import java.io.*;
import java.util.*;
public class Dictionary
{
	public static void main(String[] args)
	{
		Scanner infile = null;
		try
		{
			infile = new Scanner(new File("spanglish.txt"));
			System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
		}
		catch(Exception e)
		{
            System.out.println("Error, file not found.");
            System.exit(1);
        }
		Map<String, Set<String>> eng2spn = makeDictionary( infile );
		System.out.println("ENGLISH TO SPANISH");
		display(eng2spn);

		Map<String, Set<String>> spn2eng = reverse(eng2spn);
		System.out.println("SPANISH TO ENGLISH");
		display(spn2eng);
	}
	public static Map<String, Set<String>> makeDictionary(Scanner infile)
	{
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
        while (infile.hasNext()) {
            String word = "" + infile.next();
            String translation = "";
            if(infile.hasNext()) {
                 translation = "" + infile.next();
            }
            add(map, word, translation);
        }
		return map;
	}
	private static void add(Map<String, Set<String>> dictionary, String word, String translation)
	{
        if(dictionary.containsKey(word)) {
            Set<String> set = dictionary.get(word);
            set.add(translation);
            dictionary.put(word, set);
        }
        else{
            Set<String>  set = new TreeSet<String>();
            set.add(translation);
            dictionary.put(word, set);
        }
	}
	public static void display(Map<String, Set<String>> m)
	{
        String s = "";
        if(m != null) {
            Iterator i = m.keySet().iterator();
            while (i.hasNext()) {
                Object o = i.next();
                String s1 = "" + m.get(o);
                s = s + o + " " + s1;
                s = s + "\n";
            }
            System.out.println(s);
        }
    }
	public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
	{
        Iterator i = dictionary.keySet().iterator();
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
        while(i.hasNext()){
            Object o = i.next();
            String translation = "" + o ;
            Set s = dictionary.get(o);
            Iterator i1 = s.iterator();
            while(i1.hasNext()){
                String word = "" + i1.next();
                add(map, word, translation);
            }
        }
        return map;
	}
}
/********************
 INPUT:
 holiday
 fiesta
 holiday
 vacaciones
 party
 fiesta
 celebration
 fiesta
 <etc.>
 ***********************************
 OUTPUT:
 ENGLISH TO SPANISH
 banana [banana]
 celebration [fiesta]
 computer [computadora, ordenador]
 double [doblar, doble, duplicar]
 father [padre]
 feast [fiesta]
 good [bueno]
 hand [mano]
 hello [hola]
 holiday [fiesta, vacaciones]
 party [fiesta]
 plaza [plaza]
 priest [padre]
 program [programa, programar]
 sleep [dormir]
 son [hijo]
 sun [sol]
 vacation [vacaciones]

 SPANISH TO ENGLISH
 banana [banana]
 bueno [good]
 computadora [computer]
 doblar [double]
 doble [double]
 dormir [sleep]
 duplicar [double]
 fiesta [celebration, feast, holiday, party]
 hijo [son]
 hola [hello]
 mano [hand]
 ordenador [computer]
 padre [father, priest]
 plaza [plaza]
 programa [program]
 programar [program]
 sol [sun]
 vacaciones [holiday, vacation]

 **********************/