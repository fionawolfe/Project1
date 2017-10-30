import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DirectedAcyclicGraphTest {

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

	node2.ancestors = new ArrayList<Node>();
	node3.ancestors = new ArrayList<Node>();
	node4.ancestors = new ArrayList<Node>();
	node5.ancestors = new ArrayList<Node>();
	node6.ancestors = new ArrayList<Node>();

	rootNode.ancestors = null;

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

	assertEquals(3,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode,node6,node3));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node4, node5));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node6, node2));
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
}






