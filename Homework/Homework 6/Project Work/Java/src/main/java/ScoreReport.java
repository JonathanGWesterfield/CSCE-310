import java.util.*;
import java.io.*;

public class ScoreReport
{

	public static void main(String[] args)
	{
		System.out.println("Starting Report Generation");

		ScoreReport report = new ScoreReport();
		long cmt1, cmt2;
		DBManager dbMgr = new DBManager();

		// get the game ID to query for
		Integer queryID = Integer.parseInt(report.getUserGameID());

		// DBGame dbGame = dbMgr.findAllGames(queryID);



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
	public String getUserGameID()
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
