import java.util.ArrayList;
import java.io.*;

public class Wires {

    public static void plotRoute(ArrayList<Character> direction, ArrayList<Integer> location) {

        String[][] plotGrid = new String[20000][20000];
        int xCord = 10000;
        int yCord = 10000;

        for (int i = 0; i < direction.size(); i++) {
            switch (direction.get(i)) {
                case 'R':
                    plotGrid[xCord][yCord + location.get(i)] = "X";
                    yCord = yCord + location.get(i);
                    System.out.println("X: " + xCord + ", Y: " + yCord);
                    break;
                case 'L':
                    plotGrid[xCord][yCord - location.get(i)] = "X";
                    yCord = yCord - location.get(i);
                    System.out.println("X: " + xCord + ", Y: " + yCord);
                    break;
                case 'U':
                    plotGrid[xCord + location.get(i)][yCord] = "X";
                    xCord = xCord + location.get(i);
                    System.out.println("X: " + xCord + ", Y: " + yCord);
                    break;
                case 'D':
                    plotGrid[xCord + location.get(i)][yCord] = "X";
                    xCord = xCord - location.get(i);
                    System.out.println("X: " + xCord + ", Y: " + yCord);
                    break;
            }
        }
    }

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
        ArrayList<Integer> firstLoc = new ArrayList<Integer>();
        ArrayList<Character> secondDirection = new ArrayList<Character>();
        ArrayList<Integer> secondLoc = new ArrayList<Integer>();

        for (String direction :  firstWire) {
            Character dInput = direction.charAt(0);
            String lInput = direction.replaceAll("[A-Z]", "");
            firstDirection.add(dInput);
            firstLoc.add(Integer.parseInt(lInput));
        }

        for (String direction :  secondWire) {
            Character dInput = direction.charAt(0);
            String lInput = direction.replaceAll("[A-Z]", "");
            secondDirection.add(dInput);
            secondLoc.add(Integer.parseInt(lInput));
        }

        plotRoute(firstDirection, firstLoc);

    }
}