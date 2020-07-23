import Intcode.*;
import java.util.ArrayList;

public class Boost {

    public static void main(String[] args) {
        Comp mComputer = new Comp();
        ArrayList<Long> iList = mComputer.readProgram("Day9/input");
        int[] input = {1};

        PMemory memory = new PMemory(null, 0, 0, 0, 0L);

        PMemory data = mComputer.runProgram(iList, input, memory);

        //System.out.println(data.output);
    }
}
