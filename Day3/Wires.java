import java.util.ArrayList;
import java.io.*;

public class Wires {

    public static void main(String[] args) {
        //Read input of two wires. Sort into two arrays.
        String firstMem = "";
        String secondMem = "";

        try (BufferedReader bReader = new BufferedReader(new FileReader("input"))) {
            String sCurrentLine;
            //This is bad. Rewrite to be more dynamic, time permitting. 
            sCurrentLine = bReader.readLine();
            firstMem = sCurrentLine;
            sCurrentLine = bReader.readLine();
            secondMem = sCurrentLine;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Process Wire Data into managable chunks. Spit into Direction and Location.
        String[] firstWire = firstMem.split(",");
        String[] secondWire = secondMem.split(",");
        ArrayList<Character> firstDirection = new ArrayList<Character>();
        ArrayList<String> firstLoc = new ArrayList<String>();
        ArrayList<Character> secondDirection = new ArrayList<Character>();
        ArrayList<String> secondLoc = new ArrayList<String>();

        for (String direction :  firstWire) {
            Character dInput = direction.charAt(0);
            String lInput = direction.replaceAll("[A-Z]", "");
            firstDirection.add(dInput);
            firstLoc.add(lInput);
        }

        for (String direction :  secondWire) {
            Character dInput = direction.charAt(0);
            String lInput = direction.replaceAll("[A-Z]", "");
            secondDirection.add(dInput);
            secondLoc.add(lInput);
        }



    }
}