package bfs;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Closed class creates the closed list
 * used by the best-first-search algorithm.
 * The closed list is an ArrayList of Integers.
 * Boards will be added to the closed list after
 * they have been dequeued.
 * @author susie
 *
 */
public class Closed {
	
	/**
	 * Represents the closed list.
	 */
	ArrayList<Board> reviewed = new ArrayList<Board>();
	
	/**
	 * Adds the specified board to the closed list.
	 * @param b specified board
	 */
	public void add(Board b){
	    	
		reviewed.add(b);
	    	
	}
	    
	/**
	 * Determines if the specified board is on the closed list.
	 * @param b specified board
	 * @return true if the specified board is on the closed list,
	 * else false
	 */
	public boolean isReviewed (Board b){
	    	
		Iterator<Board> itreviewed = reviewed.iterator();
	    	
	    while (itreviewed.hasNext()){
	    	if (itreviewed.next().equals(b)){
	    		return true;
	    	}
	    }
	    	
	    return false;
	    	
	}
}