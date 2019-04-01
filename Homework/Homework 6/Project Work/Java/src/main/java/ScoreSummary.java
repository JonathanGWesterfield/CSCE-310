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
		removeIncompletes();
		replaceShotgun();
		replaceNoHuddle();
		replaceParenthesis();

		for(String desc : eventDescriptionList)
			System.out.println(desc);
	}

	public void pullDescriptions()
	{
		// get every game event for the given game ID
		for (DBGame gameEvent : gameEventList)
			eventDescriptionList.add(gameEvent.getString("desc"));
		return;
	}

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
	public void removeIncompletes()
	{
		ArrayList<String> popStrings = new ArrayList<>();
		ArrayList<String> tempList = eventDescriptionList;
		for(String desc : eventDescriptionList)
		{
			if (desc.toLowerCase().contains("incomplete"))
				popStrings.add(desc);
		}

		tempList.removeAll(popStrings);

		this.eventDescriptionList = tempList;
	}
}

// TODO: Check if desc has "GOOD" or "TOUCHDOWN", and make sure to include field goals failed and good


