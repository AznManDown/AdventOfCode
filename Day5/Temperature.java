import Intcode.Comp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Temperature {

    public static ArrayList<Integer> readProgram(String fileName) {
        ArrayList<Integer> mList = new ArrayList<Integer>();
        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
            String sValue = bReader.readLine();
            String[] intArray = sValue.split(",");

            for (String i : intArray) {
                mList.add(Integer.parseInt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> pInput = readProgram("Day5/input");
        ArrayList<Integer> dayTwoInput = readProgram("Day2/input");
        ArrayList<Integer> testInput = readProgram("Day5/TestInput");
        Comp intComputer = new Comp();
        Scanner usrInput = new Scanner(System.in);

        System.out.println("Program ID: ");
        int uInput = Integer.parseInt(usrInput.next());

        int[] pID = {uInput};

        intComputer.runProgram(pInput, pID);

    }
}
