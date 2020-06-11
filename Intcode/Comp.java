package Intcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Comp {

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

    public static int runProgram(ArrayList<Integer> memory, int[] usrInput) {

        //currentPosEnd isn't being utilized correctly. Fix later.
        int cInput = 0;
        int pID = 0;

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
                case 8:
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
                    if (cInput < usrInput.length) {
                        pID = usrInput[cInput];
                    }
                    int destPos = tempList.get(1);
                    mList.set(destPos, pID);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    cInput++;
                    break;
                case 4:
                    //Read value and return
                    int readVal;
                    if (paramOne == 1) {
                        readVal = tempList.get(1);
                    } else {
                        readVal = mList.get(tempList.get(1));
                    }

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    return readVal;
                case 5:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);

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

                    if (intA != 0) {
                        currentPosStart = intB;
                        currentPosEnd = intB;
                    } else {
                        currentPosStart = currentPosStart + 3;
                        currentPosEnd = currentPosEnd + 3;
                    }
                    break;
                case 6:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);

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

                    if (intA == 0) {
                        currentPosStart = intB;
                        currentPosEnd = intB;
                    } else {
                        currentPosStart = currentPosStart + 3;
                        currentPosEnd = currentPosEnd + 3;
                    }
                    break;
                case 7:
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

                    if (intA < intB) {
                        mList.set(outPos, 1);
                    } else {
                        mList.set(outPos, 0);
                    }

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 8:
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

                    if (intA == intB) {
                        mList.set(outPos, 1);
                    } else {
                        mList.set(outPos, 0);
                    }

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 99:
                    return mList.get(0);
            }
        }
        return mList.get(0);
    }
}
