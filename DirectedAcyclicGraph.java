import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Node {
	int data;
	int count;
	String colour;
	Node[] parent;
	Node[] child;
	
	Node (int value) {
		data = value;
		count = 0;
		colour = "white";
		parent = null;
		child = null;
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
	
	public void findAncestorNode1(Node node1)
	{
		if(node1.parent != null)
		{
			for(int i=0; i < node1.parent.length; i ++)
			{
				node1.parent[i].colour = "blue";
			}
		}
		node1.colour = "blue";
	}
	
	public void findAncestorNode2(Node node2)
	{
		if(node2.parent != null)
		{
			for(int i=0; i < node2.parent.length; i ++)
			{
				node2.parent[i].colour = "red";
			}
		}
		node2.colour = "red";
	}
	
	public static void main (String [] args)
	{
		Node rootNode = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		rootNode.parent = null;
		rootNode.child[0] = node2;
		node2.parent[0] = rootNode;
		node2.child[0] = node3;
		node2.child[1] = node4;
		node3.parent[0] = node2;
		node3.child[0] = node5;
		node4.parent[0] = node2;
		node4.child[0] = node6;
		node5.parent[0] = node3;
		node5.child[0] = node6;
		node6.parent[0] = node4;
		node6.parent[1] = node5;
		node6.child = null;
	}
}
