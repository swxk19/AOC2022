import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RucksackReorganization2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static void main(String args[]) throws IOException {
        int sum = 0;
        while (!line.equals("end")) {
            if (!line.equals("init")) { 
                int[] alphaCheck = new int[58];
                
                for (int i = 0; i < 3; i++) {
                    int[] alphaCheck2 = new int[58];
                    for (int j = 0; j < line.length(); j++) {
                        if (alphaCheck2[mapAlpha(line.charAt(j))] == 0) {
                            alphaCheck[mapAlpha(line.charAt(j))] += 1;
                            alphaCheck2[mapAlpha(line.charAt(j))] = 1;
                        }

                        if (alphaCheck[mapAlpha(line.charAt(j))] == 3) {
                            sum += mapAlpha(line.charAt(j));
                            break;
                        }
                    }    
                    line = input.readLine();
                }
            } else {
                line = input.readLine();
            } 
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