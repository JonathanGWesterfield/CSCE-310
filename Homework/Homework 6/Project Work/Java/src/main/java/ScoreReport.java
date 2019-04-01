import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.io.*;

public class ScoreReport
{
	HashMap<Integer, Integer> homeScoreMap; // Map is <quarter, score for that quarter>
	HashMap<Integer, Integer> awayScoreMap; // Map is <quarter, score for that quarter>
	DBManager dbMgr;
	Integer queryGameID;
	String homeTeam, awayTeam;
	int numGameEvents;
	ArrayList<Integer> homeQuarterScores, awayQuarterScores;

	Collection<DBGame> gameEventList;

	public static void main(String[] args)
	{
		long cmt1, cmt2;

		System.out.println("Starting Report Generation");

		// make sure program is invoked correctly
		if (args.length != 1)
		{
			System.out.println("Usage: java ScoreReport <game id>");
			System.exit(-1);
		}

		// check to make sure the game id is actually a number
		if (!isNumeric(args[0]))
		{
			System.out.println("Game ID is must be a number!\nExiting!");
			System.exit(-1);
		}

		// get the game ID to query for
		Integer queryID = Integer.parseInt(args[0]);

		ScoreReport report = new ScoreReport(queryID);
		report.printScoreReport();


	}

	public ScoreReport(Integer gameID)
	{
		this.homeScoreMap = new HashMap<>();
		this.awayScoreMap = new HashMap<>();
		this.dbMgr = new DBManager();
		this.queryGameID = gameID;

		getScoreInfo();
		calcQuarterScores();

	}

	public void printScoreReport()
	{
		System.out.printf("QTR\t\t1\t2\t3\t4\tOT\tTotal\n");

		System.out.print(homeTeam + "\t");
		for(int score : this.homeQuarterScores)
			System.out.printf("\t%d", score);
		System.out.printf("\t%d", homeScoreMap.get(5));

		System.out.print("\n" + awayTeam + "\t");
		for(int score: this.awayQuarterScores)
			System.out.printf("\t%d", score);
		System.out.printf("\t%d\n\n", awayScoreMap.get(5));

	}

	public void getScoreInfo()
	{
		gameEventList = dbMgr.findAllGames(queryGameID);

		if (gameEventList == null)
		{
			System.out.println("THERE WERE NO EVENTS FOUND FOR THIS GAME ID");
			return;
		}

		homeTeam = gameEventList.iterator().next().getString("home_team");
		awayTeam = gameEventList.iterator().next().getString("away_team");
		numGameEvents = (int)gameEventList.size();

		// get every game event for the given game ID
		for (DBGame gameEvent : gameEventList)
		{
			int quarter = gameEvent.getInteger("qtr");
			homeScoreMap.put(quarter, gameEvent.getInteger("total_home_score"));
			awayScoreMap.put(quarter, gameEvent.getInteger("total_away_score"));
		}

		// if the game didn't go into overtime, just put the last score from the 4th quarter in
		if (!homeScoreMap.containsKey(5))
		{
			homeScoreMap.put(5, homeScoreMap.get(4));
			awayScoreMap.put(5, awayScoreMap.get(4));
		}

		return;
	}

	public void calcQuarterScores()
	{
		homeQuarterScores = new ArrayList<>();
		awayQuarterScores = new ArrayList<>();

		// this is bad and I feel bad but it works so ignore the dumpster fire
		for (int i = 1; i <= 5; i++)
		{
			if (i > 1)
			{
				int homeScoreDiff = homeScoreMap.get(i) - homeScoreMap.get(i - 1);
				homeQuarterScores.add(homeScoreDiff);

				int awayScoreDiff = awayScoreMap.get(i) - awayScoreMap.get(i - 1);
				awayQuarterScores.add(awayScoreDiff);
			}
			else
			{
				homeQuarterScores.add(homeScoreMap.get(i));
				awayQuarterScores.add(awayScoreMap.get(i));
			}
		}

		return;
	}

	/**
	 * Check if a string is numeric. Helper for getUserGameID to make sure
	 * user is actaully puttin in a game ID
	 * @param strNum
	 * @return
	 */
	public static boolean isNumeric(String strNum)
	{
		try
		{
			double d = Double.parseDouble(strNum);
		}
		catch (NumberFormatException | NullPointerException nfe)
		{
			return false;
		}
		return true;
	}

	/**
	 * Get the game ID from the user and make sure it is actually a number.
	 * If it's not a number, ask again.
	 * @return
	 */
	public static String getUserGameID()
	{
		boolean stop = false;
		String gameID = "";
		do
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Enter a game ID to get a report for: ");
			gameID = keyboard.next();

			if (isNumeric(gameID))
				stop = true;
		} while (!stop);

		return gameID;
	}


}
