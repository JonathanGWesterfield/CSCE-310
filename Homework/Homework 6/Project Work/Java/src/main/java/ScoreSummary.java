import java.util.*;

public class ScoreSummary
{
	private Collection<DBGame> gameEventList;
	private ArrayList<String> eventDescriptionList;

	public ScoreSummary() { /* Default constructor */ }

	public ScoreSummary(Collection<DBGame> gameEventList)
	{
		this.gameEventList = gameEventList;
		this.eventDescriptionList = new ArrayList<>();

		pullDescriptions();
		removeIncompletesAndTimeouts();
		removeEverythingBut();
		removeDeclinedPenalty();
		replaceShotgun();
		replaceNoHuddle();
		replaceParenthesis();
		replaceHolderCenter();

		/* for(String desc : eventDescriptionList)
			System.out.println(desc); */
	}

	public void pullDescriptions()
	{
		// get every game event for the given game ID
		for (DBGame gameEvent : gameEventList)
			eventDescriptionList.add(gameEvent.getString("desc"));
		return;
	}

	/**
	 * 	Get rid of all parenthetical information
	 */
	public void replaceParenthesis()
	{
		for(int i = 0; i < eventDescriptionList.size(); i++)
		{
			String replace = eventDescriptionList.get(i).replaceAll("[(].*?[)]", "YEETUS");
			replace = replace.replaceAll("YEETUS ", "");
			replace = replace.replaceAll("YEETUS\\.", "");

			if (replace != null && replace.length() > 0 && replace.charAt(replace.length() - 1) == ' ')
				replace = replace.substring(0, replace.length() - 1);

			if (replace != null && replace.length() > 0 && replace.charAt(replace.length() - 1) == '.')
				replace = replace.substring(0, replace.length() - 1);

			replace += ".";

			replace = replace.replaceAll(" YEETUS", "");
			eventDescriptionList.set(i, replace);
		}
	}

	public void replaceShotgun()
	{
		for(int i = 0; i < eventDescriptionList.size(); i++)
		{
			String replace = eventDescriptionList.get(i).replace("(Shotgun)", "From the shotgun,");
			eventDescriptionList.set(i, replace);

			replace = eventDescriptionList.get(i).replace(" Shotgun)", "From the shotgun,");
			eventDescriptionList.set(i, replace);
		}
	}

	public void replaceNoHuddle()
	{
		for(int i = 0; i < eventDescriptionList.size(); i++)
		{
			String replace = eventDescriptionList.get(i).replace("(No Huddle,", "");
			eventDescriptionList.set(i, replace);
		}
	}

	/**
	 * USE THIS METHOD TO DO ALL FURTHER DELETIONS!!!
	 */
	public void removeIncompletesAndTimeouts()
	{
		ArrayList<String> popStrings = new ArrayList<>();
		ArrayList<String> tempList = eventDescriptionList;
		for(String desc : eventDescriptionList)
		{
			if (desc.toLowerCase().contains("incomplete") || desc.toLowerCase().contains("timeout"))
				popStrings.add(desc);
		}

		tempList.removeAll(popStrings);

		this.eventDescriptionList = tempList;
	}

	/**
	 * Removes everything but desc's with "GOOD", "TOUCHDOWN", and "field"
	 */
	public void removeEverythingBut()
	{
		ArrayList<String> popStrings = new ArrayList<>();
		ArrayList<String> tempList = eventDescriptionList;
		for(String desc : eventDescriptionList)
		{
			if (!desc.toLowerCase().contains("good") && !desc.toLowerCase().contains("touchdown")
					&& !desc.toLowerCase().contains("field"))
						popStrings.add(desc);
		}

		tempList.removeAll(popStrings);

		this.eventDescriptionList = tempList;
	}

	/**
	 * Remove the desc if the score is declined due to a penalty
	 */
	public void removeDeclinedPenalty()
	{
		ArrayList<String> popStrings = new ArrayList<>();
		ArrayList<String> tempList = eventDescriptionList;
		for(String desc : eventDescriptionList)
		{
			if (desc.toLowerCase().contains("declined") || desc.toUpperCase().contains("PENALTY"))
				popStrings.add(desc);
		}

		tempList.removeAll(popStrings);

		this.eventDescriptionList = tempList;
	}

	/**
	 * If there is a field goal, replace "Center-" with "Center is".
	 * Same thing with "Holder-"
	 */
	public void replaceHolderCenter()
	{
		for(int i = 0; i < eventDescriptionList.size(); i++)
		{
			String replace = eventDescriptionList.get(i).replace(", Center-", ". Center player was ");
			eventDescriptionList.set(i, replace);

			replace = eventDescriptionList.get(i).replace(", Holder-", ". Holder player was ");
			eventDescriptionList.set(i, replace);
		}
	}

	/**
	 * Uses the toString() method to print the game report
	 */
	public void printSummary()
	{
		System.out.println(this.toString());
	}

	@Override
	public String toString()
	{
		String retStr = "";
		for (String str : eventDescriptionList)
			retStr += str + "\n";
		return retStr;
	}
}





