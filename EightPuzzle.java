
import java.util.*;

public class EightPuzzle extends Driver
{
    public EightPuzzle(State initial)
    {
        super(initial);
        store = new SearchFrontierQueue();
    }

    public static void main(String[] args)
    {
        EightPuzzle p = new EightPuzzle(new EightPuzzleState(args[0]));
        p.search();
    }
}


