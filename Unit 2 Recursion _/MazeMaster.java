//name:    date:

import java.util.*;
import java.io.*;

public class MazeMaster {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the maze's filename: ");
        Maze m = new Maze(sc.next());
        m.display();
        m.solve();
        m.display();
    }
}


class Maze {
    private final char wall = 'W';
    private final char path = '.';
    private final char start = 'S';
    private final char exit = 'E';
    private final char step = '*';
    private char[][] maze;
    private int startRow, startCol, counter;
    private boolean S_Exists = false, E_Exists = false;

    public Maze(String filename) {
        Scanner infile = null;
        try {
            infile = new Scanner(new File(filename + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        //read the file
        int row = infile.nextInt();
        int column = infile.nextInt();
        maze = new char[row][column];
        //System.out.println(row);System.out.println(column);
        for (int i = -1; i < row; i++) {
            String str = infile.nextLine();
            for (int col = 0; col < str.length(); col++) {
                maze[i][col] = str.charAt((col));
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[i][col] == start) {
                    startRow = i;
                    startCol = col;
                    return;
                }
            }
        }

    }

    public void display() {
        System.out.println(startRow + ", " + startCol);
        for (int i = 0; i < maze.length; i++) {
            for (int col = 0; col < maze[0].length; col++) {
                System.out.print(maze[i][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void solve() {
      /*  1  */
        //solveAndMark(startRow, startCol);
      
      /*  2  */
        int count = solveAndCount(startRow, startCol);
        System.out.println("Number of steps = " + count);

      /*  3  */
        //markThePath(startRow, startCol);

      /*  4  */
        //if( !markThePath(startRow, startCol) )
        //System.out.println("No solution");

      /*  5  */
        //markThePathAndCount(startRow, startCol, 0);

    }

    private static int[][] cp(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        int[][] c = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c[i][j] = m[i][j];
            }
        }
        return c;
    }

    private void solveAndMark(int r, int c) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            return;
        } else {
            maze[r][c] = '*';
            if (c + 1 < maze[0].length && maze[r][c + 1] == path)
                solveAndMark(r, c + 1);
            if (r + 1 < maze.length && maze[r + 1][c] == path)
                solveAndMark(r + 1, c);
            if (r - 1 > 0 && maze[r - 1][c] == path)
                solveAndMark(r - 1, c);
            if (c - 1 > 0 && maze[r][c - 1] == path)
                solveAndMark(r, c - 1);
        }
    }

    private int[][] recur(int r, int c, int[][] markedArray) {
        // 3 Bases Cases, Out of Array, Wall, Already Been there
        if (isValid(r, c, markedArray)) {
            if (maze[r][c] == exit)
                return markedArray;
            int[][] copyArray = cp(markedArray);
            copyArray[r][c] = 1;
            int[][] upArray = recur(r + 1, c, copyArray);
            int[][] downArray = recur(r - 1, c, copyArray);
            int[][] rightArray = recur(r, c + 1, copyArray);
            int[][] leftArray = recur(r, c - 1, copyArray);

            return findMinArray(upArray, downArray, rightArray, leftArray);
        } else
            return null;
    }

    private boolean isValid(int r, int c, int[][] markedArray) {
        if (r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] != wall && markedArray[r][c] != 1)
            return true;
        else
            return false;
    }

    private int[][] findMinArray(int[][] upArray, int[][] downArray, int[][] rightArray, int[][] leftArray) {
        int minLength = -1;
        int[][] minArray = null; //= new int[maze.length][maze[0].length];
        int[][][] array = {upArray, downArray, rightArray, leftArray};
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            if (i == 0 || minLength < sumArray(array[i])) {
                minLength = sumArray(array[i]);
                minArray = array[i];
            }
        }
        return minArray;
    }

    private int sumArray(int[][] array) {
        int var = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 1)
                    var++;
            }
        }
        return var;
    }

    private int solveAndCount(int r, int c) {
        maze[r][c] = '*';
        int count = 1;
        if (c + 1 < maze[0].length && maze[r][c + 1] == path) {
            count += solveAndCount(r, c + 1);
        } if (r + 1 < maze.length && maze[r + 1][c] == path) {
            count += solveAndCount(r + 1, c);
        } if (r - 1 > 0 && maze[r - 1][c] == path) {
            count += solveAndCount(r - 1, c);
        } if (c - 1 > 0 && maze[r][c - 1] == path) {
            count += solveAndCount(r, c - 1);
        }
        return count;
    }

    private boolean markThePath(int r, int c) {
        int[][] markedArray = new int[maze.length][maze[0].length];
        markedArray = recur(r, c, markedArray);
        if (markedArray != null) {
            for (int ri = 0; ri < markedArray.length; ri++) {
                for (int ci = 0; ci < markedArray[0].length; ci++) {
                    if (markedArray[ri][ci] == 1)
                        maze[ri][ci] = 'o';
                }
            }
            return true;
        } else
            return false;
    }

    private boolean markThePathAndCount(int r, int c, int count) {
        int[][] markedArray = new int[maze.length][maze[0].length];
        markedArray = recur(r, c, markedArray);
        if (markedArray != null) {
            for (int ri = 0; ri < markedArray.length; ri++) {
                for (int ci = 0; ci < markedArray[0].length; ci++) {
                    //System.out.print(markedArray[r][c]);
                    if (markedArray[ri][ci] == 1)
                        maze[ri][ci] = 'o';
                }
            }
            count = sumArray(markedArray);
            System.out.println(count);
            return true;
        }
        return false;
    }
}
