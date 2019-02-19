import java.util.*;

public class FootBallGame
{
    private int numQuarters;
    private String gameID;
    private boolean tie;
    private int numHomeScore;
    private int numAwayScore;
    private boolean overTime;

    public FootBallGame(String gameID, int numHomeScore, int numAwayScore, boolean tie, boolean overTime)
    {
        this.gameID = gameID;
        this.numQuarters = 4;
        this.numHomeScore = numHomeScore;
        this.numAwayScore = numAwayScore;
        this.tie = tie;
        this.overTime = overTime;
    }

    public String getGameID()
    {
        return this.gameID;
    }

    public int getNumQuarters()
    {
        return numQuarters;
    }

    public int getNumHomeScore()
    {
        return numHomeScore;
    }

    public int getNumAwayScore()
    {
        return numAwayScore;
    }

    public boolean getIfOvertime()
    {
        return this.overTime;
    }

    public boolean getWasTie()
    {
        return this.tie;
    }
}
