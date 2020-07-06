import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ImageFormat {

    public static ArrayList<BigInteger> readProgram(String fileName) {
        ArrayList<BigInteger> mList = new ArrayList<>();

        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
            String sValue = bReader.readLine();
            String[] intArray = sValue.split(",");

            for (String i : intArray) {
                mList.add(new BigInteger(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }

    public static int[][] buildImage(ArrayList<Integer> data, int a, int b) {
        int[][] twoDArray = new int[b][a];
        int instrucCount = 0;
        for (int i=0; i < b; i++) {
            for (int x=0; x < a; x++) {
                twoDArray[i][x] = data.get(instrucCount);
                instrucCount++;
            }
        }
        return twoDArray;
    }

    public static void main(String[] args) {
        //Read Instruction List
        ArrayList<BigInteger> instructionList = readProgram("Day8/TestInput");
        BigInteger number = instructionList.get(0);
        LinkedList<Integer> stack = new LinkedList<>();

        //Split numbers into individual Integers
        while (number.compareTo(new BigInteger("10")) == 1) {
            BigInteger sepNum = number.remainder(new BigInteger("10"));
            stack.push(sepNum.intValue());
            number = number.divide(new BigInteger("10"));
        }
        //Final Instruction Push
        stack.push(number.intValue());

        LinkedList<ArrayList<Integer>> tempList = new LinkedList<>();
        HashMap<Integer, int[][]> dataStorage = new HashMap<>();

        for (int i=0; i < stack.size(); i += 6) {
            ArrayList<Integer> tempArray = new ArrayList<>();
            for (int x=i; x < i+6; x++) {
                tempArray.add(stack.get(x));
            }
            tempList.add(tempArray);
        }

        for (int i=0; i < tempList.size(); i++) {
            dataStorage.put(i, buildImage(tempList.get(i), 3, 2));
        }



    }
}
