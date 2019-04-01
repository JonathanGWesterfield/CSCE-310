import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class FullGameReport
{

	/** Need Reports on: 2009019000, 2017112000, 2016101601, 2018092303 */
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

		DBManager dbMgr = new DBManager();
		Collection<DBGame> gameEventList = dbMgr.findAllGames(queryID);

		ScoreReport report = new ScoreReport(gameEventList);
		ScoreSummary summary = new ScoreSummary(gameEventList);

		// Print out the report
		System.out.println("\n\nGame Report For Game: " + queryID);
		report.printScoreReport();

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

}
