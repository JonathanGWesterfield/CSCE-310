import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.io.*;

/**
 * Author: Jonathan Westerfield Summary: This class takes the scoring
 * information for each quarter and formats it so that the score for each
 * quarter is shown. Is used in the FullGameReport class
 */
public class ScoreReport {
	HashMap<Integer, Integer> homeScoreMap; // Map is <quarter, score for that quarter>
	HashMap<Integer, Integer> awayScoreMap; // Map is <quarter, score for that quarter>
	DBManager dbMgr;
	Integer queryGameID;
	String homeTeam, awayTeam;
	int numGameEvents;
	ArrayList<Integer> homeQuarterScores, awayQuarterScores;

	Collection<DBGame> gameEventList;

	public ScoreReport(Collection<DBGame> gameEventList) {
		this.homeScoreMap = new HashMap<>();
		this.awayScoreMap = new HashMap<>();
		this.gameEventList = gameEventList;

		getScoreInfo();
		calcQuarterScores();
	}

	public void printScoreReport() {
		System.out.printf("QTR\t\t1\t2\t3\t4\tOT\tTotal\n");

		System.out.print(homeTeam + "\t");
		for (int score : this.homeQuarterScores)
			System.out.printf("\t%d", score);
		System.out.printf("\t%d", homeScoreMap.get(5));

		System.out.print("\n" + awayTeam + "\t");
		for (int score : this.awayQuarterScores)
			System.out.printf("\t%d", score);
		System.out.printf("\t%d\n\n", awayScoreMap.get(5));

	}

	public void getScoreInfo() {
		if (gameEventList == null) {
			System.out.println("THERE WERE NO EVENTS FOUND FOR THIS GAME ID");
			return;
		}

		homeTeam = gameEventList.iterator().next().getString("home_team");
		awayTeam = gameEventList.iterator().next().getString("away_team");
		numGameEvents = (int) gameEventList.size();

		// get every game event for the given game ID
		for (DBGame gameEvent : gameEventList) {
			int quarter = gameEvent.getInteger("qtr");
			homeScoreMap.put(quarter, gameEvent.getInteger("total_home_score"));
			awayScoreMap.put(quarter, gameEvent.getInteger("total_away_score"));
		}

		// if the game didn't go into overtime, just put the last score from the 4th
		// quarter in
		if (!homeScoreMap.containsKey(5)) {
			homeScoreMap.put(5, homeScoreMap.get(4));
			awayScoreMap.put(5, awayScoreMap.get(4));
		}

		return;
	}

	public void calcQuarterScores() {
		homeQuarterScores = new ArrayList<>();
		awayQuarterScores = new ArrayList<>();

		// this is bad and I feel bad but it works so ignore the dumpster fire
		for (int i = 1; i <= 5; i++) {
			if (i > 1) {
				int homeScoreDiff = homeScoreMap.get(i) - homeScoreMap.get(i - 1);
				homeQuarterScores.add(homeScoreDiff);

				int awayScoreDiff = awayScoreMap.get(i) - awayScoreMap.get(i - 1);
				awayQuarterScores.add(awayScoreDiff);
			} else {
				homeQuarterScores.add(homeScoreMap.get(i));
				awayQuarterScores.add(awayScoreMap.get(i));
			}
		}

		return;
	}
}
