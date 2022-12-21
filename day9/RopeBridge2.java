import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;

public class RopeBridge2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";

    public static void main(String args[]) throws IOException {

        Coord[] rope = new Coord[10];

        for (int i = 0; i < 10; i++) {
            rope[i] = new Coord(0, 0);
        }

        HashSet<String> visited = new HashSet<>(); 

        visited.add(rope[0].toString());

        while (!line.equals("end")) {
            if (!line.equals("init")) {
  
                    for (int i = 0; i < Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length())); i++ ) { 

                        if (line.charAt(0) == 'U') {
                            rope[0].r -= 1;
                        } else if (line.charAt(0) == 'D') {
                            rope[0].r += 1;
                        } else if (line.charAt(0) == 'L') {
                            rope[0].c -= 1; 
                        } else {
                            rope[0].c += 1;
                        }
                        
        
                        for (int pos = 1; pos < 10; pos++) {
                            if (!rope[pos].touching(rope[pos - 1])) {
                                rope[pos].moveTowards(rope[pos - 1]);
                            }

                            if (pos == 9) {
                                visited.add(rope[pos].toString());
                            }
                        }

                    }
                
            }

            line = input.readLine();
        }


        output.println(visited.size());
        output.close();
        input.close();
        
    }
    
}

class Coord {
    int r;
    int c;

    public Coord(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        return this.r == ((Coord) o).r && this.c == ((Coord) o).c;
    }

    public float distanceTo(Coord o) {
        if (this.r == o.r) {     // same row
            return Math.abs(this.c - o.c);
        } else if (this.c == o.c) {     // same col
            return Math.abs(this.r - o.r);
        } else {    // diagonal
            return ((float) Math.abs(this.r - o.r) + Math.abs(this.c - o.c)) / 2;
        }
    }

    public boolean touching(Coord o) {
        
        int[] row = {-1, 1, 0};
        int[] col = {0,-1, 1};
        
        boolean result = false;

        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < col.length; j++) {
                if (new Coord(this.r + row[i], this.c + col[j]).equals(o))  {
                    result = result || true;
                }
            }
        }
        return result;
    }

    public boolean moveTowards(Coord o) {
        int[] dRow = {-1, 1};
        int[] dCol = {-1, 1};

        int[] aRow = {0, 0, 1, -1};
        int[] aCol = {1, -1, 0, 0};

        boolean result = false;

        if (this.r != o.r && this.c != o.c) {   // o is diagonally away
            for (int i = 0; i < dRow.length; i++) {
                for (int j = 0; j < dCol.length; j++) {
                    Coord temp = new Coord(this.r + dRow[i], this.c + dCol[j]);
                    if (temp.touching(o)) {
                        this.r = temp.r;
                        this.c = temp.c;
                        result = true;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        } else {        // o is non-diagonal away
            for (int i = 0; i < aRow.length; i++) {
                Coord temp = new Coord(this.r + aRow[i], this.c + aCol[i]);
                if (temp.touching(o)) {
                    this.r = temp.r;
                    this.c = temp.c;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override 
    public String toString() {
        return this.r + " " + this.c;
    }
}


