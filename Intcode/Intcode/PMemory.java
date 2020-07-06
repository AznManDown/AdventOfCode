package Intcode;
import java.util.ArrayList;

public class PMemory {

    public ArrayList<Integer> instructionList = new ArrayList<>();
    public int currentPosStart;
    public int currentPosEnd;
    public int output;
    public int exitCode;

    public PMemory(ArrayList<Integer> instructionList, int currentPosStart, int currentPosEnd,int exitCode, int output) {
        this.instructionList = instructionList;
        this.currentPosStart = currentPosStart;
        this.currentPosEnd = currentPosEnd;
        this.exitCode = exitCode;
        this.output = output;
    }

}
