package bfs;
import java.util.Comparator;

/**
 * The BoardComparator class compares board objects.
 */
public class BoardComparator implements Comparator<Board>{

	/**
	 * Compares the specified board objects based on their 
	 * evaluation function.
	 * @param arg0 first board object to be compared
	 * @param arg1 second board object to be compared
	 * @return negative one, zero, or one as the first argument 
	 * is less than, equal to, or greater than the second.
	 */
	@Override
	public int compare(Board arg0, Board arg1) {
		if (arg0.getF() < arg1.getF())
        {
            return -1;
        }
        if (arg0.getF() > arg1.getF())
        {
            return 1;
        }
        return 0;
	}
}