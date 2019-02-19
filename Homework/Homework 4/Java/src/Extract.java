import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Extract
{
    private String filePath;
    private ArrayList<FootBallGame> gameList;
    private int lineCount;
    private int homeScore;
    private int numHomeScore;
    private int awayScore;
    private int numAwayScore;
    private boolean overtime;
    private String drive;
    private String teamID;

    public static void main(String[] args)
    {
        System.out.println("Starting Program!");

        String filePath = "Datasets/NFL Play by Play 2009-2018 (v5).csv";
        // String filePath = "Datasets/sampleData.csv";
        // String filePath = "Datasets/sampleData1.csv";

        Extract fileExtract = new Extract(filePath);

    }

    public Extract(String filePath)
    {
        this.gameList = new ArrayList<FootBallGame>();
        this.filePath = filePath;
        this.lineCount = 0;
        this.homeScore = 0;
        this.numHomeScore = 0;
        this.awayScore = 0;
        this.numAwayScore = 0;
        this.overtime = false;
        this.drive = "Half1";
        this.teamID = "";

        readFile(this.filePath);
        // showFile(this.filePath);

        // showGameInfo();
        showFinalStats();

    }

    public void showFinalStats()
    {
        int[] variousStats = calcVariousStats();
        DecimalFormat df = new DecimalFormat("##.###");

        System.out.println();
        System.out.println("Number of Quarters in the Data set: " + variousStats[4]);
        System.out.println("Number of Overtime periods in the Data set: " + variousStats[1]);
        System.out.println("Number of times the game ended in a Tie (is the same as overtime games): " + variousStats[1]);
        System.out.println("Number of times the Home team scored: " + variousStats[2]);
        System.out.println("Number of time the Away team scored: " + variousStats[3]);
        System.out.println("Probability of a game ending in overtime: " + df.format(calcOvertimeProb()) + "%");
        System.out.println("Likelihood (ratio) of the home team scoring vs the away team scoring: " + df.format(calcHomeAwayScoreRatio()));
        System.out.println("Total Number of games in data set: " + this.gameList.size());
    }

    /**
     * This function does multiple things to avoid looping through the massive dataset multiple times
     * and increasing the runtime
     *
     * @return {number of tied games in the dataset, number of overtimes in the dataset,
     * total times the home team scored, total times the away team scored, number of quarters in the game}
     */
    public int[] calcVariousStats()
    {
        int numTied = 0;
        int numOvertimes = 0;
        int totalHomeScore = 0;
        int totalAwayScore = 0;

        int numQuarters = 4 * this.gameList.size();
        for(FootBallGame obj : this.gameList)
        {
            if (obj.getIfOvertime() == true)
                numOvertimes++;
            totalHomeScore += obj.getNumHomeScore();
            totalAwayScore += obj.getNumAwayScore();
        }

        int[] variousInfo = {numTied, numOvertimes, totalHomeScore, totalAwayScore, numQuarters};
        return variousInfo;
    }

    public double calcOvertimeProb()
    {
        int numOvertimes = 0;
        for(FootBallGame obj : this.gameList)
            if (obj.getIfOvertime() == true)
                numOvertimes++;

        return 100 * ((double)numOvertimes / (double)this.gameList.size());
    }

    /**
     * Calculates the likelihood of the home team scoring vs the away team
     * @return
     */
    public double calcHomeAwayScoreRatio()
    {
        int totalNumAway = 0;
        int totalNumHome = 0;
        for (FootBallGame obj : this.gameList)
        {
            totalNumAway += obj.getNumAwayScore();
            totalNumHome += obj.getNumHomeScore();
        }

        return (double)totalNumHome / (double)totalNumAway;
    }

    public void showGameInfo()
    {

        for(FootBallGame obj : this.gameList)
        {
            System.out.println("Game ID: " + obj.getGameID());
            System.out.println("# of times Home Scored: " + obj.getNumHomeScore());
            System.out.println("# of times Away Scored: " + obj.getNumAwayScore());
            System.out.println("Overtime? " + obj.getIfOvertime());
            System.out.println("Game Tied:? " + obj.getWasTie());
            System.out.println();
        }
        System.out.println();
    }

    public void initialize(String[] initialLine)
    {
        //0 away_team, 1 drive, 2 posteam_score, 3 defteam_scor
        this.teamID = initialLine[0];
        this.drive = initialLine[1];
        this.homeScore = Integer.parseInt(initialLine[2]);
        this.awayScore = Integer.parseInt(initialLine[3]);
        this.lineCount++;

        /* System.out.println("Initial Values: ");
        System.out.println("Team ID: " + this.teamID);
        System.out.println("Drive: " + this.drive);
        System.out.println("Home Score: " + this.homeScore);
        System.out.println("Away Score: " + this.awayScore); */
    }

    public void readFile(String filePath)
    {
        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));
            String line;
            String[] condLine;

            // throw out the first line - it just has the column names
            bf.readLine();
            // We keep a line count to make sure we analyzed every line in the file
            lineCount++;

            // set the initial values of the class
            initialize(condenseLine(bf.readLine()));

            while ((line = bf.readLine()) != null)
            {
                condLine = condenseLine(line);

                // Get 2 consecutive events and compare the differences between them.
                // System.out.println("\nCond Line: ");
                // printTokens(condLine);
                // printClassStatus();

                compareLines(condLine);

                // make the new line old so it can be compared to the updated line
                lineCount++;
            }
        }
        catch (IOException e)
        {
            System.out.println("ERROR: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    /**
     * Will compare 2 consecutive lines. If the score changes in either column, check which
     * one changed and log it. If team number changes, new game started. If drive changes,
     * it means a new half or overtime started
     * @param line
     */
    public void compareLines(String[] line)
    {
        String tempTeam = line[0];
        String tempDrive = line[1];
        int tempHomeScore = Integer.parseInt(line[2]);
        int tempAwayScore = Integer.parseInt(line[3]);

        boolean tied = false;
        if(tempDrive.equalsIgnoreCase("Overtime"))
        {
            if (this.homeScore == this.awayScore)
                tied = true;
            this.overtime = true;
        }

        // if the team id changed, it means the game ended
        //0 away_team, 1 drive, 2 posteam_score, 3 defteam_scor
        if (!(tempTeam.equalsIgnoreCase(this.teamID)))
        {
            // System.out.println("\nChanged Games\n");
            gameList.add(new FootBallGame(this.teamID, this.numHomeScore, this.numAwayScore, tied, this.overtime));
            reset(tempTeam);
        }

        if(tempHomeScore != this.homeScore)
        {
            this.homeScore = tempHomeScore;
            this.numHomeScore++;
        }
        if(tempAwayScore != this.awayScore)
        {
            this.awayScore = tempAwayScore;
            this.numAwayScore++;
        }
    }

    public void reset(String teamID)
    {
        this.teamID = teamID;
        this.homeScore = 0;
        this.awayScore = 0;
        this.numHomeScore = 0;
        this.numAwayScore = 0;
        this.overtime = false;
    }

    /**
     * We only need about 4 out of the 200 columns in the dataset so we will get them.
     * The columns we care about are away_team, drive, posteam_score, defteam_score & score_differential
     *
     * 1 = Awayteam ID
     * 13 = half
     * 50 = home team score
     * 51 = away team score
     * 52 = score diff
     *
     * @param line
     * @return
     */
    public String[] condenseLine(String line)
    {
        // crazy regular expression thing I found on the internet to get rid of commas in the dataset
        // that appear when there is touchdown or any comma that is in quotes
        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        String[] returnTokens = {tokens[1], tokens[13], tokens[50], tokens[51]};
        // System.out.println("Number of tokens: " + tokens.length);
        // printTokens(returnTokens);

        return returnTokens;
    }

    public void printTokens(String[] tokens)
    {
        System.out.print(lineCount + ": ");
        for(int i = 0; i < tokens.length; i++)
            System.out.print(i + ": " + tokens[i] + "\\\\  ");
        System.out.println();
    }

    public void printClassStatus()
    {
        //0 away_team, 1 drive, 2 posteam_score, 3 defteam_scor
        System.out.println("\nStatus: ");
        System.out.printf("%s, %s, %d, %d\n", this.teamID, this.drive, this.homeScore, this.awayScore);
        System.out.println("\n");
    }

}
