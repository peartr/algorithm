package dfs;

/**
 * The Matrix class creates the adjacency matrix
 * used by the depth-first-search algorithm.
 * The matrix is a two-dimensional array of integers.  
 * Nodes in the matrix that are adjacent have a value
 * of 1.  Nodes in the matrix that are not adjacent
 * have a value of 0.
 * @author susie
 *
 */
public class Matrix {

	/**
	 * Represents the matrix.
	 */
	int [][] matrix;
    
    /**
     * Initializes a matrix having the specified number
     * of rows and columns.
     * @param rows specified rows
     * @param columns specified columns
     */
    public Matrix (int rows, int columns){
        
        matrix = new int [rows][columns];
     
    }
    
    /**
     * Creates a connectivity between the node specified as  
     * the row to the node specified as the column.
     * @param row node specified as the row
     * @param column node specified as the column
     */
    public void connectNodes (int row, int column){
        
        matrix[row][column] = 1;
        
    }
    
    public void displayMatrix (){
        
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.print("\n");
        }
        
    }
    
    /**
     * Determines if the node specified as the row is
     * connected to the node specified as the column.
     * @param row node specified as the row
     * @param column node specified as the column
     * @return true if the specified nodes are connected, 
     * else false
     */
    public boolean isNodeAdjacent (int row, int column){
        
        return (matrix[row][column] == 1);
        
    }
    
    public void resetNodes (){
    	
    	for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = 0;
            }
        }
    }
}