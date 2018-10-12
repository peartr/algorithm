package bfs;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Reviewed class creates the reviewed list
 * used by the breadth-first-search algorithm.
 * The reviewed list is an ArrayList of Integers.
 * Nodes will be added to the reviewed list after
 * they have been popped from the Stack.
 * @author susie
 *
 */
public class Reviewed {
	
	/**
	 * Represents the reviewed list.
	 */
	ArrayList<Integer> reviewed = new ArrayList<Integer>();
	
	/**
	 * Adds the specified node to the reviewed list.
	 * @param node specified node
	 */
	public void addNodeToReviewed(int node){
	    	
		reviewed.add(node);
	    	
	}
	    
	/**
	 * Determines if the specified node is on the reviewed list.
	 * @param node specified node
	 * @return true if the specified node is on the reviewed list,
	 * else false
	 */
	public boolean isNodeReviewed (int node){
	    	
		Iterator<Integer> itreviewed = reviewed.iterator();
	    	
	    while (itreviewed.hasNext()){
	    	if (itreviewed.next() == node){
	    		return true;
	    	}
	    }
	    	
	    return false;
	    	
	}
}