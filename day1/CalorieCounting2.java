package day1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CalorieCounting2 {

    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static int first = 0;
    public static int second = 0;
    public static int third = 0;
    public static String line = "init";

    public static void main(String args[]) throws IOException {

        while (!line.equals("end")) {
            int count = 0;
            if (!line.equals("init")) {
                count += Integer.parseInt(line);
            }
            line = input.readLine();
            while (!line.equals("")) {
                count += Integer.parseInt(line);
                System.out.println(count);

                line = input.readLine();
            }
            if (count > first) {
                third = second;
                second = first;
                first = count;
            } else if (count > second) {
                third = second;
                second = count;
            } else if (count > third) {
                third = count;
            } 
            line = input.readLine();
        }

        output.println(first + second + third);
        output.close();
        input.close();
    }

}