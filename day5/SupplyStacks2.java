
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class SupplyStacks2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";


    public static void main(String args[]) throws IOException {


        System.out.println("Input no. of stacks: ");
        line = input.readLine();
        int numStacks = Integer.parseInt(line) + 1;
        ArrayList<Stack<Character>> stackArr = new ArrayList<Stack<Character>>();

        stackArr.add(null);

        for (int i = 1; i < numStacks; i++) {
            stackArr.add(i, new Stack<>());
        }

        System.out.println("For each line, input corresponding stack items:");

        for (int i = 1; i < numStacks; i++) {
            line = input.readLine();
            for (int j = 0; j < line.length(); j++) {
                stackArr.get(i).push(line.charAt(j));
            }
        }

        line = "init";
        System.out.println("Input moves: ");
        while (!line.equals("end")) {
            if (!line.equals("init")) {
                int movePos = line.indexOf("move");
                int fromPos = line.indexOf("from");
                int toPos = line.indexOf("to");

                int numMove = Integer.parseInt(line.substring(movePos + 5, fromPos - 1));
                int subject = Integer.parseInt(line.substring(fromPos + 5, toPos - 1));
                int object = Integer.parseInt(line.substring(toPos + 3, line.length()));

                Stack<Character> subjectStack = stackArr.get(subject);
                Stack<Character> objectStack = stackArr.get(object);

                Stack<Character> reverser = new Stack<>();
                for (int n = 0; n < numMove; n++) {
                    reverser.push(subjectStack.pop());
                }
                for (int n = 0; n < numMove; n++) {
                    objectStack.push(reverser.pop());
                }
            }
            line = input.readLine();
        }


        StringBuilder result = new StringBuilder();

        for (int i = 1; i < numStacks; i++) {
            result.append(stackArr.get(i).pop());
        }

        output.println(result.toString());
        output.close();
        input.close();
    }
}
