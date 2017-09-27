import java.util.*;
import junit.framework.TestCase;
public class Test extends TestCase {

    String[][] testCases = {
        {"12345678 ", "1234567 8", "12345 786"},
        {"1234567 8", "123456 78", "1234 6758", "12345678 "},
        {"123456 78", "123 56478", "1234567 8"},
        {"12345 678", "12 453678", "1234 5678", "12345867 "},
        {"1234 5678", "1 3425678", "123 45678", "12345 678", "1234756 8"},
        {"123 45678", " 23145678", "1234 5678", "123645 78"},
        {"12 345678", "1 2345678", "12534 678"},
        {"1 2345678", " 12345678", "1423 5678", "12 345678"},
        {" 12345678", "1 2345678", "312 45678"}
    };

    public void testStuff()
    {
        for(String[] testCase : testCases)
        {
            ArrayList<String> list = new ArrayList<String>();
            for(String str : testCase) list.add(str);
            list.remove(0);
            EightPuzzleState s = new EightPuzzleState(testCase[0]);
            for(Action a : s.getAvailableActions())
            {
                assertTrue("Case: " + testCase[0] + ", Action: " + a + " --> " + 
                    ((EightPuzzleState)(a.updateState(s))).board, 
                    list.remove(((EightPuzzleState)(a.updateState(s))).board));
            }
            assertTrue(list.isEmpty());
        }
    }
}
