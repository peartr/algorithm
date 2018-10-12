package bfs;
import java.util.Random;

/**
 * The Board class creates the board objects that get
 * placed in the open and closed queues by the best-first-search algorithm.
 * Each board object has a 4 by 4 board,
 * an id, cost, depth, and heuristic function.  This class has accessor
 * methods that return the board, id, cost, and depth.  The id generated 
 * by this class is the hexidecimal representation of the board.
 */
public class Board {
    
	/**
	 * Represents the 4 by 4 board.
	 */
    int board [][] = new int [4][4];
    /**
	 * Represents the heuristic (depth).
	 */
    int h;
    /**
	 * Represents the cost (number of conflicts).
	 */
    int g;
    /**
	 * Represents the evaluation function (g + h).
	 */
    int f;
    /**
	 * Represents the hexidecimal id of the 4 by 4 board.
	 */
    String id = new String();
        
    /**
     * Initializes root board object.
     */
    public Board () {
        
    	Random r = new Random();
    	
        int column;
        
        for (int i = 0; i < this.board.length; i++){
            
            column = r.nextInt(4);
            
            for (int j = 0; j < this.board[i].length; j++){
                
                if (j == column)
                    this.board[i][j] = 1;
                else
                    this.board[i][j] = 0;
            }
        }
        
        this.h = 0;
        this.g = evaluateBoard(this.board);
        this.f = this.h + this.g;
        this.id = getId(this.board);
    }
    
    /**
     * Initializes child board objects.
     * @param b 4 by 4 child board
     * @param id hexidecimal id of child board
     * @param depth depth of child board
     * @param cost cost of child board
     */
    public Board (int b[][], String id, int depth, int cost){
        
        this.board = copyBoard(b);
        this.id = id;
        this.h = depth;
        this.g = cost;
        this.f = this.h + this.g;
    }

    /**
     * Returns String representation of this board.
     * @return String representation of this board
     */
    @Override
    public String toString() {
        return "Board{" + "h = " + h + ", g = " + g + ", f = " + f + ", id = " + id + "}";
    }
    
    /**
     * Returns this board's 4 by 4 board.
     * @return this board's 4 by 4 board
     */
    public int[][] getBoard (){       
        return this.board;        
    }
    
    /**
     * Returns this board's evaluation function.
     * @return this board's evaluation function
     */
    public int getF (){        
        return this.f;
    } 
    
    /**
     * Returns this board's cost.
     * @return this board's cost
     */
    public int getG (){
        return this.g;
    }
    
    /**
     * Returns this board's depth.
     * @return this board's depth
     */
    public int getH () {
        return this.h;
    }
    
