import java.util.ArrayList;
import java.util.Collection;

import com.mongodb.Block;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;

public class GameDAOImpl implements GameDao
{

	//list is working as a database

	private MongoDatabase db = null;
	private DBManager mDBmgr = null;
	private MongoCollection<Document> coll = null;
	ArrayList<DBGame> games = null;

	public GameDAOImpl(MongoClient client, MongoDatabase db)
	{
		this.db = db;
		this.coll = this.db.getCollection("plays");
		if (coll != null)
			System.out.println("Select Collection Successful");
		else
		{
			System.out.println("Collection NOT found!");
			System.exit(-1);
		}
		games = null;	// load games from real database as needed
	}


	public Collection<DBGame> findGames(Integer gameID)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("game_id", gameID);

		coll.createIndex(Indexes.text("desc"));

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

	Block<Document> printBlock = new Block<Document>() {
		@Override
		public void apply(final Document document) {
			System.out.println(document.toJson());
		}
	};

}