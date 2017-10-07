import static org.junit.Assert.*;

import org.junit.Test;

public class LowestCommonAncestorTest {
	
	@Test
	public void testSize() {
		LowestCommonAncestor binaryTreeEmpty = new LowestCommonAncestor();
		assertEquals("Lowest common ancestor of empty tree", -1, binaryTreeEmpty.LowestCommonAncestor(0, 0));
		
	}

}
