
import java.util.*;

public abstract class State
{
    State parent;
    public State(State parent)
    {
        this.parent=parent;
    }
    public State()
    {
        parent=null;
    }

    public String traverseFullList()
    {
        return ((parent!=null)?parent.traverseFullList():"") + this.toString();
    }

    /** returns a list of available actions determined by the state */
    public abstract Action[] getAvailableActions();
    public abstract boolean isGoal();
    public abstract boolean canActOn();
}


