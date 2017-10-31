import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DirectedAcyclicGraphTest {

	@Test
	public void testSizeEmpty() {
		DirectedAcyclicGraph binaryTreeEmpty = new DirectedAcyclicGraph();

		assertEquals("Lowest common ancestor of empty tree", -1, binaryTreeEmpty.LowestCommonAncestorFunctionBinaryTree(0, 0));
	}

	@Test
	public void testSizeOneNode() {
		DirectedAcyclicGraph binaryTreeOneNode = new DirectedAcyclicGraph();
		binaryTreeOneNode.rootNode = new Node(1);

		assertEquals("Lowest common ancestor of one node tree", -1, binaryTreeOneNode.LowestCommonAncestorFunctionBinaryTree(1, 0));
	}

	DirectedAcyclicGraph binaryTree15Nodes = new DirectedAcyclicGraph();

	@Test
	public void testSize15Nodes() {
		binaryTree15Nodes.rootNode = new Node(1);
		binaryTree15Nodes.rootNode.left = new Node(2);
		binaryTree15Nodes.rootNode.right = new Node(3);
		binaryTree15Nodes.rootNode.left.left = new Node(4);
		binaryTree15Nodes.rootNode.left.right = new Node(5);
		binaryTree15Nodes.rootNode.right.left = new Node(6);
		binaryTree15Nodes.rootNode.right.right = new Node(7);
		binaryTree15Nodes.rootNode.left.left.left = new Node(8);
		binaryTree15Nodes.rootNode.left.left.right = new Node(9);
		binaryTree15Nodes.rootNode.left.right.left = new Node(10);
		binaryTree15Nodes.rootNode.left.right.right = new Node(11);
		binaryTree15Nodes.rootNode.right.left.left = new Node(12);
		binaryTree15Nodes.rootNode.right.left.right = new Node(13);
		binaryTree15Nodes.rootNode.right.right.left = new Node(14);
		binaryTree15Nodes.rootNode.right.right.right = new Node(15);

		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(1, 2));
		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(2, 3));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(2, 4));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(4, 5));
		assertEquals("Lowest common ancestor of 15 node tree", 3, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(6, 7));
		assertEquals("Lowest common ancestor of 15 node tree", 4, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(4, 8));
		assertEquals("Lowest common ancestor of 15 node tree", 4, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(8, 9));
		assertEquals("Lowest common ancestor of 15 node tree", 6, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(6, 12));
		assertEquals("Lowest common ancestor of 15 node tree", 7, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(14, 15));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(8, 2));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(9, 11));
		assertEquals("Lowest common ancestor of 15 node tree", 3, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(13, 14));
		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(15, 1));
	}

	@Test
	public void testNonExistentNode() {
		DirectedAcyclicGraph binaryTree15Nodes = new DirectedAcyclicGraph();
		binaryTree15Nodes.rootNode = new Node(1);
		binaryTree15Nodes.rootNode.left = new Node(2);
		binaryTree15Nodes.rootNode.right = new Node(3);
		binaryTree15Nodes.rootNode.left.left = new Node(4);
		binaryTree15Nodes.rootNode.left.right = new Node(5);
		binaryTree15Nodes.rootNode.right.left = new Node(6);
		binaryTree15Nodes.rootNode.right.right = new Node(7);
		binaryTree15Nodes.rootNode.left.left.left = new Node(8);
		binaryTree15Nodes.rootNode.left.left.right = new Node(9);
		binaryTree15Nodes.rootNode.left.right.left = new Node(10);
		binaryTree15Nodes.rootNode.left.right.right = new Node(11);
		binaryTree15Nodes.rootNode.right.left.left = new Node(12);
		binaryTree15Nodes.rootNode.right.left.right = new Node(13);
		binaryTree15Nodes.rootNode.right.right.left = new Node(14);
		binaryTree15Nodes.rootNode.right.right.right = new Node(15);

		assertEquals(-1, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(2, 17));
		assertEquals(-1, binaryTree15Nodes.LowestCommonAncestorFunctionBinaryTree(23, 17));
	}

	@Test
	public void outOfOrderTest() {
		DirectedAcyclicGraph binaryTreeOutOfOrder = new DirectedAcyclicGraph();
		binaryTreeOutOfOrder.rootNode = new Node(5);
		binaryTreeOutOfOrder.rootNode.left = new Node(3);
		binaryTreeOutOfOrder.rootNode.right = new Node(1);
		binaryTreeOutOfOrder.rootNode.left.left = new Node(4);
		binaryTreeOutOfOrder.rootNode.left.right = new Node(7);
		binaryTreeOutOfOrder.rootNode.right.left = new Node(2);
		binaryTreeOutOfOrder.rootNode.right.right = new Node(6);

		assertEquals("Lowest common ancestor of tree out of order", 5, binaryTreeOutOfOrder.LowestCommonAncestorFunctionBinaryTree(6, 4));
		assertEquals("Lowest common ancestor of tree out of order", 3, binaryTreeOutOfOrder.LowestCommonAncestorFunctionBinaryTree(3, 4));
		assertEquals("Lowest common ancestor of tree out of order", 1, binaryTreeOutOfOrder.LowestCommonAncestorFunctionBinaryTree(6, 2));
		assertEquals("Lowest common ancestor of tree out of order", 5, binaryTreeOutOfOrder.LowestCommonAncestorFunctionBinaryTree(7, 2));
	}
