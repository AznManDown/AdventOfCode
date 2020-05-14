import java.util.ArrayList;
import java.io.*;

public class Rocket {

    public static void main(String[] args) {

        //Read module inputs from file
        ArrayList<Integer> mList = new ArrayList<Integer>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("input"))) {
            String sCurrentLine;

            while((sCurrentLine = bReader.readLine()) != null) {
                mList.add(Integer.parseInt(sCurrentLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double totalFuel = 0;
        int arrayCount = mList.size() - 1;

        //Caclulate fuel requrements per module. Mass / 3, round down, subtract 2.
        for (int i = 0; i <= arrayCount; i++) {
            int currentModule = mList.get(i);

            double initalFuel = (Math.floor(currentModule / 3) - 2);
            totalFuel = totalFuel + initalFuel;
            double requiredFuel = initalFuel;
            while (requiredFuel > 0) {
                requiredFuel = Math.floor(requiredFuel / 3 - 2);

                if (requiredFuel >= 0) {
                    totalFuel = totalFuel + requiredFuel;
                }
            }
        }

        System.out.println(totalFuel);


    }
}