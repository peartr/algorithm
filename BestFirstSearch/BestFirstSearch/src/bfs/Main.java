package bfs;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	/* Create the open and closed lists */
	private static Comparator<Board> comparator = new BoardComparator();
	private static PriorityQueue<Board> open = 
            new PriorityQueue<Board>(10, comparator);
	private static Closed closed = new Closed();
	
	/* Declare variables that will be used for 
     * purposes of processing the lists and generating
     * child board objects.
     */
    private static Board b;
    private static int board [][] = new int[4][4];       
    private static int cboard1 [][] = new int [4][4];
    private static int cboard2 [][] = new int [4][4];
    private static String id = new String();
    private static int cost = 0;
    private static int depth;
	
	public static void main(String[] args) {
		
		/* Create the root board object*/
        Board root = new Board ();
        
        run(root);
	}
	
	/* Uses the best-first-search algorithm to solve the NQueens puzzle */
	public static void run(Board root) {
		
		/* Enqueue the root board object on the open and closed lists */  
        open.add(root);
        closed.add(root);
        
        /* While the open list isn't empty */
        while (open.size() != 0){
            
            /* Dequeue a board object from the open list and 
             * get its depth, cost and the actual board */
            b = open.remove();
            cost = b.getG();   
            depth = b.getH();
            board = b.getBoard();
            
            /* If the cost of the dequeued a board object is 
             * equal to 0, end processing */
            if (cost == 0){
                System.out.println("\nGoal Achieved ...");
                b.displayBoard();
                System.out.println(b.toString());
                break;
            }
            
            /* Display the contents of the board object */
            System.out.println("\nEvaluating Board ...");
            b.displayBoard();
            System.out.println(b.toString());
                                   
            enumerateChildren();
            
        }
	}
	
	/* Enumerates the next set of child board objects in the graph */
	public static void enumerateChildren() {
		
		/* Get the depth for the child board objects */
        depth += 1;
        
        System.out.println("\nGenerating Children ...");
        for (int i = 0; i < board.length; i++){            
            for (int j = 0; j < board[i].length; j++){
                
                /* If the current element is a queen */
                if (board[i][j] == 1){
                
                    /* Copy board and remove the queen from the copies */
                    cboard1 = Board.copyBoard(board);
                    cboard2 = Board.copyBoard(board);
                    cboard1[i][j] = 0;
                    cboard2[i][j] = 0;
                    
                    if (j == 0){
                    
                        /* If the queen was in the first column, move it
                         * one element to the right and process the child board
                         */                            	
                        cboard1[i][j+1] = 1;    
                        processChild(cboard1);
                        
                    } else if ((j == 1) || (j == 2)){
                    
                        /* If the queen was in the second or third columns, move it
                         * one element to the right and one element to the left, and
                         * process the child boards
                         */ 
                        cboard1[i][j-1] = 1;
                        cboard2[i][j+1] = 1;
                        processChild(cboard1);
                        processChild(cboard2);                        
                    
                    } else {
                    
                        /* If the queen was in the fourth column, move it
                         * one element to the left and process the child board
                         */  
                        cboard1[i][j-1] = 1;
                        processChild(cboard1);            
                    }
                }   
            }
        }
	}
	
	/* Processes each of the child board objects that has been enumerated */
	public static void processChild(int board[][]){
		
		/* Get id and cost of board */
		id = Board.getId(board);
        cost = Board.evaluateBoard(board);
    
        /* Create a board Object */
        Board b = new Board (board, id, depth, cost);
    
        /* If the board object isn't on the closed
         * list, enqueue it on the open and closed lists
         */
        if (!closed.isReviewed(b)){
            open.add(b);
            closed.add(b);
            
            b.displayBoard();
            System.out.println(b.toString());
        }
	}
}