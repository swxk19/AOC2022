package day4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CampCleanup2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";

    public static void main(String args[]) throws IOException {
        int count = 0;

        while (!line.equals("end")) {
            if (!line.equals("init")) {
                String[] temp1 = line.split(",");
                String[] temp2 = temp1[0].split("-");
                String[] temp3 = temp1[1].split("-");

                int[] firstElf = {Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1])};
                int[] secondElf = {Integer.parseInt(temp3[0]), Integer.parseInt(temp3[1])};

                if (!((firstElf[1] < secondElf[0]) || (firstElf[0] > secondElf[1]))) {
                    count += 1;
                }
     
            }
            line = input.readLine();
        }
        output.println(count);
        output.close();
        input.close();
    }


}