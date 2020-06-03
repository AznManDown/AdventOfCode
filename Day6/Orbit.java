import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Orbit {

    public static LinkedList<String> readInput() {
        LinkedList<String> mList = new LinkedList<>();
        try (BufferedReader bReader = new BufferedReader(new FileReader("Day6/TestInput"))) {
            String sCurrentLine;

            while((sCurrentLine = bReader.readLine()) != null) {
                mList.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mList;
    }

    public static List<Tree> createTree(LinkedList<String> data) {

        String rootNode = "COM";
        Boolean setRoot = false;

        for (String node : data) {
            if (setRoot == false) {
                if (node.startsWith("COM") == true) {

                }
            }
        }

        Tree root = new Tree("A", null);
        Tree nodeB = new Tree("B", root);
        root.children.add(nodeB);
        Tree nodeC = new Tree("C", nodeB);
        nodeB.children.add(nodeC);
        Tree nodeD = new Tree("D", nodeC);
        nodeC.children.add(nodeD);

        return root.children;
    }

    public static void main(String[] args) {

        LinkedList<String> orbitData = new LinkedList<String>(readInput());
        List<Tree> orbitTree = new LinkedList<>(createTree(orbitData));

        System.out.println(orbitTree.get(0).data);
    }
}
