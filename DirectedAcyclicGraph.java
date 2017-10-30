import java.util.ArrayList;
import java.util.List;

class Node {
	int data;
	Node left;
	Node right;
	ArrayList <Node> ancestors;
	
	Node (int value) {
		data = value;
		left = right;
		left = null;
		right = null;
		ancestors = null;
	}
}

public class DirectedAcyclicGraph {

	static Node rootNode;
	static List<Integer> Node1Path = new ArrayList<>();
	static List<Integer> Node2Path = new ArrayList<>();
	
	static int LowestCommonAncestorFunctionBinaryTree (int node1, int node2)
	{
		Node1Path.clear();
		Node2Path.clear();
		return findLowestCommonAncestorBinaryTree(rootNode, node1, node2);
	}
	
	static int LowestCommonAncestorFunctionDAG (Node node1, Node node2)
	{
		return findLowestCommonAncestorDAG(rootNode, node1, node2);
	}
	
	private static int findLowestCommonAncestorBinaryTree(Node rootNode, int node1, int node2)
	{
		if (!findPathBinaryTree(rootNode, node1, Node1Path) || !findPathBinaryTree(rootNode, node2, Node2Path)) 
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
	
	static int findLowestCommonAncestorDAG(Node rootNode, Node node1, Node node2)
	{
		if (node1.ancestors != null && node2.ancestors != null) {
			for (int i = 0; i < node2.ancestors.size(); i++) {
				for (int j = 0; j < node1.ancestors.size(); j++) {
					if (node2.ancestors.get(i) == node1.ancestors.get(j)) {
						return node2.ancestors.get(i).data;
					}
				}
			}
		} else
			return rootNode.data;
		return 0;
	}
	
	private static boolean findPathBinaryTree(Node rootNode, int n, List<Integer> path)
    {
        if (rootNode == null) {
            return false;
        }
 
        path.add(rootNode.data);
 
        if (rootNode.data == n) {
            return true;
        }
 
        if (rootNode.left != null && findPathBinaryTree(rootNode.left, n, path)) {
            return true;
        }
 
        if (rootNode.right != null && findPathBinaryTree(rootNode.right, n, path)) {
            return true;
        }
 
        path.remove(path.size()-1);
 
        return false;
    }
	
	public static void addAncestorsToNode(Node node1, Node node2)
	{
		for (int i=0; i < node1.ancestors.size(); i++)
		{
			if(!node2.ancestors.contains(node1.ancestors.get(i)))
			{
				node2.ancestors.add(node1.ancestors.get(i));
			}
		}
	}
	public static void addAncestorsToNodeAtPosition(int position, Node node1, Node node2)
	{
		for (int i=0; i < node1.ancestors.size(); i++)
		{
			if(!node2.ancestors.contains(node1.ancestors.get(i)))
			{
				node2.ancestors.add(position, node1.ancestors.get(i));
			}
		}
	}
}
