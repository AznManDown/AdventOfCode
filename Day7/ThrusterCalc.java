import Intcode.Comp;
import java.util.ArrayList;

public class ThrusterCalc {
    
    public static void main(String[] args) {
        Comp mComputer = new Comp();
        HeapAlgo hAlgo = new HeapAlgo();
        ArrayList<Integer> pInput = mComputer.readProgram("Day7/AmpController");

        int currentSetting = 0;
        int ampOutput = 0;
        int[] uInput = {0,0};
        int[] pSettings = {0,1,2,3,4};
        //ArrayList<int[]> pResults = hAlgo.heapPermutation(pSettings, pSettings.length, pSettings.length);

        for (int phase : pSettings) {
            uInput[0] = phase;
            uInput[1] = ampOutput;
            ampOutput = mComputer.runProgram(pInput, uInput);

        }

        //System.out.println(pResults.get(0));



    }

}
