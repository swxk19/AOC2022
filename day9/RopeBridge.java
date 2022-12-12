import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;

public class RopeBridge {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";

    public static void main(String args[]) throws IOException {

        Coord head = new Coord(0, 0);
        Coord tail = new Coord(0, 0);

        HashSet<String> visited = new HashSet<>(); 

        visited.add(head.toString());
        visited.add(tail.toString());

        output.println(visited.size());
        while (!line.equals("end")) {
            if (!line.equals("init")) {
                for (int i = 0; i < Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length())); i++ ) { 
                    Coord temp = new Coord(head.r, head.c);
                    if (line.charAt(0) == 'U') {
                        head.r -= 1;
                    } else if (line.charAt(0) == 'D') {
                        head.r += 1;
                    } else if (line.charAt(0) == 'L') {
                        head.c -= 1; 
                    } else {
                        head.c += 1;
                    }
                    
                    if (!tail.touching(head)) {
                        tail = temp;
                        visited.add(tail.toString());
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
    public int hashCode() {
        return Integer.parseInt(String.valueOf(this.r) + String.valueOf(this.c < 0 ? this.c + 999999 : this.c));
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

    @Override 
    public String toString() {
        return this.r + " " + this.c;
    }
}


