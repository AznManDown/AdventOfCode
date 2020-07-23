package Intcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Comp {

    public static ArrayList<Long> readProgram(String fileName) {
        ArrayList<Long> mList = new ArrayList<Long>();
        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
            String sValue = bReader.readLine();
            String[] intArray = sValue.split(",");

            for (String i : intArray) {
                mList.add(Long.parseLong(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mList;
    }

    private static ArrayList<Long> dynamicMemory(ArrayList<Long> data, int rValue) {
        int currentLength = data.size();

        for (int i = currentLength; i < rValue + 1; i++) {
            data.add(0L);
        }

        return data;
    }

    public static PMemory runProgram(ArrayList<Long> memory, int[] usrInput, PMemory pData) {

        //currentPosEnd isn't being utilized correctly. Fix later.
        int cInput = 0;
        int pID = 0;
        Long rBase = 0L;
        ArrayList<Long> mList;

        if (pData.instructionList != null) {
            mList = pData.instructionList;
        } else {
            mList = new ArrayList<>(memory);
        }

        int aLength = mList.size();
        int currentPosStart = pData.currentPosStart;
        int currentPosEnd = pData.currentPosEnd;
        while (currentPosEnd <= (mList.size() - 1)) {
            //Temp array to store segments of 4.
            int tempInt = currentPosStart;
            ArrayList<Long> tempList = new ArrayList<>();
            int currentInt = mList.get(tempInt).intValue();
            int opCode;
            int paramOne = 0;
            int paramTwo = 0;
            int paramThree = 0;

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
                paramThree = stack.get(4);

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
                case 9:
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
                    tempList.add(99L);
                    break;
            }

            //Read Instructions & move instruction pointer forward
            switch (currentInt) {
                case 1:
                    //Add
                    Long posOne = tempList.get(1);
                    Long posTwo = tempList.get(2);
                    Long outPos = tempList.get(3);
                    Long intA;
                    Long intB;

                    if (paramOne == 1) {
                        intA = posOne;
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        if (posOne.intValue() > aLength) {
                            dynamicMemory(mList, posOne.intValue());
                        }
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (paramThree == 2) {
                        outPos = outPos + rBase;
                    }

                    Long posAdd = intA + intB;

                    if (outPos.intValue() > aLength) {
                        dynamicMemory(mList, outPos.intValue());
                    }

                    mList.set(outPos.intValue(), posAdd);

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
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (paramThree == 2) {
                        outPos = outPos + rBase;
                    }

                    Long posMult = intA * intB;

                    if (outPos.intValue() > aLength) {
                        dynamicMemory(mList, outPos.intValue());
                    }

                    mList.set(outPos.intValue(), posMult);

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 3:
                    //Read Input & Set
                    if (cInput < usrInput.length) {
                        pID = usrInput[cInput];
                    }

                    Long destPos = 0L;

                    if (paramOne == 2) {
                        destPos = (rBase + tempList.get(1));
                    } else {
                        destPos = tempList.get(1);
                    }

                    if (destPos > aLength) {
                        dynamicMemory(mList, destPos.intValue());
                    }

                    mList.set(destPos.intValue(), (long) pID);

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    cInput++;
                    break;
                case 4:
                    //Read value and return
                    Long readVal = 0L;
                    if (paramOne == 1) {
                        readVal = tempList.get(1);
                    } else if (paramOne == 2) {
                        readVal = mList.get((int) (rBase + tempList.get(1)));
                    }
                    else {
                        readVal = mList.get(tempList.get(1).intValue());
                    }

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;

                    PMemory data = new PMemory(mList, currentPosStart, currentPosEnd,4, readVal);

                    System.out.println(readVal);

                    //return data;
                    break;
                case 5:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);

                    if (paramOne == 1) {
                        intA = posOne;
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (intA != 0) {
                        currentPosStart = intB.intValue();
                        currentPosEnd = intB.intValue();
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
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (intA == 0) {
                        currentPosStart = intB.intValue();
                        currentPosEnd = intB.intValue();
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
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (paramThree == 2) {
                        outPos = outPos + rBase;
                    }

                    if (intA < intB) {
                        mList.set(outPos.intValue(),(long) 1);
                    } else {
                        mList.set(outPos.intValue(),(long) 0);
                    }

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 8:
                    posOne = tempList.get(1);
                    posTwo = tempList.get(2);
                    outPos = tempList.get(3);

                    if (outPos.intValue() > aLength) {
                        dynamicMemory(mList, outPos.intValue());
                    }

                    if (paramOne == 1) {
                        intA = posOne;
                    } else if (paramOne == 2) {
                        intA = mList.get((int) (rBase + posOne));
                    } else {
                        intA = mList.get(posOne.intValue());
                    }

                    if (paramTwo == 1) {
                        intB = posTwo;
                    } else if (paramTwo == 2) {
                        intB = mList.get((int) (rBase + posTwo));
                    } else {
                        intB = mList.get(posTwo.intValue());
                    }

                    if (paramThree == 2) {
                        outPos = outPos + rBase;
                    }

                    if (intA == intB) {
                        mList.set(outPos.intValue(), (long) 1);
                    } else {
                        mList.set(outPos.intValue(), (long) 0);
                    }

                    currentPosStart = currentPosStart + 4;
                    currentPosEnd = currentPosEnd + 4;
                    break;
                case 9:
                    readVal = tempList.get(1);
                    int adjVal;

                    if (paramOne == 1) {
                        adjVal = readVal.intValue();
                    } else if (paramOne == 2) {
                        adjVal = mList.get((int) (rBase + readVal)).intValue();
                    } else {
                        adjVal = mList.get(readVal.intValue()).intValue();
                    }

                    rBase = rBase + adjVal;

                    currentPosStart = currentPosStart + 2;
                    currentPosEnd = currentPosEnd + 2;
                    break;
                case 99:
                    data = new PMemory(mList, currentPosStart, currentPosEnd,99, mList.get(0));
                    return data;
            }
        }
        PMemory data = new PMemory(mList, currentPosStart, currentPosEnd,0, mList.get(0));
        return data;
    }
}