    /**
     * Displays this board's 4 by 4 board.
     */
    public void displayBoard (){
        
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + "\t");
            }

            System.out.print("\n");
        }
    }
    
    /**
     * Returns this board's hexidecimal id.
     * @param b this board's 4 by 4 board
     * @return this board's hexidecimal id
     */
    public static String getId (int b[][]){
        
        String id = new String();
        String binaryVal = new String();
        
        /* Loop through rows */
        for (int i = 0; i < b.length; i++){
        	/* Loop through columns */
            for (int j = 0; j < b[i].length; j++){
                /* If current element is a queen */
                if (b[i][j] == 1){
                	/* Convert its index number to binary 
                	 * and process next row */
                	binaryVal = convertToBinary(j);
                    break;
                }
                
            }
            
            /* Concatenate binary values */
            id = id.concat(binaryVal);
        }
        
        return id;
        
    }
    
    /**
     * Converts indices of queen elements in 4 by 4 board of this board to binary values.
     * @param index index of queen element
     * @return binary value of index
     */
    private static String convertToBinary (int index){
        
        String bin = new String();
        
        switch (index){
            
            case 0:
                bin = "8";
                break;
            case 1:
                bin = "4";
                break;
            case 2:
                bin = "2";
                break;
            case 3:
                bin = "1";
                break;
        }
        
        return bin;
    }
    
    /**
     * Creates of copy of the specified 4 by 4 board.
     * @param b 4 by 4 board
     * @return copy of specified 4 by 4 board
     */
    public static int[][] copyBoard (int b[][]){
        
        int tempBoard [][] = new int [4][4];
        
        for (int i = 0; i < b.length; i++) {
            System.arraycopy(b[i], 0, tempBoard[i], 0, b[0].length);
        }
        
        return tempBoard;
    }
    
    /**
     * Computes the cost (number of conflicts) of the specified 4 by 4 board.
     * @param b 4 by 4 board
     * @return cost of the 4 by 4 board
     */
    public static int evaluateBoard (int b[][]){
        
        int cost = 0;    
        
        for (int j = 0; j < b.length; j++){
                        
            cost = cost + checkColumn(b, j);
        
        }
        
        cost = cost + checkDiagonal(b);
        
        return cost;
    }
    
    /**
     * Determines if any conflicts exist in the specified column of the specified 4 by 4 board.
     * @param b 4 by 4 board
     * @param j column
     * @return number of conflicts
     */
    private static int checkColumn (int b[][], int j){
        
        int cost = 0;
        int numQueens = 0;
        
        for (int i = 0; i < b.length; i++){
            
            if (b[i][j] == 1)
            	numQueens++;
            
        }
        
        /* If there are four queens in a column, the cost is three.
         * If there are three queens in a column, the cost is two.
         * If there are two queens in a column, the cost is one.
         */
        if (numQueens == 0)
            cost = 0;
        else 
            cost = numQueens - 1;
        
        return (cost);
    }
    
    /**
     * Determines if any diagonal conflicts exist in the the specified 4 by 4 board.
     * @param b 4 by 4 board
     * @return number of conflicts
     */
    private static int checkDiagonal (int b[][]){
    
    	int cost = 0;
    	
    	/*
    	 * Queens adjacent diagonally going downward to the right
    	 */
    	for (int i = 0; i < b.length - 1; i++){
    		for (int j = 0; j < b[i].length - 1; j++){
    			if (b[i][j] == 1)
    				if (b[i][j] == b[i+1][j+1])
    					cost++;
    		}  		
    	}
    	
    	/*
    	 * Queens adjacent diagonally going downward to the left
    	 */
    	for (int i = 0; i < b.length - 1; i++){
    		for (int j = b[i].length - 1; j > 0; j--){
    			if (b[i][j] == 1)
    				if (b[i][j] == b[i+1][j-1])
    					cost++;
    		}  		
    	}
    	
    	/*
    	 * Queens adjacent diagonally going downward to the right
    	 * with one intervening non-Queen
    	 */
    	if (b[0][0] == 1 && b[1][1] == 0 && b[2][2] == 1)
    		cost++;
    	
    	if (b[0][1] == 1 && b[1][2] == 0 && b[2][3] == 1)
    		cost++;
    	
    	if (b[1][0] == 1 && b[2][1] == 0 && b[3][2] == 1)
    		cost++;
    	
    	if (b[1][1] == 1 && b[2][2] == 0 && b[3][3] == 1)
    		cost++;
    	
    	/*
    	 * Queens adjacent diagonally going downward to the left
    	 * with one intervening non-Queen
    	 */
    	if (b[0][3] == 1 && b[1][2] == 0 && b[2][1] == 1)
    		cost++;
    	
    	if (b[0][2] == 1 && b[1][1] == 0 && b[2][0] == 1)
    		cost++;
    	
    	if (b[1][3] == 1 && b[2][2] == 0 && b[3][1] == 1)
    		cost++;
    	
    	if (b[1][2] == 1 && b[2][1] == 0 && b[3][0] == 1)
    		cost++;
    	
    	/*
    	 * Queens in upper left and lower right corners
    	 */
    	if (b[0][0] == 1 && b[1][1] == 0 && b[2][2] == 0 && b[3][3] == 1)
    		cost++;
    	
    	/*
    	 * Queens in lower left and upper right corners
    	 */
    	if (b[0][3] == 1 && b[1][2] == 0 && b[2][1] == 0 && b[3][0] == 1)
    		cost++;
    	
    	return cost;
    }  
    
    /**
     * Determines if the specified object is equal to this board.
     * @return true if the specified object is equal to this board, else false.
     */
    @Override
    public boolean equals(Object o){
    	
    	if (o == null) {
            return false;
        }
    	
    	if (getClass() != o.getClass()) {
            return false;
        }
    	
    	final Board other = (Board) o;
    	
    	if (!this.id.equals(other.id)) {
            return false;
        }
	
    	return true;
    }
}