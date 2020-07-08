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

    public static int findImage(int[][] data) {
        int zeroCount = 0;

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                if (data[row][col] == 0) {
                    zeroCount++;
                }
            }
        }
        return zeroCount;
    }

    public static void digitMulti(int[][] data) {
        int oneCount = 0;
        int twoCount = 0;
        int multiResult;

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                if (data[row][col] == 1) {
                    oneCount++;
                }

                if (data[row][col] == 2) {
                    twoCount++;
                }
            }
        }

        multiResult = oneCount * twoCount;

        System.out.println(multiResult);
    }

    public static void generateImage(HashMap<Integer, int[][]> data) {
        int[][] generatedImage = new int[6][25];

        //generatedImage init.
        for (int row = 0; row < generatedImage.length; row++) {
            for (int col = 0; col < generatedImage[row].length; col++) {
                generatedImage[row][col] = 2;
            }
        }

        for (int cLayer = 0; cLayer < data.size(); cLayer++) {
            int[][] lData = data.get(cLayer);

            for (int row = 0; row < lData.length; row++) {
                for (int col = 0; col < lData[row].length; col++) {
                    if (generatedImage[row][col] == 2) {
                        generatedImage[row][col] = lData[row][col];
                    }
                }
            }
        }

        for (int row = 0; row < generatedImage.length; row++) {
            for (int col = 0; col < generatedImage[row].length; col++) {
                System.out.print(generatedImage[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //Read Instruction List
        ArrayList<BigInteger> instructionList = readProgram("Day8/input");
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

        LinkedList<ArrayList<Integer>> chunkTemp = new LinkedList<>();
        LinkedList<Integer> resultCount = new LinkedList<>();
        HashMap<Integer, int[][]> dataStorage = new HashMap<>();
        int zeroCount = 0;

        //Split instructions into proper sized chunks
        for (int i=0; i < stack.size(); i += 150) {
            ArrayList<Integer> tempArray = new ArrayList<>();
            for (int x=i; x < i+150; x++) {
                tempArray.add(stack.get(x));
            }
            chunkTemp.add(tempArray);
        }

        //Add chunks to HashMap for storage
        for (int i=0; i < chunkTemp.size(); i++) {
            dataStorage.put(i, buildImage(chunkTemp.get(i), 25, 6));
        }

        for (int x = 0; x < dataStorage.size(); x++) {
            resultCount.add(findImage(dataStorage.get(x)));
        }

        for (int y = 0; y < resultCount.size(); y++) {
            int cCount = resultCount.get(y);
            if (y == 0) {
                zeroCount = cCount;
            }

            if (cCount < zeroCount) {
                zeroCount = cCount;
            }
        }

        //digitMulti(dataStorage.get(resultCount.indexOf(zeroCount)));
        generateImage(dataStorage);



    }
}
