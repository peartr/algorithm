package ids;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		/* Create a matrix */
		int nodes = 24;

		Matrix m = new Matrix(nodes, nodes);

		/* Connect the nodes in the matrix */
		m.connectNodes(0, 1, 1);
		m.connectNodes(0, 2, 1);
		m.connectNodes(0, 3, 1);
		m.connectNodes(1, 4, 2);
		m.connectNodes(1, 5, 2);
		m.connectNodes(1, 6, 2);
		m.connectNodes(2, 7, 2);
		m.connectNodes(2, 8, 2);
		m.connectNodes(3, 9, 2);
		m.connectNodes(4, 10, 3);
		m.connectNodes(4, 11, 3);
		m.connectNodes(5, 12, 3);
		m.connectNodes(5, 13, 3);
		m.connectNodes(6, 14, 3);
		m.connectNodes(6, 15, 3);
		m.connectNodes(7, 16, 3);
		m.connectNodes(8, 17, 3);
		m.connectNodes(10, 18, 4);
		m.connectNodes(11, 19, 4);
		m.connectNodes(12, 20, 4);
		m.connectNodes(13, 21, 4);
		m.connectNodes(14, 22, 4);
		m.connectNodes(15, 23, 4);

		/* Create the root and goal nodes */
		int root = 0;
		int goal = 9;

		run(m, root, goal, nodes);
		
		nodes = 9;
		m = new Matrix(nodes, nodes);

		/* Connect the nodes in the matrix */
		m.connectNodes(0, 1, 1);
		m.connectNodes(0, 2, 1);
		m.connectNodes(1, 4, 2);
		m.connectNodes(1, 3, 2);
		m.connectNodes(2, 5, 2);
		m.connectNodes(5, 7, 3);
		m.connectNodes(3, 6, 3);
		m.connectNodes(6, 8, 4);

		/* Create the root and goal nodes */
		root = 0;
		goal = 8;

		run(m, root, goal, nodes);

	}

	/**
	 * Displays search order for each limit using the 
	 * iterative-deepening-search algorithm.
	 * @param m specified adjacency matrix
	 * @param root specified root node
	 * @param goal specified goal node
	 * @param nodes total number of nodes
	 */
	public static void run(Matrix m, int root, int goal, int nodes) {

		/* Set current limit to one */
		int limit = 1;

		/* Declare variable used to store dequeued nodes */
		int node;

		/* While the goal has been found */
		foundIt: while (true) {

			System.out.print("Search Order at limit " + limit + " is : ");

			/* Create the stack */
			Stack<Integer> s = new Stack<Integer>();
			
			/* Create a reviewed list */
			Reviewed r = new Reviewed();

			/* Push the root node on the stack */
			s.push(root);

			/* While the stack is not empty */
			while (!s.empty()) {

				/* Pop a node and print it out in the search order */
				node = s.pop();

				System.out.print(node + " ");
				
				/* If the popped node is equal to the goal, stop searching */
				if (node == goal) {
					break foundIt;
				}

				/* Add the popped node to reviewed list */
				r.addNodeToReviewed(node);

				/*
				 * Loop through all of the nodes in the graph starting with node farthest from
				 * the root for the current limit
				 */
				for (int i = nodes - 1; i >= 0 && m.getNodeDepth(node, i) <= limit; i--) {

					/*
					 * If the current node is adjacent to the popped node and it hasn't already been
					 * reviewed.
					 */
					if (m.isNodeAdjacent(node, i) && !r.isNodeReviewed(i)) {
						/* Push the current node */
						s.push(i);
					}
				}
			}

			/* Increase the current limit */
			limit++;
			System.out.println();

		}
		System.out.println();
	}
}