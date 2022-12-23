import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class CathodeRayTube {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static int x = 1;

    public static void main(String args[]) throws IOException {
        
        Queue<String> execQ = new LinkedList<String>();

        while (!line.equals("end")) {
            if (!line.equals("init")) {
                String[] cmdLine = line.split(" ");
                String cmd = cmdLine[0];
                if (cmd.equals("noop")) {
                    execQ.offer(cmd);
                } else {
                    execQ.offer("noop");
                    execQ.offer(line);
                }
            }
            line = input.readLine();
        }

        int resultSum = 0;
        int targetCycle = 20;
        int curCycle = 1;
    
        while (targetCycle <= 220) {
            if (curCycle == targetCycle) {
                targetCycle += 40;

                output.println("x: " + x);
                output.println("curCycle * x: " + curCycle * x);
                resultSum += (curCycle * x);
                output.println(curCycle + ": " + resultSum);
            }

            execute(execQ.poll());
            curCycle += 1;
        }

        output.println(resultSum);
        output.close();
        input.close();

    }

    public static void execute(String cmdLine) { 
        String[] cmd = cmdLine.split(" ");
        if (cmd[0].equals("noop")) {

        } else {
            x += Integer.parseInt(cmd[1]);
        }
    }




}
