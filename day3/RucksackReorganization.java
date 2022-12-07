import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RucksackReorganization {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static void main(String args[]) throws IOException {
        int sum = 0;
        while (!line.equals("end")) {
            if (!line.equals("init")) { 
                int[] firstCompartment = new int[58];

                int size = line.length();
                for (int i = 0; i < size / 2; i++) {
                    firstCompartment[mapAlpha(line.charAt(i))] = 1;    
                }

                for (int i = size / 2; i < size; i++) {
                    if (firstCompartment[mapAlpha(line.charAt(i))] == 1) {
                        firstCompartment[mapAlpha(line.charAt(i))] = 2; 
                        sum += mapAlpha(line.charAt(i));
                    }
                }
            } 
            line = input.readLine();
        }

        output.println(sum);
        input.close();
        output.close();
    }

    public static int mapAlpha(char c) {
        int ascii = (int) c;
        if (ascii > 96) {
            return ascii - 96;
        } else {
            return ascii - 38;
        }
    }

}