package dls;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		/* Create a matrix */
        int nodes = 17;
        
        Matrix m = new Matrix (nodes, nodes);
        
        /* Connect the nodes in the matrix */
        m.connectNodes(0, 1, 1);
        m.connectNodes(0, 2, 1);
        m.connectNodes(0, 3, 1);
        m.connectNodes(1, 4, 2);
        m.connectNodes(1, 5, 2);
        m.connectNodes(2, 6, 2);
        m.connectNodes(3, 7, 2);
        m.connectNodes(3, 8, 2);
        m.connectNodes(4, 9, 3);
        m.connectNodes(4, 10, 3);
        m.connectNodes(4, 11, 3);      
        m.connectNodes(6, 12, 3);
        m.connectNodes(6, 13, 3);
        m.connectNodes(7, 14, 3);
        m.connectNodes(7, 15, 3);
        m.connectNodes(8, 16, 3); 
        
        /* Establish the root and goal nodes */
        int root = 0;
        int goal = 16;
        int limit = 3; 
        
		/* Create a reviewed list */
        Reviewed r = new Reviewed();
        
        /* Create the node stack */
        Stack<Integer> s = new Stack<Integer>();
        
        run (m, root, goal, limit, nodes, r, s);
        
        m.resetNodes();
        
        m.connectNodes(0, 1, 1);
        m.connectNodes(0, 2, 1);
        m.connectNodes(1, 4, 2);
        m.connectNodes(1, 3, 2);
        m.connectNodes(2, 5, 2);
        m.connectNodes(5, 7, 3);
        m.connectNodes(3, 6, 3);
        m.connectNodes(6, 8, 4);
        
        m.connectNodes(1, 0, 0);
        m.connectNodes(2, 0, 0);
        m.connectNodes(4, 1, 1);
        m.connectNodes(3, 1, 1);
        m.connectNodes(5, 2, 1);
        m.connectNodes(7, 5, 2);
        m.connectNodes(6, 3, 2);
        m.connectNodes(8, 6, 3);
        
        /* Create the root, goal, and limit (depth) */
        root = 0;
        goal = 8;
        limit = 4;
        
		/* Create a reviewed list */
        r = new Reviewed();
        
        /* Create the node stack */
        s = new Stack<Integer>();
        
        run (m, root, goal, limit, nodes, r, s);
	}
	
	/**
	 * Displays search order using the depth-limited-search algorithm.
	 * @param m specified adjacency matrix
	 * @param root specified root node
	 * @param goal specified goal node
	 * @param nodes total number of nodes
	 * @param r reviewed list
	 * @param s stack
	 */
	public static void run (Matrix m, int root, int goal, int limit, int nodes, Reviewed r, Stack<Integer> s){
        
        /* Declare variable used to store dequeued nodes */
        int node;
              
        /* Push the root node on the stack */
        s.push(root);
        
        System.out.print("Search Order is : ");
        
        /* While the node stack is not empty */
        while (!s.empty()){
            
            /* Pop a node from the node stack and a depth from the
             * depth stack and print them out in the search order */
            node = s.pop();
            System.out.print(node + " ");
                       
            /* If the popped node is equal to the goal, stop searching */
            if (node == goal) {
                break;
            }
            
            /* Add the popped node to reviewed list */
            r.addNodeToReviewed(node);
            
            /* Loop through all of the nodes in the graph
             * starting with node farthest from the root for the self imposed depth */
            for (int i = nodes - 1; i >= 0 && m.getNodeDepth(node, i) <= limit; i--){

            	/* If the current node is adjacent to the popped node
            	 * and it hasn't already been reviewed. */
            	if (m.isNodeAdjacent(node, i) && !r.isNodeReviewed(i)){
            		/* Push the current node */
            		s.push(i);
            	}
            }               
        }
        
        System.out.println();
	}
}