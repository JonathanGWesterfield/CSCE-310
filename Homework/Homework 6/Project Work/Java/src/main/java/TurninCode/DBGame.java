import java.io.Serializable;

import org.bson.Document;

//model class to represent a single document in a collection, or a row in a table
public class DBGame implements Serializable
{
	// use wrapper data type, since a DB field can be null (primitive types cannot be null
	private Integer gameID;
	Document doc;

	public DBGame(Integer id, Document d)
	{
		this.gameID = id;
		this.doc = d;
	}

	public int getGameID()
	{
		return gameID;
	}

	public String getString(String fieldName)
	{
		return doc.getString(fieldName);
	}

	public Integer getInteger(String fieldName)
	{
		return doc.getInteger(fieldName);
	}
}