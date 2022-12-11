import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TreetopTreeHouse3  {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";

    public static void main(String args[]) throws IOException {
        ArrayList<String> rowList = new ArrayList<>();

        while (!line.equals("end")) {
            if (!line.equals("init")) {
                rowList.add(line);
            }
            line = input.readLine();
        }

        int numRows = rowList.size();
        int numCols = rowList.get(0).length();
        

        int[][] grid = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String curRow = rowList.get(i);
            for (int j = 0; j < curRow.length(); j++) {
                grid[i][j] = Character.getNumericValue(curRow.charAt(j));
            }
        }

        int maxScenic = -1;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                maxScenic = Math.max(maxScenic, scenicVal(grid, i, j, numRows, numCols));
            }

        }

        output.println(maxScenic);
        output.close();
        input.close();
    }

    public static int scenicVal(int[][] grid, int r, int c, int R, int C) {

        int curVal = grid[r][c];
        int result = 1;

        int treesSeen = 0;

        // check left edge
        for (int i = c - 1; i >= 0; i--) {
            if (grid[r][i] < curVal) {
                treesSeen += 1;
            } else {
                treesSeen += 1;
                break;
            }
        }
        result *= treesSeen;

        treesSeen = 0;

        // check right edge
        for (int i = c + 1; i < C; i++) {
            if (grid[r][i] < curVal) {
                treesSeen += 1;
            } else {
                treesSeen += 1;
                break;
            }
        }

        result *= treesSeen;
        treesSeen = 0;

        //check top edge 
        for (int i = r - 1; i >= 0; i--) {
            if (grid[i][c] < curVal) {
                treesSeen += 1;
            } else {
                treesSeen += 1;
                break;
            }
        }

        result *= treesSeen;
        treesSeen = 0;

        //check bottom edge 
        for (int i = r + 1; i < R; i++) {
            if (grid[i][c] < curVal) {
                treesSeen += 1;
            } else {
                treesSeen += 1;
                break;
            }
        }

        result *= treesSeen;
        treesSeen = 0;

        return result;

    }

    
}
