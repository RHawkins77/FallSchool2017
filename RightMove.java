

import java.util.*;

public class RightMove implements Action<EightPuzzleState>
{
    /** returns a new state based on the given action */
    public EightPuzzleState updateState(EightPuzzleState s)
    {
        return new EightPuzzleState(s.board.substring(0,s.blank-1) + ' ' + s.board.charAt(s.blank-1) + s.board.substring(s.blank+1),s);
    }
    public String toString() { return "RightMove"; }
}


