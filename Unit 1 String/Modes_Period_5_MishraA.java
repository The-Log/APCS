//Name: Ankur Mishra
//Date: 9/9/2015
public class Modes_Period_5_MishraA
{
    public static void main(String[] args)
    {
        int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
        display(tally);
        int[] modes = calculateModes(tally);
        display(modes);
        int sum = 0;
        for(int k = 0; k < tally.length; k++)
            sum += tally[k];
        System.out.println("kth \tindex");
        for(int k = 1; k <= sum; k++)
            System.out.println(k + "\t\t" + kthDataValue(tally, k));
    }
    public static int[] calculateModes(int[] tally)
    {
        int[] modesArray = new int[3];
        int iter = 0;
        for(int i = 0; i < tally.length; i++){
            if (tally[i] == findMax(tally)){
                modesArray[iter] = i;
                //System.out.println(modesArray[iter]);
                iter++;
            }
        }
        return modesArray;
    }
    public static int kthDataValue(int[] tally, int k)
    {
        int sum = 0; int i = 0;
        int kth = 0;
        for ( i = 0; i < tally.length;){
            sum += tally[i];
            if (sum >= k){
              break;
            }else {
                i++;
            }

        }
        return i;

    }
    public static int findMax(int[] nums)
    {
        int pos = 0;
        for(int k = 1; k < nums.length; k++)
            if(nums[k] > nums[pos])
                pos = k;
        return nums[pos];
    }
    public static void display(int[] args)
    {
        for(int k = 0; k < args.length; k++)
            System.out.print(args[k] + " ");
        System.out.println();
        System.out.println();
    }
}
