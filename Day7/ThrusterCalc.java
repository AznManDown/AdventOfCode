import Intcode.Comp;
import java.util.ArrayList;

public class ThrusterCalc {
    
    public static void main(String[] args) {
        Comp mComputer = new Comp();
        ArrayList<Integer> pInput = mComputer.readProgram("Day7/AmpController");

        System.out.println(pInput);

        
        
    }

}
