
public class EightPuzzleState extends State
{
    String board;
    int blank;
    static Action actionList[][] = 
    {
        { new UpMove(), new LeftMove() },
        { new UpMove(), new LeftMove(), new RightMove() },
        { new UpMove(), new RightMove() },
        { new UpMove(), new LeftMove(), new DownMove() },
        { new RightMove(), new UpMove(), new LeftMove(), new DownMove() },
        { new UpMove(), new RightMove(), new DownMove() },
        { new LeftMove(), new DownMove() },
        { new RightMove(), new LeftMove(), new DownMove() },
        { new RightMove(), new DownMove() }
    };

    public EightPuzzleState(String board, EightPuzzleState parent)
    {
        super(parent);
        this.board=board;
        blank = board.indexOf(' ');
    }

    public EightPuzzleState(String board)
    {
        super();
        this.board=board;
        blank = board.indexOf(' ');
    }

    public Action[] getAvailableActions()
    {
       return actionList[blank];
    }
    public boolean isGoal()
    {
        return "12345678 ".equals(board);
    }
    public boolean canActOn()
    {
        return true;
    }

    public String toString()
    {
        return board.substring(0,3) + "\n" + board.substring(3,6) + "\n" + board.substring(6) + "\n\n";
    }

    public int hashCode()
    {
        return board.hashCode();
    }

    public boolean equals(Object s)
    {
        if(!(s instanceof EightPuzzleState)) return false;
        return board.equals(((EightPuzzleState)(s)).board);
    }


}

