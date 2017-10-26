import java.util.ArrayList;
import java.util.List;

class Node {
	int data;
	int count;
	String colour;
	
	Node (int value) {
		data = value;
		count = 0;
		colour = "white";
	}
}

public class DirectedAcyclicGraph {

	Node rootNode;
	List<Integer> Node1Path = new ArrayList<>();
	List<Integer> Node2Path = new ArrayList<>();
	
	int LowestCommonAncestor (int node1, int node2)
	{
		Node1Path.clear();
		Node2Path.clear();
		return findLowestCommonAncestor(rootNode, node1, node2);
	}
	
	private int findLowestCommonAncestor(Node rootNode, int node1, int node2)
	{
		if (!findPath(rootNode, node1, Node1Path) || !findPath(rootNode, node2, Node2Path)) 
			{
				if(Node1Path.size() == 0 && Node2Path.size() == 0)
				{
					if(!Node1Path.contains(node1) && !Node2Path.contains(node2))
					{
						System.out.println("These nodes does not exist in the tree.");
					}
					else
					System.out.println("Tree is empty.");
				}
				if (Node1Path.size() > 0 && Node2Path.size() == 0)
				{
					System.out.println("Node 1 is present but Node 2 is not.");
				}
				if (Node2Path.size() > 0 && Node1Path.size() == 0)
				{
					System.out.println("Node 2 is present but Node 1 is not.");
				}
				return -1;
			}
			int i;
			for (i = 0; i < Node1Path.size() && i < Node2Path.size(); i++)
			{
				if (!Node1Path.get(i).equals(Node2Path.get(i)))
				{
					break;
				}
			}
			return Node1Path.get(i - 1);
	}
	
	private boolean findPath(Node rootNode, int n, List<Integer> path)
    {
        if (rootNode == null) {
            return false;
        }
 
        path.add(rootNode.data);
 
        if (rootNode.data == n) {
            return true;
        }
 
        if (rootNode.left != null && findPath(rootNode.left, n, path)) {
            return true;
        }
 
        if (rootNode.right != null && findPath(rootNode.right, n, path)) {
            return true;
        }
 
        path.remove(path.size()-1);
 
        return false;
    }
	
}
