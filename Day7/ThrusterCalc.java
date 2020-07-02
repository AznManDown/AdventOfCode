import Intcode.Comp;
import IntMemory.PMemory;
import java.util.ArrayList;
import java.util.HashMap;

public class ThrusterCalc {
    static ArrayList<int[]> phaseArray = new ArrayList<>();

    private static void printArr(int[] phase, int n) {
        int[] tempArray = {0,1,2,3,4};
        for (int i = 0; i < n; i++) {
            tempArray[i] = phase[i];
        }
        phaseArray.add(tempArray);
    }

    private static void permCalc(int[] phase, int size, int n) {

        if (size == 1) {
            printArr(phase, n);
        }

        for (int i = 0; i <  size; i++) {
            permCalc(phase, size - 1, n);

            int temp;
            if (size % 2 == 1) {
                temp = phase[0];
                phase[0] = phase[size - 1];
            } else {
                temp = phase[i];
                phase[i] = phase[size - 1];
            }
            phase[size - 1] = temp;
        }
    }

    public static void main(String[] args) {
        Comp mComputer = new Comp();
        ArrayList<Integer> pInput = mComputer.readProgram("Day7/AmpController");

        int currentSetting = 0;
        int[] pSettings = {5,6,7,8,9};

        permCalc(pSettings, pSettings.length, pSettings.length);

        for (int[] currentPerm : phaseArray) {
            //HashMap for memory storage
            HashMap<Integer, PMemory> ampProg = new HashMap<>();
            //Custom Class for data formatting. ArrayList, Start Position, End Position, IntComputer output.
            PMemory ampOutput = new PMemory(null, 0, 0, 0, 0);
            int[] uInput = {0, 0};
            boolean firstLoop = true;

            //Memory init
            for (int i = 0; i < 5; i++) {
                ampProg.put(i, new PMemory(null, 0,0,0, 0));
            }

            while (ampProg.get(4).exitCode != 99) {
                for (int x = 0; x < currentPerm.length; x++) {
                    if (firstLoop == true ) {
                        uInput[0] = currentPerm[x];
                    } else {
                        uInput[0] = ampOutput.output;
                    }

                    uInput[1] = ampOutput.output;

                    ampOutput = mComputer.runProgram(pInput, uInput, ampProg.get(x));
                    ampProg.replace(x, ampOutput);
                }

                if (firstLoop == true) {
                    firstLoop = false;
                }

                if (ampOutput.output > currentSetting) {
                    currentSetting = ampOutput.output;
                    System.out.println(currentSetting);
                }
            }
        }

        System.out.println(currentSetting);
    }

}
