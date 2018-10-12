package ids;

/**
 * The Matrix class creates the adjacency matrix
 * used by the iterative-deepening-search algorithm.
 * The matrix is a two-dimensional array of integers.  
 * Nodes in the matrix that are adjacent have a value
 * of the depth of the node specified by the column.  
 * Nodes in the matrix that are not adjacent have a
 * value of 0.
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
     * @param depth depth of node specified as the column
     */
    public void connectNodes (int row, int column, int depth){
        
        matrix[row][column] = depth;
        
    }
    
    /**
     * Gets the node depth for the node specified as the column.
     * @param row node specified as the row
     * @param column node specified as the column
     * @return depth of node specified as the column
     */
    public int getNodeDepth (int row, int column) {
    	return matrix[row][column];
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
        
        return (matrix[row][column] != 0);
        
    }
    
    public void resetNodes (){
    	
    	for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = 0;
            }
        }
    }
}