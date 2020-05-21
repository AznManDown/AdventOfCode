package Intcode;

import java.util.ArrayList;

public class Comp {

    public static int Intcomp(ArrayList<Integer> memory, int pID) {

        ArrayList<Integer> mList = new ArrayList<Integer>(memory);
        int currentPosStart = 0;
        int currentPosEnd = 0;
        while (currentPosEnd <= (mList.size() - 1)) {
            //Temp array to store segments of 4.
            int tempInt = currentPosStart;
            ArrayList<Integer> tempList = new ArrayList<Integer>();

            switch (mList.get(tempInt)) {
                case 1:
                case 2:
                    for (int i = 0; i <= 3; i++) {
                        tempList.add(mList.get(tempInt));
                        tempInt++;
                    }
                    break;
                case 3:
                case 4:
                    for (int i = 0; i <= 1; i++) {
                        tempList.add(mList.get(tempInt));
                        tempInt++;
                    }
                    break;
                case 99:
                    tempList.add(99);
                    break;
            }

            //Read Instructions
            switch (tempList.get(0)) {
                case 1:
                    int posOne = tempList.get(1);
                    int posTwo = tempList.get(2);
                    int outPos = tempList.get(3);

                    int posAdd = mList.get(posOne) + mList.get(posTwo);

                    mList.set(outPos, posAdd);

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 2:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);
                    outPos = tempList.get(3);

                    int posMult = mList.get(posOne) * mList.get(posTwo);

                    mList.set(outPos, posMult);

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 3:
                    int destPos = tempList.get(1);
                    mList.set(destPos, pID);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    break;
                case 4:
                    int readVal = mList.get(tempList.get(1));

                    System.out.println(readVal);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    break;
                case 99:
                    return mList.get(0);
            }
        }
        return mList.get(0);
    }
}
