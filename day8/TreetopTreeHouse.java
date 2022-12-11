import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class TreetopTreeHouse  {
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
        boolean[][] visible = new boolean   [numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String curRow = rowList.get(i);
            for (int j = 0; j < curRow.length(); j++) {
                grid[i][j] = Character.getNumericValue(curRow.charAt(j));
            }
        }


        HashMap<String, Integer> visibleCoord = new HashMap<>();
        
        // check by rows from left to right
        for (int i = 0; i < numRows; i++) {
            int curMax = -1;
            for (int j = 0; j < numCols; j++) {
                int curVal = grid[i][j];
                if (curVal > curMax) {
                    curMax = curVal;
                    visible[i][j] = true;
                    // String coordToString = Integer.toString(i) + Integer.toString(j);
                    // visibleCoord.put(coordToString, 1);
                }
            }
        }

        // check rows by right to left
        for (int i = 0; i < numRows; i++) {
            int curMax = -1;
            for (int j = numCols - 1; j >= 0; j--) {
                int curVal = grid[i][j];
                if (curVal > curMax) {
                    curMax = curVal;
                    visible[i][j] = true;
                    // String coordToString = Integer.toString(i) + Integer.toString(j);
                    // visibleCoord.put(coordToString, 1);
                }
            }
        }
        
        // check by rows from top to bottom
        for (int i = 0; i < numCols; i++) {
            int curMax = -1;
            for (int j = 0; j < numRows; j++) {
                int curVal = grid[j][i];
                if (curVal > curMax) {
                    curMax = curVal;
                    visible[j][i] = true;
                    // String coordToString = Integer.toString(j) + Integer.toString(i);
                    // visibleCoord.put(coordToString, 1);
                }
            }
        }

        // check rows by bottom to top
        for (int i = 0; i < numCols; i++) {
            int curMax = -1;
            for (int j = numRows - 1; j >= 0; j--) {
                int curVal = grid[j][i];
                if (curVal > curMax) {
                    curMax = curVal;
                    visible[j][i] = true;
                    // String coordToString = Integer.toString(j) + Integer.toString(i);
                    // visibleCoord.put(coordToString, 1);
                }
            }
        }

        ArrayList<String> visibleKeys = new ArrayList<>(visibleCoord.keySet());

        int result = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (visible[i][j]) {
                    result += 1;
                }
            }
        }

        output.println(result);
        // output.println(visibleKeys.size());

        output.close();
        input.close();
    }

    
}
