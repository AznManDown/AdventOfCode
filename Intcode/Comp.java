package Intcode;

import java.util.ArrayList;

public class Comp {

    public static int Intcomp(ArrayList<Integer> memory) {

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
}
