import Intcode.Comp;
import java.util.ArrayList;

public class Temperature {

    public static void main(String[] args) {

        ArrayList<Integer> testInput = new ArrayList<>();

        String[] testString = "1,9,10,3,2,3,11,0,99,30,40,50".split(",");

        for (String i : testString) {
            testInput.add(Integer.parseInt(i));
        }

        Comp testRun = new Comp();

        System.out.println(testRun.Intcomp(testInput));
    }

}
