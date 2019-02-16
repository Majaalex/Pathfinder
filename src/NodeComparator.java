import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        if (node1.getTotalF() > node2.getTotalF()){
            return 1;
        } else if (node1.getTotalF() < node2.getTotalF()){
            return -1;
        }
        return 0;
    }
}
