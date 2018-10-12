package dfs;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		/* Create a matrix */
        int nodes = 17;
        
        Matrix m = new Matrix (nodes, nodes);
        
        /* Connect the nodes in the matrix */
        m.connectNodes(0, 1);
        m.connectNodes(0, 2);
        m.connectNodes(0, 3);
        m.connectNodes(1, 4);
        m.connectNodes(1, 5);
        m.connectNodes(2, 6);
        m.connectNodes(3, 7);
        m.connectNodes(3, 8);
        m.connectNodes(4, 9);
        m.connectNodes(4, 10);
        m.connectNodes(4, 11);      
        m.connectNodes(6, 12);
        m.connectNodes(6, 13);
        m.connectNodes(7, 14);
        m.connectNodes(7, 15);
        m.connectNodes(8, 16);
        
        m.connectNodes(1, 0);
        m.connectNodes(2, 0);
        m.connectNodes(3, 0);
        m.connectNodes(4, 1);
        m.connectNodes(5, 1);
        m.connectNodes(6, 2);
        m.connectNodes(7, 3);
        m.connectNodes(8, 3);
        m.connectNodes(9, 4);
        m.connectNodes(10, 4);
        m.connectNodes(11, 4);      
        m.connectNodes(12, 6);
        m.connectNodes(13, 6);
        m.connectNodes(14, 7);
        m.connectNodes(15, 7);
        m.connectNodes(16, 8);
        
        /* Establish the root and goal nodes */
        int root = 0;
        int goal = 16;
        
        /* Create a reviewed list */
        Reviewed r = new Reviewed();
        
        /* Create the stack */
        Stack<Integer> s = new Stack<Integer>();
        
        run (m, root, goal, nodes, r, s);
	}
	
	/**
	 * Displays search order using the depth-first-search algorithm.
	 * @param m specified adjacency matrix
	 * @param root specified root node
	 * @param goal specified goal node
	 * @param nodes total number of nodes
	 * @param r reviewed list
	 * @param s stack
	 */
	public static void run (Matrix m, int root, int goal, int nodes, Reviewed r, Stack<Integer> s){
        
        /* Declare variable used to store popped nodes */
        int node;
        
        System.out.print("Search Order is : ");
        
        /* Push the root node onto the stack */
        s.push(root);
               
        /* While the stack is not empty */
        while (!s.empty()){
            
            /* Pop a node from the stack and print it out in the search order */
            node = s.pop();
            System.out.print(node + " ");
                     
            /* If the popped node is equal to the goal, stop searching */
            if (node == goal) {
                break;
            }
            
            /* Add the popped node to reviewed list */
            r.addNodeToReviewed(node);
            
            /* Loop through all of the nodes in the graph 
             * starting with node farthest from the root */
            for (int i = nodes - 1; i >= 0; i--){
                
                /* If the current node is adjacent to the popped node
                 * and it hasn't already been reviewed. */
                if (m.isNodeAdjacent(node, i) && !r.isNodeReviewed(i))
                	/* Push the current node onto the stack */
                    s.push(i);
            }      
        }
        
        System.out.println();
	}

}