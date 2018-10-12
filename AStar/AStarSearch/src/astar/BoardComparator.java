package astar;

import java.util.Comparator;

public class BoardComparator implements Comparator<Board>{

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