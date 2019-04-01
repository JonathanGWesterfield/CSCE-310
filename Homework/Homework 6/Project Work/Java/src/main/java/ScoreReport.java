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


	}

	public ScoreReport(Integer gameID)
	{
		this.homeScoreMap = new HashMap<>();
		this.awayScoreMap = new HashMap<>();
		this.dbMgr = new DBManager();
		this.queryGameID = gameID;

		getScoreInfo();

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
		for(DBGame gameEvent : gameEventList)
		{
			int quarter = gameEvent.getInteger("qtr");
			homeScoreMap.put(quarter, gameEvent.getInteger("total_home_score"));
			awayScoreMap.put(quarter, gameEvent.getInteger("total_away_score"));
		}
	}

	public void calcQuarterScores()
	{
		ArrayList homeQuarterScores = new ArrayList(), awayQuarterScores = new ArrayList();




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
