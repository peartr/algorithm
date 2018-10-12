package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		
		/* Create a matrix */
        int nodes = 17;
        
        Matrix m = new Matrix (nodes, nodes);
        
        /* Connect the nodes in the matrix */
        /* These connections are for the graph illustrated
         * on slide 24 */
        /* This first set of connections assume the graph
         * is directed. */
        m.connectNodes(0, 1);
        m.connectNodes(0, 2);        
        m.connectNodes(1, 3); 
        m.connectNodes(1, 4);
        m.connectNodes(2, 5);
        m.connectNodes(5, 7);
        m.connectNodes(3, 6);
        m.connectNodes(6, 8);
        
        /* This second set of connections assume the graph
         * is undirected. */
        m.connectNodes(1, 0);
        m.connectNodes(2, 0);
        m.connectNodes(3, 1);
        m.connectNodes(4, 1);
        m.connectNodes(5, 2);
        m.connectNodes(6, 3);
        m.connectNodes(7, 5);
                
        /* Establish the root and goal nodes */
        int root = 0;
        int goal = 6;
        
        /* Create a reviewed list */
        Reviewed r = new Reviewed();
        
        /* Create the queue */
        Queue<Integer> q = new LinkedList<Integer>();
        
        run (m, root, goal, nodes, r, q);
        
        m.resetNodes();
        
        /* These connections are for the graph illustrated
         * on slide 27 */   
        /* This set of connections assume the graph
         * is directed. */
        m.connectNodes(0, 1);
        m.connectNodes(0, 2);
        m.connectNodes(1, 3);
        m.connectNodes(2, 4);
        m.connectNodes(2, 5);
        m.connectNodes(3, 6);
        m.connectNodes(3, 7);
        m.connectNodes(7, 8);
        
        root = 0;
        goal = 7;
        
        /* Initialize reviewed list */
        r = new Reviewed();
        
        /* Initialize the queue */
        q = new LinkedList<Integer>();
        
        run (m, root, goal, nodes, r, q);
        
        m.resetNodes();
        
        /* These connections are for the graph illustrated
         * on slide ?? */         
        m.connectNodes(0, 1);
        m.connectNodes(0, 2);
        m.connectNodes(0, 3);
        m.connectNodes(1, 4);
        m.connectNodes(1, 5);
        m.connectNodes(2, 6);
        m.connectNodes(2, 7);
        m.connectNodes(3, 8);
        m.connectNodes(3, 9);
        m.connectNodes(5, 10);
        m.connectNodes(5, 11);
        m.connectNodes(8, 12);
        m.connectNodes(8, 13);
        
        root = 0;
        goal = 11;
        
        /* Initialize reviewed list */
        r = new Reviewed();
        
        /* Initialize the queue */
        q = new LinkedList<Integer>();
        
        run (m, root, goal, nodes, r, q);

	}
	
	/**
	 * Displays search order using the breadth-first-search algorithm.
	 * @param m specified adjacency matrix
	 * @param root specified root node
	 * @param goal specified goal node
	 * @param nodes total number of nodes
	 * @param r reviewed list
	 * @param q queue
	 */
	public static void run (Matrix m, int root, int goal, int nodes, Reviewed r, Queue<Integer> q){
		
        /* Declare variable used to store dequeued nodes */
        int node;
              
        /* Enqueue the root node */
        q.add(root);
        
        System.out.print("Search Order is : ");
        
        /* While the queue is not empty */
        while (!q.isEmpty()){
            
            /* Dequeue a node and print it out in the search order */
            node = q.remove();
            System.out.print(node + " ");
            
            /* Add the dequeued node to reviewed list */
            r.addNodeToReviewed(node);
            
            /* If the dequeued node is equal to the goal, stop searching */
            if (node == goal) {
                break;
            }
            
            /* Loop through all of the nodes in the graph starting with the root */
            for (int i = 0; i < nodes; i++){
                
                /* If the current node is adjacent to the dequeued node
                 * and it hasn't already been reviewed. */
                if (m.isNodeAdjacent(node, i) && !r.isNodeReviewed(i))
                	/* Enqueue the current node */
                    q.add(i);
            }      
        }
        
        System.out.println();
	}

}