package astar;

import java.util.ArrayList;
import java.util.Iterator;

public class Closed {
	
	ArrayList<Board> closed = new ArrayList<Board>();
	
	public void add(Board b){
	    	
		closed.add(b);
	    	
	}
	    
	public boolean isClosed (Board b){
	    	
		Iterator<Board> itclosed = closed.iterator();
	    	
	    while (itclosed.hasNext()){
	    	if (((Board) itclosed.next()).equals(b)){
	    		return true;
	    	}
	    }
	    	
	    return false;
	    	
	}
}