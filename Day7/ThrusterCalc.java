import Intcode.Comp;
import java.util.ArrayList;
import java.util.Arrays;

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
        int ampOutput = 0;
        int[] uInput = {0,0};
        int[] pSettings = {0,1,2,3,4};

//        for (int phase : pSettings) {
//            uInput[0] = phase;
//            uInput[1] = ampOutput;
//            ampOutput = mComputer.runProgram(pInput, uInput);
//
//        }

        permCalc(pSettings, pSettings.length, pSettings.length);

        System.out.println(phaseArray.size());
        



    }

}
