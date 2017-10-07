import static org.junit.Assert.*;

import org.junit.Test;

public class LowestCommonAncestorTest {
	
	@Test
	public void testSizeEmpty() {
		LowestCommonAncestor binaryTreeEmpty = new LowestCommonAncestor();
		assertEquals("Lowest common ancestor of empty tree", -1, binaryTreeEmpty.LowestCommonAncestor(0, 0));
	}
	
	@Test
	public void testSizeOneNode() {
		LowestCommonAncestor binaryTreeOneNode = new LowestCommonAncestor();
		binaryTreeOneNode.rootNode = new Node(1);
		assertEquals("Lowest common ancestor of one node tree", -1, binaryTreeOneNode.LowestCommonAncestor(1, 0));
	}
	
	@Test
	public void testSize15Nodes() {
		LowestCommonAncestor binaryTree15Nodes = new LowestCommonAncestor();
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
		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestor(1, 2));
		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestor(2, 3));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestor(2, 4));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestor(4, 5));
		assertEquals("Lowest common ancestor of 15 node tree", 3, binaryTree15Nodes.LowestCommonAncestor(6, 7));
		assertEquals("Lowest common ancestor of 15 node tree", 4, binaryTree15Nodes.LowestCommonAncestor(4, 8));
		assertEquals("Lowest common ancestor of 15 node tree", 4, binaryTree15Nodes.LowestCommonAncestor(8, 9));
		assertEquals("Lowest common ancestor of 15 node tree", 6, binaryTree15Nodes.LowestCommonAncestor(6, 12));
		assertEquals("Lowest common ancestor of 15 node tree", 7, binaryTree15Nodes.LowestCommonAncestor(14, 15));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestor(8, 2));
		assertEquals("Lowest common ancestor of 15 node tree", 2, binaryTree15Nodes.LowestCommonAncestor(9, 11));
		assertEquals("Lowest common ancestor of 15 node tree", 3, binaryTree15Nodes.LowestCommonAncestor(13, 14));
		assertEquals("Lowest common ancestor of 15 node tree", 1, binaryTree15Nodes.LowestCommonAncestor(15, 1));
	}
	
	

}
