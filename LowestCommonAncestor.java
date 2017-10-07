import java.util.ArrayList;
import java.util.List;

class Node {
	int data;
	Node left;
	Node right;
	
	Node (int value) {
		data = value;
		left = right;
		left = null;
		right = null;
	}
}

public class LowestCommonAncestor {

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
				if (Node1Path.size() > 0)
				{
					System.out.println("Node 1 is present");
				}
				else
					System.out.println("Node 1 is missing");
			
				if (Node2Path.size() > 0)
				{
					System.out.println("Node 2 is present");
				}
				else
					System.out.println("Node 2 is missing");
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
