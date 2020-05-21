import java.util.ArrayList;
import java.io.*;

public class Intercode {

    public static int mainFunction(ArrayList<Integer> memory) {

        ArrayList<Integer> mList = new ArrayList<Integer>(memory);

        int currentPosStart = 0;
        int currentPosEnd = 0;

        while (currentPosEnd <= (mList.size() - 1)) {

            //Temp array to store segments of 4.
            int tempInt = currentPosStart;
            ArrayList<Integer> tempList = new ArrayList<Integer>();

            for (int i = 0; i <= 3; i++) {
                tempList.add(mList.get(tempInt));
                tempInt++;
            }

            //Read Instructions
            switch (tempList.get(0)) {
                case 1: 
                    int posOne = tempList.get(1);
                    int posTwo = tempList.get(2);
                    int outPos = tempList.get(3);

                    int posAdd = mList.get(posOne) + mList.get(posTwo);

                    mList.set(outPos, posAdd);
                    break;
                case 2:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);
                    outPos = tempList.get(3);

                    int posMult = mList.get(posOne) * mList.get(posTwo);

                    mList.set(outPos, posMult);
                    break;
                case 99:
                    return mList.get(0);
            }

            //Advance to the next segment of 4
            currentPosStart = currentPosStart + 4;
            currentPosEnd = currentPosEnd + 4;
        }
        return mList.get(0);
    }
    
    public static void main(String[] args) {

        //Import input values
        ArrayList<Integer> mList = new ArrayList<Integer>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("Day2/input"))) {
            String sValue = bReader.readLine();

            String[] intArray = sValue.split(",");

            for (String i : intArray) {
                mList.add(Integer.parseInt(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int dResult = 0;
        int fPos = 0;

        do {
            //Copy input array for modification.
            ArrayList<Integer> cArray = new ArrayList<Integer>(mList);

            int endInput = cArray.size() - 1;
            cArray.set(1, fPos);

            for (int i = 0; i <= endInput; i++) {
                cArray.set(2, i);
                dResult = mainFunction(cArray);
                if (dResult == 19690720) {
                    System.out.println("Assembly One: " + fPos);
                    System.out.println("Assembly Two: " + i);
                }
            }
            
            fPos++;

        } while (fPos < (mList.size()));
    }
} 