
import java.util.*;

public abstract class Driver {
	State initial;
	SearchFrontierStorage store;

	public Driver(State initial) {
		this.initial = initial;
	}

	public int search() {
		store.add(initial);
		State s;
		int numExpanded = 0;
		while (!store.isEmpty()) {
			s = store.next();
			numExpanded++;
			if (numExpanded % 100 == 0)
				System.out.print("\rNumExpanded = " + numExpanded);
			if (s.isGoal()) {
				System.out.println(s.traverseFullList());
				System.out.println("NumExpanded = " + numExpanded);
				return 0;
			} else if (s.canActOn()) {
				Action[] list = s.getAvailableActions();
				for (Action a : list) {
					store.add(a.updateState());
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

	}
}
