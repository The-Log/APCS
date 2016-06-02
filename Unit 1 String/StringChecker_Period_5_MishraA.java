//2008 Syllabus "A", Question #4.
//The Design Question.
public class StringChecker_Period_5_MishraA
{
    public static void main(String [] args)
    {
        System.out.println("-------Part A ---------------");
        Checker broccoliChecker = new SubstringChecker("broccoli");
        System.out.println(broccoliChecker.accept("broccoli")); 				//true
        System.out.println(broccoliChecker.accept("I like broccoli")); 	//true
        System.out.println(broccoliChecker.accept("carrots are great")); 	//false
        System.out.println(broccoliChecker.accept("Broccoli Bonanza")); 	//false

        System.out.println("-------Part B ---------------");
        Checker bChecker = new SubstringChecker("beets");
        Checker cChecker = new SubstringChecker("carrots");
        Checker bothChecker = new AndChecker(bChecker, cChecker);
        Checker aChecker = new SubstringChecker("artichokes");
        Checker veggies = new AndChecker(bothChecker, aChecker);
        System.out.println(bothChecker.accept("I love beets and carrots")); 	//true
        System.out.println(bothChecker.accept("beets are great")); 				//false
        System.out.println(veggies.accept("artichokes, beets, and carrots"));//true

        System.out.println("-------Part C ---------------");
        Checker artiChecker = new SubstringChecker("artichokes");
        Checker kChecker = new SubstringChecker("kale");
        Checker yummyChecker;
        yummyChecker = new NorChecker(artiChecker,kChecker);
        System.out.println(yummyChecker.accept("chocolate truffles")); 		//true
        System.out.println(yummyChecker.accept("kale is great")); 				//false
        System.out.println(yummyChecker.accept("Yuck: artichokes & kale")); 	//false
    }
}
//examines strings and accepts those strings that meet a particular criterion.
interface Checker
{
    /** @param text a string to consider for acceptance
     * @return true if this Checker accepts text; false otherwise
     */
    boolean accept(String text);
}


class NotChecker implements Checker
{
    private Checker checker1;
    /*A one-parameter constructor that takes one Checker object
     */
    public NotChecker(Checker chk1)
    {
        checker1 = chk1;
    }
    /*
    * returns true if and only if its Checker object does NOT accept the string
    */
    public boolean accept(String text)
    {
        return !checker1.accept(text);
    }
}
// Part A  -- SubstringChecker

class SubstringChecker implements Checker{
    private String myString;
    public SubstringChecker(String s){
        myString = s;
    }
    public boolean accept(String text){
        int i = text.indexOf(myString);
        if ( i > -1)
            return true;
        else
            return false;
    }
}

// Part B  -- AndChecker
class AndChecker implements Checker{
    private Checker myCheckerA, myCheckerB;
    public AndChecker(Checker a, Checker b){
        myCheckerA =a;
        myCheckerB = b;
    }
    public boolean accept(String s){
        if(myCheckerA.accept(s) == true && myCheckerB.accept(s)== true){
            return true;
        }
        else
            return false;
    }
}
class NorChecker implements Checker{
    private Checker myCheckerA, myCheckerB;
    public NorChecker(Checker a, Checker b){
        myCheckerA =a;
        myCheckerB = b;
    }
    public boolean accept(String s){
        if(myCheckerA.accept(s) == true || myCheckerB.accept(s)== true){
            return false;
        }
        else
            return true;
    }
}

/*   Sample Run   


-------Part A ---------------
true
true
false
false
-------Part B ---------------
true
false
true
-------Part C ---------------
true
false
false
  
*/