@Test
public void testDAG1 ()
{
	
//       1
//       |
//       ^
//	     |
//	     2 --<-- 3
//	     |       |
//	     ^       ^
//	     |       |
//	     4       5
//        \     /
//         ^   ^
//          \ /
//           6
	
	Node rootNode = new Node(1);
	Node node2 = new Node(2);
	Node node3 = new Node(3);
	Node node4 = new Node(4);
	Node node5 = new Node(5);
	Node node6 = new Node(6);

	rootNode.ancestors = new ArrayList<Node>();
	node2.ancestors = new ArrayList<Node>();
	node3.ancestors = new ArrayList<Node>();
	node4.ancestors = new ArrayList<Node>();
	node5.ancestors = new ArrayList<Node>();
	node6.ancestors = new ArrayList<Node>();

	rootNode.ancestors.add(rootNode);

	node2.ancestors.add(node2);
	node2.ancestors.add(rootNode);
	node3.ancestors.add(node3);
	node4.ancestors.add(node4);
	node5.ancestors.add(node5);
	node6.ancestors.add(node6);
	
	DirectedAcyclicGraph.addAncestorsToNode(node2, node3);
	DirectedAcyclicGraph.addAncestorsToNode(node2, node4);
	DirectedAcyclicGraph.addAncestorsToNode(node3, node5);
	DirectedAcyclicGraph.addAncestorsToNode(node5, node6);
	DirectedAcyclicGraph.addAncestorsToNodeAtPosition(1, node4, node6);

	assertEquals(3,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node6, node3));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node4, node5));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node6, node2));
	assertEquals(1,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node2, rootNode));
}

@Test
public void testDAG2 ()
{
	//             3
	//            / \
	//           ^   V
	//          /     \
	//   1 ->- 2  ->-  5
	//          \     / \
	//           V   ^   V
	//            \ /     \
	//             4       6
	//            /
	//           ^
	//          /
	//         7
	
	
	Node node1 = new Node(1);
	Node node2 = new Node(2);
	Node node3 = new Node(3);
	Node node4 = new Node(4);
	Node node5 = new Node(5);
	Node node6 = new Node(6);
	Node node7 = new Node(7);
	
	node1.ancestors = new ArrayList<Node>();
	node2.ancestors = new ArrayList<Node>();
	node3.ancestors = new ArrayList<Node>();
	node4.ancestors = new ArrayList<Node>();
	node5.ancestors = new ArrayList<Node>();
	node6.ancestors = new ArrayList<Node>();
	node7.ancestors = new ArrayList<Node>();
	
	
	node1.ancestors.add(node1);
	node2.ancestors.add(node2);
	node3.ancestors.add(node3);
	node4.ancestors.add(node4);
	node5.ancestors.add(node5);
	node6.ancestors.add(node6);
	node7.ancestors.add(node7);
	
	DirectedAcyclicGraph.addAncestorsToNode(node6, node5);
	DirectedAcyclicGraph.addAncestorsToNode(node3, node2);
	DirectedAcyclicGraph.addAncestorsToNode(node4, node2);
	DirectedAcyclicGraph.addAncestorsToNode(node5, node3);
	DirectedAcyclicGraph.addAncestorsToNode(node5, node4);
	DirectedAcyclicGraph.addAncestorsToNode(node5, node2);
	DirectedAcyclicGraph.addAncestorsToNode(node2, node1);
	DirectedAcyclicGraph.addAncestorsToNode(node4, node7);
	
	assertEquals(4, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node1, node7));
	assertEquals(5, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node3, node7));
	assertEquals(6, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node5, node6));
	assertEquals(5, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node3, node4));
	assertEquals(4, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node2, node7));
	assertEquals(2, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node6, node1, node2));

}

@Test
public void testGraphOneNode()
{
	Node node1 = new Node(1);
	assertEquals(1, DirectedAcyclicGraph.findLowestCommonAncestorDAG(node1, node1, node1));
}
}






