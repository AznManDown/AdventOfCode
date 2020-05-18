import java.util.ArrayList;
import java.io.*;

public class Wires {

    public static String[][] plotRoute(ArrayList<Character> direction, ArrayList<Integer> location) {

        String[][] plotGrid = new String[30000][30000];
        int xCord = 15000;
        int yCord = 15000;

        plotGrid[xCord][yCord] = "O";

        for (int i = 0; i < direction.size(); i++) {
            switch (direction.get(i)) {
                case 'R':
                    int endLoc = yCord + location.get(i);
                    for (int x = yCord; x <= endLoc; x++) {
                        plotGrid[xCord][x] = "X";
                    }
                    yCord = endLoc;
                    break;
                case 'L':
                    endLoc = yCord - location.get(i);
                    for (int x = yCord; x >= endLoc; x--) {
                        plotGrid[xCord][x] = "X";
                    }
                    yCord = endLoc;
                    break;
                case 'D':
                    endLoc = xCord + location.get(i);
                    for (int x = xCord; x <= endLoc; x++) {
                        plotGrid[x][yCord] = "X";
                    }
                    xCord = endLoc;
                    break;
                case 'U':
                    endLoc = xCord - location.get(i);
                    for (int x = xCord; x >= endLoc; x--) {
                        plotGrid[x][yCord] = "X";
                    }
                    xCord = endLoc;
                    break;
            }
        }
        return plotGrid;
    }

    public static void checkOverlap(String[][] firstWire, String[][] secondWire) {
        ArrayList<String> wireOne = new ArrayList<String>();
        ArrayList<String> wireTwo = new ArrayList<String>();
        ArrayList<String> matchList = new ArrayList<String>();

        for (int i = 0; i < firstWire.length; i++) {
            for (int x = 0; x < firstWire[0].length; x++) {
                if (firstWire[i][x] != null) {
                    wireOne.add(i + "," + x);
                }
            }
        }

        for (int i = 0; i < secondWire.length; i++) {
            for (int x = 0; x < secondWire[0].length; x++) {
                if (secondWire[i][x] != null) {
                    wireTwo.add(i + "," + x);
                }
            }
        }

        for (int i = 0; i < wireOne.size(); i++) {
            for (int x = 0; x < wireTwo.size(); x++) {
                if (wireOne.get(i).equals(wireTwo.get(x))) {
                    matchList.add(wireOne.get(i));
                }
            }
        }

        System.out.println(matchList);

    }

    public void


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

        String[][] firstRoute = plotRoute(firstDirection, firstLoc);
        String[][] secondRoute = plotRoute(secondDirection, secondLoc);

        checkOverlap(firstRoute, secondRoute);

    }
}