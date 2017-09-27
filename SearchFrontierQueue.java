

import java.util.*;

public class SearchFrontierQueue extends SearchFrontierStorage
{
   LinkedList<State> q = new LinkedList<State>();
   public boolean isEmpty()
   {
       return q.isEmpty();
   }
   protected void addToStorage(State s)
   {
       q.offer(s);
   }
   public State next()
   {
       return q.poll();
   }
}
