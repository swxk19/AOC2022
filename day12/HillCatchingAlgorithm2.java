import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class HillCatchingAlgorithm2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static String line = "init";

    public static void main(String args[]) throws IOException {
        
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        
        ArrayList<IntegerTriple> start = new ArrayList<>();
        IntegerTriple end = new IntegerTriple(-1, -1, -1);

        int rowCount = -1;
        while (!line.equals("end")) {
            if (!line.equals("init")) {
                ArrayList<Integer> cur = new ArrayList<>();
                grid.add(cur);
                for (int c = 0; c < line.length(); c++) {
                    char curChar = line.charAt(c);
                    if (curChar == 'S' || curChar == 'a') {
                        start.add(new IntegerTriple(rowCount, c, 0));
                        cur.add((int) 'a');
                    } else if (curChar == 'E') {
                        end = new IntegerTriple(rowCount, c, 0);
                        cur.add((int) 'z');
                    } else {
                        cur.add((int) curChar);
                    }
                }
            }
            line = input.readLine();
            rowCount++;
        }

        int gridRows = grid.size();
        int gridCols = grid.get(0).size();


        int steps = 999999;

        for (int startPos = 0; startPos < start.size(); startPos++) {
            IntegerTriple curStart = start.get(startPos);
            Queue<IntegerTriple> bfsQ = new LinkedList<IntegerTriple>();
            HashSet<String> visited = new HashSet<>();
        
            bfsQ.offer(curStart);
            boolean reachedEnd = false;
    
            int[] moveX = {0, 0, 1, -1};
            int[] moveY = {1, -1, 0, 0};
            int result = -1;

            while (!reachedEnd) {
                IntegerTriple cur = bfsQ.poll();
                if (cur == null) {
                    break;
                }
                int row = cur.first;
                int col = cur.second;
                int curHeight = grid.get(row).get(col);

                if (row == end.first && col == end.second) {
                    reachedEnd = true;
                    result = cur.third;
                }

                for (int i = 0; i < moveX.length; i++) {
                    int newRow = row + moveX[i];
                    int newCol = col + moveY[i];

                    if ((newRow >= 0 && newRow < gridRows) && (newCol >= 0 && newCol < gridCols) && !visited.contains(new IntegerTriple(newRow, newCol, 0).visit())) { // valid grid index
                        int targetPos = grid.get(newRow).get(newCol);
                        if ((targetPos - curHeight) < 2) {
                            bfsQ.offer(new IntegerTriple(newRow, newCol, cur.third + 1));
                            visited.add(new IntegerTriple(newRow, newCol, 0).visit());
                        }
                    }
                }
            }
            if (result != -1) { 
                steps = Math.min(result, steps);
            }
            System.out.println(startPos);
        }
        output.println(steps);
        input.close();
        output.close();
    }
}

class IntegerTriple {
    int first;
    int second;
    int third;

    public IntegerTriple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public String visit() {
        return this.first + " " + this.second;
    }

    @Override
    public String toString() {
        return this.first + " " + this.second + " " + this.third;
    }
}
