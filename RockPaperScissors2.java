import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class RockPaperScissors2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static void main(String args[]) throws IOException {
        HashMap<Integer, Integer> winCheck = new HashMap<>();
        HashMap<Integer, Integer> playScore = new HashMap<>();
        winCheck.put(0, 1);
        winCheck.put(1, 2);
        winCheck.put(2, 0);

        playScore.put(0, 1);
        playScore.put(1, 2);
        playScore.put(2, 3);

        int score = 0;

        while (!line.equals("end")) {
            if (!line.equals("init")) {
                String[] temp = line.split(" ");
                int play = mapPlay(temp[0]);
                if (temp[1].equals("Z")) {
                    score += 6;
                    score += playScore.get((play + 1) % 3);
                } else if (temp[1].equals("Y")) {
                    score += 3;
                    score += playScore.get(play);
                } else {
                    score += playScore.get(Math.floorMod(play - 1, 3));
                }
            }
            line = input.readLine();
        }

        output.println(score);
        output.close();
        input.close();
    }

    public static int mapPlay(String play) {
        if (play.equals("A")) {
            return 0;
        } else if (play.equals("B")) {
            return 1;
        } else {
            return 2;
        }
        
    }
}
