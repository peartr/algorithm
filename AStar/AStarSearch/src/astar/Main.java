package astar;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {

    /* Create the open and closed lists */
    private static Comparator<Board> comparator = new BoardComparator();
    private static PriorityQueue<Board> open = 
            new PriorityQueue<Board>(10, comparator);
    private static Closed closed = new Closed();
    
    /* Declare variables that will be used for 
     * purposes of processing the OPEN and CLOSED lists and generating
     * child Board objects.
     */
    private static Board b;
    private static int heuristic, blank, depth;
    //private static int depth;
    private static int board [][] = new int [3][3];               
    
    /* Create an array that represents the valid 
     * moves that may be made on the board 
     * 0	1	2
     * 3	4	5
     * 6	7	8
     * 0 can move to 1 and 3
     * 1 can move to 0, 2, and 4
     * 2 can move to 1 and 5
     * 3 can move to 0, 4, and 6
     */
    private static int moves [][] = new int [][]{
   /* 0 */ {1, 3, 9, 9},
   /* 1 */ {0, 2, 4, 9},
   /* 2 */ {1, 5, 9, 9},
   /* 3 */ {0, 4, 6, 9},
   /* 4 */ {1, 3, 5, 7},
   /* 5 */ {2, 4, 8, 9},
   /* 6 */ {3, 7, 9, 9},
   /* 7 */ {4, 6, 8, 9},
   /* 8 */ {5, 7, 9, 9}};
   
    private static int element1 [] = new int [2];
    private static int element2 [] = new int [2];
    private static int moveFrom;
    
    private static int cboard [][] = new int [3][3];
    
    private static String id = new String();
    private static int cBlank, cDepth, cHeuristic;
        
	public static void main(String[] args) {
				
		/* Create the root Board object */
        Board root = new Board();
        
        run(root);
	}
	
	/* Uses the a-star-algorithm to solve the 8 Board puzzle */
	public static void run (Board root) {
		  	
        /* Enqueue the root Board object on the OPEN list */
        open.add(root);
                             
        /* While the OPEN list isn't empty */
        while (open.size() != 0){
            
            /* Dequeue a Board object from the OPEN list and get its attributes */ 
            b = open.remove();
            heuristic = b.getH();
            blank = b.getBlank();
            board = b.getBoard();
            depth = b.getG();
            
            /* If the heuristic is equal to 0, end processing */
            if (heuristic == 0){
                System.out.println("\nGoal Achieved ...");
                System.out.println(b.toString());
                break;
            }
            
            /* Add the Board object to the closed list */
            closed.add(b);
            
            System.out.println("\nEvaluating Board ...");
            System.out.println(b.toString());
            
            /* If the depth is greater than 15, skip the current
             * iteration and get the next Board object
             */ 
            if (depth > 15)
               continue;
            
            enumerateChildren();
        } 
        
        if (open.size() == 0)
        	System.out.println("OPEN list is empty - no solution found within a depth of " + (depth-1) + "!");
	}
	
	/* Enumerates the next set of child board objects in the graph */
	public static void enumerateChildren() {
		
		System.out.println("\nGenerating Children ...");
        
        /* For the max number of moves an array element may make */
        for (int i = 0; i < 4; i++){
        
            /* Process the move if it's not equal to 9 */
            if (moves[blank][i] != 9){
            
                /* Create a copy of the board dequeued from the open list */
                cboard = Board.copyBoard(board);
                
                /* Get the indices for the array element that's going to move
                 * into the blank space
                 */
                element1 = 
                		Board.getElement(moves[blank][i]);
                
                /* Get the indices for the array element that has the blank space 
                */
                element2 = Board.getElement(blank);
            
                /* On the copied board, swap the array element with the array 
                 * element that has the blank space 
                 */
                moveFrom = board[element1[0]][element1[1]];
                cboard[element2[0]][element2[1]] = moveFrom;
                cboard[element1[0]][element1[1]] = 0;  
            
                processChild(cboard);
            }  
        }
	}
	
	/* Processes each of the child board objects that has been enumerated */
	public static void processChild (int cboard[][]){
		
		/* Get the attributes for the child board and create a
         * Board object
         */
		id = Board.getId(cboard);
        cBlank = Board.findBlankSpace(cboard);
        cDepth = b.getG() + 1;
        cHeuristic = Board.evaluateBoard(cboard);
        
        Board b = new Board (cboard, id, cDepth, cHeuristic, cBlank);
        
        /* If the child Board object exists on the CLOSED list,
         * skip the current child and process the next child
         */
        if (closed.isClosed(b))
            return;
        
        /* If the child Board object already exists on the OPEN list, get its
         * f.  If the f of the Board object on the OPEN list is better
         * than the f of the child Board, keep the existing Board on the OPEN list,
         * else add the child Board to the OPEN list and remove the existing Board
         * from the OPEN list.
         */                   
        if (open.contains(b)){
            
            Board ob = null;
            int oF = 0;
            
            Iterator<Board> it = open.iterator();
            while(it.hasNext()){
            	
            	ob = (Board) it.next();                        	
            	if (ob.equals(b)){
            		
            		/* Get f of Board object on OPEN list */                       		
            		oF = ob.getF();
            		
            		if (oF < b.getF()) {
            			/* If the f of Board object on the OPEN list is better  
                    	 * than that of the child Board, keep existing Board */
                        System.out.println(ob.toString());
                        break;                                       
                    } else {                                       
                    	/* If the f of child Board object is better than that of 
                    	 * the Board object on OPEN list, add child Board and remove
                    	 * existing Board */                                    
                    	open.add(b);
                    	open.remove(ob);
                        System.out.println(b.toString());
                        break;
                    }
            	}
            }                       
        } else {                      
            /* If the child Board object doesn't already exist on the OPEN
             * list, add it.
             */                        
        	open.add(b);
            System.out.println(b.toString());
        }
	}
}