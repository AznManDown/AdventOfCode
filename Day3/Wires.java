import java.util.ArrayList;
import java.io.*;

public class Wires {

    public static void main(String[] args) {
        //Read input of two wires. Sort into two arrays.
        ArrayList<String> firstMem = new ArrayList<String>();
        ArrayList<String> secondMem = new ArrayList<String>();

        try (BufferedReader bReader = new BufferedReader(new FileReader("input"))) {
            String sCurrentLine;
            //This is bad. Rewrite to be more dynamic, time permitting. 
            sCurrentLine = bReader.readLine();
            firstMem.add(sCurrentLine);
            sCurrentLine = bReader.readLine();
            secondMem.add(sCurrentLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}