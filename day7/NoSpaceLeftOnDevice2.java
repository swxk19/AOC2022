import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class NoSpaceLeftOnDevice2 {
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static String line = "init";
    public static long totalSize = 70000000;
    public static long minSpaceNeeded = 30000000;

    public static void main(String args[]) throws IOException {

        Dir curDir = null;

        ArrayList<Dir> dirList = new ArrayList<>();
        int lineCount = 0;

        while (!line.equals("end")) {
            if (!line.equals("init")) {
                if (line.charAt(0) == '$') {    // command executed
                    if (line.indexOf("ls") != 2) {     // cd command
                        if (curDir == null) {   // init root
                            curDir = new Dir("/", null, new HashMap<>(), 0);
                            dirList.add(curDir);
                        } else {
                            String childDirName = line.substring(line.indexOf("cd") + 3, line.length());
                            
                            if (childDirName.equals("..")) {
                                if (curDir.parent != null) {  
                                    curDir = curDir.parent;
                                }
                            } else {
                                curDir = curDir.child.get(childDirName);     // change current directory
                            }
                       
                        }
                    } else {        // ls command

                    }
                } else {    // terminal output from ls
                    if (line.indexOf("dir ") == 0) {    // directory
                        String childDirName = line.substring(line.indexOf("dir") + 4, line.length());
                        Dir newDir = new Dir(childDirName, curDir, new HashMap<>(), 0);
                        curDir.child.put(childDirName, newDir);
                        newDir.parent = curDir;
                        
                        dirList.add(newDir);

                    } else {    // a file
                        long fileSize = Long.parseLong(line.substring(0, line.indexOf(" ")));
                        curDir.size += fileSize;
                        curDir.addParentSize((fileSize));
                    }

                
                }
            }
            // if (curDir != null) {
            //     List<String> children = new ArrayList<String>(curDir.child.keySet());
            //     System.out.println("Line count: " + lineCount);
            //     System.out.println("cur dir: " + curDir.dirName);
            //     for (int j = 0; j < children.size(); j++) {
            //         System.out.println("child:" + children.get(j));
            //     }
            // } else {
            //     System.out.println("null");
            // }
            line = input.readLine();
            lineCount += 1;
            

        }
        
        long result = 999999999;
        long unusedSpace = totalSize - dirList.get(0).size;
        long spaceNeeded = minSpaceNeeded - unusedSpace;

        for (int i = 0; i < dirList.size(); i++) {
            Dir cur = dirList.get(i);
            if (cur.size >= spaceNeeded) {
                result = Math.min(result, cur.size);
            }
        }


        output.println(result);
        output.close();
        input.close();
    }
}

class Dir {
    String dirName;
    Dir parent;
    HashMap<String, Dir> child;
    long size;
    
    public Dir (String dirName, Dir parent, HashMap<String, Dir> child, long size){
        this.dirName = dirName;
        this.parent = parent;
        this.child = child;
        this.size = size;
    }

    public void addParentSize(long newSize) {
        if (this.parent != null) {
            this.parent.size += newSize;
            this.parent.addParentSize(newSize);
        }
    }

}
