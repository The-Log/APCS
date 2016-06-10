// Name: Ankur
// Date: 

public class MatrixRecreate {
    public static void main(String[] args) {
        int[][] matrix = create();
        int[] rowcount = new int[matrix.length];
        int[] colcount = new int[matrix[0].length];
        count(matrix, rowcount, colcount);
        display(matrix, rowcount, colcount);
        re_create(rowcount, colcount);
    }

    public static int[][] create() {
        int row = (int) (Math.random() * 5 + 2);
        int col = (int) (Math.random() * 5 + 2);

        int[][] array = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = (int) (Math.random() * 2);
                //System.out.print(array[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println();
        return array;
    }

    public static void count(int[][] matrix, int[] rowcount, int[] colcount) {

        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    rowcount[i]++;
                    colcount[j]++;
                }
            }
        }
    }

    public static void display(int[][] matrix, int[] rowcount, int[] colcount) {
        System.out.print("   ");
        for (int i = 0; i < colcount.length; i++) {
            System.out.print(colcount[i] + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < colcount.length; i++) {
            System.out.print("--");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(rowcount[i] + "| ");
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[i][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void re_create(int[] rowcount, int[] colcount) {
        int[][] newMatrix = new int[rowcount.length][colcount.length];
        recur(newMatrix, rowcount,colcount,0,0);
    }

    private static int[][] copy(int[][] m){
        int rows = m.length;
        int cols = m[0].length;
        int[][] c = new int[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j = 0; j< cols; j++){
                c[i][j]=m[i][j];
            }
        }
        return c;
    }
    private static void recur(int[][] m, int[] rowcount, int[] colcount, int row, int col) //recursive helper method
    {
        // display(m, rowcount, colcount);    //we're done!
        if (compare(m, rowcount, colcount))    //base case: if new matrix works
        {
            // System.out.println("Solution");
            display(m, rowcount, colcount);    //we're done!
            System.exit(0);
        }

        int[] vals = new int[]{1,0};
        int rows = m.length;
        int cols = m[0].length;
        if(col >= cols) return; // base case: when we run out of cols
        if(row >= rows){ // recursive case: when we need to progress to next row
            recur(m,rowcount,colcount,0,col+1);
        }
        else{
            recur(m,rowcount,colcount,row+1,col); //Creates the permutation
            int[] nrowcount = new int[m.length];
            int[] ncolcount = new int[m[0].length];
            count(m,nrowcount,ncolcount);
            if(nrowcount[row]<=rowcount[row] && ncolcount[col]<=colcount[col]){ // The if makes sure its smart and doesn't waste time
                int[][] c=copy(m);
                c[row][col]=1;
                recur(c,rowcount,colcount,row+1,col);
            }
            // int[][] c=cp(m);
            // c[row][col]=1;
            // recur(c,rowcount,colcount,row+1,col);
        }

    }
    private static boolean compare(int[][] m, int[] rowcount, int[] colcount) {
        int[] row = new int[m.length];
        int[] col = new int[m[0].length];
        count(m, row, col);
        for (int x = 0; x < row.length; x++)
        {
            if (row[x] != rowcount[x])
                return false;
        }
        for (int y = 0; y < col.length; y++)
        {
            if (col[y] != colcount[y])
                return false;
        }
        return true;
    }
}
