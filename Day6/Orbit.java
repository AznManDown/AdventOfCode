import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Orbit {

    //Global values for map of Tree and number of orbits. Might change later.
    static int countResult = 0;
    static HashMap<String, Tree> nodeMap = new HashMap<>();

    public static LinkedList<String> readInput() {
        LinkedList<String> mList = new LinkedList<>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("Day6/input"))) {
            String sCurrentLine;

            while((sCurrentLine = bReader.readLine()) != null) {
                mList.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mList;
    }

    public static HashSet<String> createSet(LinkedList<String> data) {
        HashSet<String> hSet = new HashSet<>();

        for (String cString : data) {
            String[] dSplit = cString.split("\\)");
            String inputA = dSplit[0];
            String inputB = dSplit[1];

            hSet.add(inputA);
            hSet.add(inputB);
        }
        return hSet;
    }

    public static void calcOrbits(HashMap<String, Tree> data, String currentNode, int orbitCount) {

        if (data.get(currentNode).children.isEmpty()) {
            nodeMap.get(currentNode).orbitCount = orbitCount;
            countResult = countResult + orbitCount;
        } else {
            countResult = countResult + orbitCount;

            for (int i=0; i < data.get(currentNode).children.size(); i++) {
                nodeMap.get(currentNode).orbitCount = orbitCount;
                String nextNode = data.get(currentNode).children.get(i).data;
                calcOrbits(data, nextNode, orbitCount + 1);
            }
        }
    }

    public static int calcPath(HashMap<String, Tree> data, String start, String end) {
        Tree sPosition = data.get(start).parent;
        Tree ePosition = data.get(end).parent;
        ArrayList<String> sPathList = new ArrayList<>();
        ArrayList<String> ePathList = new ArrayList<>();

        while (sPosition.parent != null) {
            System.out.println("NODE A: " + sPosition.data);
            sPathList.add(sPosition.data);
            sPosition = sPosition.parent;
        }

        while (ePosition != null) {
            System.out.println("NODE B: " + ePosition.data);
            ePathList.add(ePosition.data);
            ePosition = ePosition.parent;
        }

        for (int i = 0; i < sPathList.size(); i++) {
            for (int x = 0; x < ePathList.size(); x++) {
                if (sPathList.get(i) == ePathList.get(x)) {
                    return x + i;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        //Read instruction input
        LinkedList<String> orbitData = new LinkedList<String>(readInput());
        //Set and Hashmap to create initial nodes for tree
        HashSet<String> nodeList = createSet(orbitData);

        for (String cNode : nodeList) {
            nodeMap.put(cNode, new Tree(cNode, null, 0));
        }

        for(String line : orbitData) {
            String[] lineSplit = line.split("\\)");
            String inputA = lineSplit[0];
            String inputB = lineSplit[1];

            nodeMap.get(inputA).children.add(nodeMap.get(inputB));
            nodeMap.get(inputB).parent = nodeMap.get(inputA);
        }

        //Calculate orbit with COM designated as root.
        calcOrbits(nodeMap, "COM", 0);
        int result = calcPath(nodeMap, "YOU", "SAN");

        System.out.println(result);

    }
}
