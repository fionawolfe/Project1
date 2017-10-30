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
	
	DirectedAcyclicGraph.addAncestorsToNode(node3.ancestors.size(), node2, node3);
	DirectedAcyclicGraph.addAncestorsToNode(node4.ancestors.size(), node2, node4);
	DirectedAcyclicGraph.addAncestorsToNode(node5.ancestors.size(), node3, node5);
	DirectedAcyclicGraph.addAncestorsToNode(node6.ancestors.size(), node5, node6);
	DirectedAcyclicGraph.addAncestorsToNode(1, node4, node6);

	assertEquals(3,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode,node6,node3));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node4, node5));
	assertEquals(2,DirectedAcyclicGraph.findLowestCommonAncestorDAG(rootNode, node6, node2));
}


}

