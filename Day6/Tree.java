import java.util.LinkedList;
import java.util.List;

public class Tree {
    public List<Tree> children = new LinkedList<Tree>();
    public Tree parent = null;
    public String data;

    public Tree(String data, Tree parent) {
        this.data = data;
        this.parent = parent;
    }
}
