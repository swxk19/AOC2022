import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MonkeyInTheMiddle2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static ArrayList<Monkey2> monkeyList = new ArrayList<>();
    public static long productEverythingModulo = 1;

    public static void main(String args[]) throws IOException {
        while (!line.equals("end")) {
            if (!line.equals("init") && !line.equals("")) {
                String[] monkeyInfo = new String[6];
                monkeyInfo[0] = line;
                for (int monkeyInfoLine = 1; monkeyInfoLine < 6; monkeyInfoLine++) {
                    line = input.readLine();
                    monkeyInfo[monkeyInfoLine] = removeLeadingSpace((line)); 
                }

                monkeyList.add(new Monkey2(monkeyInfo));
            }
            line = input.readLine();
        }

        for (int monkey = 0; monkey < monkeyList.size(); monkey++) {
            productEverythingModulo *= monkeyList.get(monkey).test;
        }



        int round = 1;
        while (round < 10001) { 
            for (int monkey = 0; monkey < monkeyList.size(); monkey++) {
                Monkey2 cur = monkeyList.get(monkey);
                cur.inspectItems();
            }
            round++;
        }



        // for (int i = 0; i < monkeyList.size(); i++) {
        //     output.println(monkeyList.get(i).toString());
        // }

        long max = -2;
        long secondMax = -1;

        for (int i = 0; i < monkeyList.size(); i++) {
            Monkey2 cur = monkeyList.get(i);
            if (cur.inspected > max) {
                secondMax = max;
                max = cur.inspected;
            } else if (cur.inspected > secondMax) {
                secondMax = cur.inspected;
            }
            output.println(cur.inspected);
        }

        output.println(max);
        output.println(secondMax);
        output.println(max * secondMax);

        output.close();
        input.close();
    }

    public static String removeLeadingSpace(String s) {
        int start = 0;
        while (s.charAt(start) == ' ') {
            start += 1;
        }
        return s.substring(start, s.length());
    }

}

class Monkey2 {
    int id;
    Queue<Long> holding = new LinkedList<>();
    String[] operation;
    long test;
    int ifTrue;
    int ifFalse;
    int inspected = 0;
    
    public Monkey2(String[] info) {
        this.id = Integer.parseInt(info[0].split(" ")[1].substring(0, info[0].split(" ")[1].indexOf(":")));
        for (String item : info[1].substring(16, info[1].length()).split(", ")) {
            holding.add(Long.parseLong(item));
        }
        this.operation = info[2].substring(21, info[2].length()).split(" ");
        this.test = Long.parseLong(info[3].split(" ")[3]);
        this.ifTrue = Integer.parseInt(info[4].split(" ")[5]);
        this.ifFalse = Integer.parseInt(info[5].split(" ")[5]);
    }

    @Override 
    public String toString() {

        String holdingTemp = "";
        for (int item = 0; item < this.holding.size(); item++) {
            long cur = this.holding.poll();
            holdingTemp += cur + " ";
            holding.offer(cur);
        }

        return "id: " + this.id + "\n" + "holding: " + holdingTemp + "\n" +
         "operation: " + this.operation + "\n" + "test: " + this.test + "\n" + "ifTrue: " + this.ifTrue + "\n" + "ifFalse: " + this.ifFalse;
    }

    public void inspectItems() {
        while (!holding.isEmpty()) {
            long item = holding.poll();
            item = this.operate(item) % MonkeyInTheMiddle2.productEverythingModulo;
            boolean testResult = item % test == 0;
            if (testResult) {
                Monkey2 trueM = MonkeyInTheMiddle2.monkeyList.get(ifTrue);
                MonkeyInTheMiddle2.monkeyList.get(ifTrue).holding.offer(item);
            } else {
                MonkeyInTheMiddle2.monkeyList.get(ifFalse).holding.offer(item);
            }
            this.inspected++;
        }
    }

    public long operate(long item) {
        String operator = this.operation[0];
        String operandTemp = this.operation[1];
        long operand;
        if (operandTemp.equals("old")) {
            operand = item;
        } else {
            operand = Integer.parseInt(operandTemp);
        }

        if (operator.equals("+")) {
            return item + operand;
        } else {
            return item * operand;
        }

    }


}
