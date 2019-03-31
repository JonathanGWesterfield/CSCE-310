import java.util.ArrayList;
import java.util.Collection;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class GameDAOImpl implements GameDAO.GameDao
{

	//list is working as a database

	private MongoDatabase database = null;
	private DBManager mDBmgr = null;
	private MongoCollection<Document> coll = null;
	ArrayList<DBGame> games = null;

	public GameDAOImpl(DBManager mgr, MongoCollection<Document> coll)
	{
		mDBmgr = mgr;
		this.coll = coll;
		if(coll != null)
			System.out.println("Select plays Collection Successful"); //should throw an exception
		else
			System.out.println("plays collection NOT found!");
		games = null;	// load games from real database as needed
	}


	@Override
	public Collection<DBGame> findGames(Integer gameID)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("game_id", gameID);
		FindIterable<Document> docs = coll.find(query);
		if(docs != null)
		{
			games = new ArrayList<DBGame>();
			for (Document d : docs)
				games.add(new DBGame(gameID, d)); // complete the mapping of a document to POJO

			return games;
		}
		return null;
	}

}