

import java.util.*;

public class DownMove implements Action<EightPuzzleState>
{
    /** returns a new state based on the given action */
    public EightPuzzleState updateState(EightPuzzleState s)
    {
        return new EightPuzzleState(s.board.substring(0,s.blank-3) + ' ' + s.board.substring(s.blank-2,s.blank) + s.board.charAt(s.blank-3) + s.board.substring(s.blank+1),s);
    }

    public String toString() { return "DownMove"; }
}


