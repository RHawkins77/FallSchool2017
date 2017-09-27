

import java.util.*;

public class UpMove implements Action<EightPuzzleState>
{
    /** returns a new state based on the given action */
    public EightPuzzleState updateState(EightPuzzleState s)
    {
        return new EightPuzzleState(s.board.substring(0,s.blank) + s.board.charAt(s.blank+3) + s.board.substring(s.blank+1,s.blank+3) + ' ' + s.board.substring(s.blank+4),s);
    }
    public String toString() { return "UpMove"; }
}


