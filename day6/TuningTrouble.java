import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TuningTrouble {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";

    public static void main(String args[]) throws IOException {
            line = input.readLine();
            Queue<Character> process = new LinkedList<>();
            int count = 0;

            for (int i = 0; i < line.length(); i++) {
                if (i > 3) {
                    if (checkPacketStart(process)) {
                        break;
                    }
                    process.poll();
                }

                process.offer(line.charAt(i));
                count += 1;
            }

            

            output.println(count);
            output.close();
            input.close();
    }

    public static boolean checkPacketStart(Queue<Character> q) {
        HashMap<Character, Integer> h = new HashMap<>();
        boolean result = true;
        int qSize = q.size();

        for (int i = 0; i < qSize; i++) {
            char cur = q.poll();
            result = result && (h.get(cur) == null);
            h.put(cur, 1);
            q.offer(cur);
        }

        return result;
    }
}
