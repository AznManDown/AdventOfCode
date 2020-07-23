package Intcode;
import java.util.ArrayList;

public class PMemory {

    public ArrayList<Long> instructionList = new ArrayList<>();
    public int currentPosStart;
    public int currentPosEnd;
    public Long output;
    public int exitCode;

    public PMemory(ArrayList<Long> instructionList, int currentPosStart, int currentPosEnd,int exitCode, Long output) {
        this.instructionList = instructionList;
        this.currentPosStart = currentPosStart;
        this.currentPosEnd = currentPosEnd;
        this.exitCode = exitCode;
        this.output = output;
    }

}
