package astar;

import java.util.Random;

/**
 * The Board class creates the board objects that get
 * placed in the OPEN and CLOSED queues by the a-star-search 
 * algorithm.  Each board object has a 3 by 3 board,
 * an id, cost, depth, blank space, and heuristic function.  
 * This class has accessor methods that return the board, id, 
 * cost, blank space, and depth.  
 */
public class Board {

	/**
	 * Represents the 3 by 3 board.
	 */
	int board[][] = new int[3][3];
	/**
	 * Represents the heuristic (misplaced tiles).
	 */
	int h;
	/**
	 * Represents the cost (depth).
	 */
	int g;
	/**
	 * Represents the evaluation function (g + h).
	 */
	int f;
	/**
	 * Represents the blank space on the 3 by 3 board.
	 */
	int blank;
	/**
	 * Represents the id of the 3 by 3 board.
	 */
	String id = new String();

	/**
     * Initializes root board object with a solvable
     * 3 by 3 board.
     */
	public Board() {

		Random r = new Random();

		int num;
		
		boolean solvable = false;

		while (!solvable) {

			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board[i].length; j++) {
					this.board[i][j] = 9;
				}
			}

			/* Generate random numbers 0 through 8 and place them
			 * in the board making sure that there are no duplicate
			 * numbers.
			 */
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board[i].length; j++) {

					num = r.nextInt(9);

					while (searchBoard(num)) {

						num = r.nextInt(9);

					}

					this.board[i][j] = num;
				}
			}
			
			/* Check if the board is solvable */
			solvable = checkSolvable(this.board);
		}

		//this.board = new int [][] {{1,2,3},{4,5,6},{7,0,8}};
		this.blank = findBlankSpace(this.board);
		this.g = 0;
		this.h = evaluateBoard(board);
		this.f = 2 * this.h + this.g;
		this.id = getId(this.board);
	}
	
	/**
	 * Checks if a 3 by 3 board is solvable.  Writes the
	 * 3 by 3 board in a linear fashion ignoring the blank element.
	 * Determines the number of inversions by counting the 
	 * elements that follow another element with a higher number.
	 * If the number of inversions is even, the 3 by 3 board is solvable.
	 * @param b 3 by 3 board of root Board object
	 * @return true if solvable, else false.
	 */
	private boolean checkSolvable (int b[][]) {
		
		int arr [] = new int[8];
		int k = 0;
		int inversions = 0;
		
		/* Convert 2 dimensional board to a 1 dimensional array */
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j] != 0) {
					arr[k] = b[i][j];
					k++;
				}
			}
		}
		
		/* Count the number of inversions */
		for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] > arr[i]){
                    inversions++;
                }
            }
        }

		/* If there are an even number of inversions, board is solvable */
        if(inversions % 2 == 0) {
            return true;
        } else {
            return false;
        }
	}

	/**
     * Initializes child board objects.
     * @param b 3 by 3 child board
     * @param id id of child board
     * @param depth depth of child board
     * @param heuristic heuristic of child board
     * @param blank blank space of child board
     */
	public Board(int b[][], String id, int depth, int heuristic, int blank) {

		this.board = copyBoard(b);
		this.id = id;
		this.g = depth;
		this.h = heuristic;
		this.f = 2 * this.h + this.g;
		this.blank = blank;
	}

	/**
     * Computes the heuristic (number of misplaced tiles) in a specified 3 by 3 board.
     * @param b 3 by 3 board
     * @return cost of the 3 by 3 board
     */
	public static int evaluateBoard(int b[][]) {

		int heuristic = 0;

		int tempBoard[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if ((b[i][j] != tempBoard[i][j]) && (b[i][j] != 0)) {
					heuristic++;
				}
			}
		}

		return heuristic;
	}

	/**
	 * Finds the location of the blank space in a specified 3 by 3 board.
	 * @param b 3 by 3 board
	 * @return blank space (a value 0 - 8)
	 */
	public static int findBlankSpace(int b[][]) {

		int blank = 0;

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j] == 0) {
					blank = getSpace(i, j);
					break;
				}

			}
		}

		return blank;

	}

	/**
	 * Converts specified array element indices to a board space.
	 * @param i index of row
	 * @param j index of column
	 * @return a value 0 - 8
	 */
	private static int getSpace(int i, int j) {

		int space = 0;

		switch (i) {

		case 0:

			switch (j) {

			case 0:
				space = 0;
				break;

			case 1:

				space = 1;
				break;

			case 2:

				space = 2;
				break;

			}
			break;

		case 1:

			switch (j) {

			case 0:
				space = 3;
				break;

			case 1:

				space = 4;
				break;

			case 2:

				space = 5;
				break;

			}
			break;

		case 2:

			switch (j) {

			case 0:
				space = 6;
				break;

			case 1:

				space = 7;
				break;

			case 2:

				space = 8;
				break;

			}
			break;
		}

		return space;
	}

	/**
	 * Converts a specified board space to array element indices.
	 * @param space specified board space (a value 0 - 8)
	 * @return index of row and index of column
	 */
	public static int[] getElement(int space) {

		int element[] = new int[2];

		switch (space) {

		case 0:
			element[0] = 0;
			element[1] = 0;
			break;
		case 1:
			element[0] = 0;
			element[1] = 1;
			break;
		case 2:
			element[0] = 0;
			element[1] = 2;
			break;
		case 3:
			element[0] = 1;
			element[1] = 0;
			break;
		case 4:
			element[0] = 1;
			element[1] = 1;
			break;
		case 5:
			element[0] = 1;
			element[1] = 2;
			break;
		case 6:
			element[0] = 2;
			element[1] = 0;
			break;
		case 7:
			element[0] = 2;
			element[1] = 1;
			break;
		case 8:
			element[0] = 2;
			element[1] = 2;
			break;
		}

		return element;
	}

	/**
	 * Searches this Board's actual 3 by 3 board for a specified number.
	 * @param num specified number (a value 0 - 8)
	 * @return true if the number is found, else false
	 */
	private boolean searchBoard(int num) {

		boolean foundIt = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (this.board[i][j] == num) {
					foundIt = true;
					break;
				}
			}
		}

		return foundIt;
	}

	/**
     * Creates of copy of a specified 3 by 3 board.
     * @param b 3 by 3 board
     * @return copy of specified 3 by 3 board
     */
	public static int[][] copyBoard(int b[][]) {

		int tempBoard[][] = new int[3][3];
		
		for (int i = 0; i < b.length; i++) {
            System.arraycopy(b[i], 0, tempBoard[i], 0, b[0].length);
        }

		return tempBoard;
	}

	/**
     * Returns String representation of this Board's 3 by 3 board.
     * @return 3 by 3 board
     */
	public String displayBoard() {

		String str = "";

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				str = str.concat(board[i][j] + "\t");
			}
			str = str.concat("\n");
		}
		return str;
	}

	/**
     * Returns String representation of this Board.
     * @return String representation of this Board
     */
	@Override
	public String toString() {
		return displayBoard() + "Board{" + "h = " + h + ", g = " + g + ", f = " + f + ", id = " + id + ", blank = "
				+ blank + "}";
	}

	/**
	 * Returns this Board's blank space.
	 * @return blank space
	 */
	public int getBlank() {
		return this.blank;
	}

	/**
	 * Returns this Board's actual 3 by 3 board.
	 * @return 3 by 3 boar
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Returns this Board's evaluation function (g + h).
	 * @return evaluation function
	 */
	public int getF() {
		return this.f;
	}

	/**
	 * Returns this Board's cost (depth).
	 * @return heuristic
	 */
	public int getG() {
		return this.g;
	}

	/**
	 * Returns this Board's heuristic (misplaced tiles).
	 * @return depth
	 */
	public int getH() {
		
		return this.h;
	}

	/** 
	 * Returns the id of a specified 3 by 3 board.
	 * @param b 3 by 3 board
	 * @return id
	 */
	public static String getId(int b[][]) {
		
		String id = new String();
		int num = 0;

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				num = b[i][j];
				id = id.concat(String.valueOf(num));
			}
		}

		return id;
	}

	/**
     * Determines if the specified object is equal to this Board.
     * @return true if the specified object is equal to this board, else false.
     */
	@Override
	public boolean equals(Object o) {

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