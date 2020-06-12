import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Orbit {

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

        calcOrbits(nodeMap, "COM", 0);

        System.out.println(countResult);

    }
}
