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
	public void testSizeTwoNodes() {
		LowestCommonAncestor binaryTreeTwoNodes = new LowestCommonAncestor();
		binaryTreeTwoNodes.rootNode = new Node(1);
		binaryTreeTwoNodes.rootNode.left = new Node(2);
		assertEquals("Lowest common ancestor of two node tree", -1, binaryTreeTwoNodes.LowestCommonAncestor(1, 2));
	}
	
	@Test
	public void testSizeThreeNodes() {
		LowestCommonAncestor binaryTreeThreeNodes = new LowestCommonAncestor();
		binaryTreeThreeNodes.rootNode = new Node(1);
		binaryTreeThreeNodes.rootNode.left = new Node(2);
		binaryTreeThreeNodes.rootNode.right = new Node(3);
		assertEquals("Lowest common ancestor of three node tree", 1, binaryTreeThreeNodes.LowestCommonAncestor(2, 3));
	}
	
	

}
