package Intcode;

import java.util.ArrayList;

public class Comp {

    public static int runProgram(ArrayList<Integer> memory, int pID) {

        //currentPosEnd isn't being utilized correctly. Fix later.

        ArrayList<Integer> mList = new ArrayList<Integer>(memory);
        int currentPosStart = 0;
        int currentPosEnd = 0;
        while (currentPosEnd <= (mList.size() - 1)) {
            //Temp array to store segments of 4.
            int tempInt = currentPosStart;
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            int currentInt = mList.get(tempInt);
            int opCode;
            int paramOne = 0;
            int paramTwo = 0;

            if (currentInt >= 100) {
                int number = currentInt;
                ArrayList<Integer> stack = new ArrayList<>();

                while (number > 0) {
                    stack.add(number % 10);
                    number = number / 10;
                }
                while (stack.size() < 5) {
                    stack.add(0);
                }
                opCode = stack.get(0);
                paramOne = stack.get(2);
                paramTwo = stack.get(3);

                currentInt = opCode;
            }

            //Parse current instruction into temporary array.
            switch (currentInt) {
                case 1:
                case 2:
                case 7:
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
                case 5:
                case 6:
                    for (int i = 0; i <= 2; i++) {
                        tempList.add(mList.get(tempInt));
                        tempInt++;
                    }
                    break;
                case 99:
                    tempList.add(99);
                    break;
            }

            //Read Instructions & move instruction pointer forward
            switch (currentInt) {
                case 1:
                    //Add
                    int posOne = tempList.get(1);
                    int posTwo = tempList.get(2);
                    int outPos = tempList.get(3);
                    int intA;
                    int intB;

                    if (paramOne == 1) {
                        intA = posOne;
                    } else {
                        intA = mList.get(posOne);
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else {
                        intB = mList.get(posTwo);
                    }

                    int posAdd = intA + intB;

                    mList.set(outPos, posAdd);

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 2:
                    //Multiply
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);
                    outPos = tempList.get(3);

                    if (paramOne == 1) {
                        intA = posOne;
                    } else {
                        intA = mList.get(posOne);
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else {
                        intB = mList.get(posTwo);
                    }

                    int posMult = intA * intB;

                    mList.set(outPos, posMult);

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 3:
                    //Read Input & Set
                    int destPos = tempList.get(1);
                    mList.set(destPos, pID);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    break;
                case 4:
                    //Read value and return
                    int readVal = mList.get(tempList.get(1));

                    if (paramOne == 1) {
                        readVal = tempList.get(1);
                    }

                    System.out.println(readVal);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    break;
                case 5:
                    posOne = tempList.get(1);
                    outPos = tempList.get(2);

                    if (posOne != 0) {
                        currentPosStart = outPos;
                        currentPosEnd = outPos;
                    }

                    break;
                case 6:
                    posOne = tempList.get(1);
                    outPos = tempList.get(2);

                    if (posOne == 0) {
                        currentPosStart = outPos;
                        currentPosEnd = outPos;
                    }
                    break;
                case 99:
                    return mList.get(0);
            }
        }
        return mList.get(0);
    }
}
