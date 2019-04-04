import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;

public class DBManager
{
	private final String dbName = "playsDB";
	private final String collName = "plays";
	private static DBManager mongoDBmgr = null;
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Document> coll;
	private GameDAOImpl gmDao;
	private ArrayList games;
	private Collection<DBGame> gameEvents;

	// Constructor creates connection to the DB
	public DBManager()
	{
		try
		{
			/* this.client = MongoClients.create(
					MongoClientSettings.builder()
							.applyToClusterSettings(builder ->
									builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
							.build()
			) // /* new MongoClient( "localhost" , 27017 )*/;

			MongoClient client = MongoClients.create();
			this.database = client.getDatabase(dbName);

			if (database != null)
			{
				System.out.println("Connect to Database Successful");
				// printShit();
				gmDao = new GameDAOImpl(client, this.database);

			}
			else
			{
				System.out.println("Database NOT found!");
				System.exit(-1);
			}
		}
		catch (MongoException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (client != null)
				client.close();
		}
	}

	public void printShit()
	{
		MongoCollection<Document> coll = this.database.getCollection("plays");
		if(coll != null)
		{
			coll.find().forEach(printBlock);
		}
	}

	Block<Document> printBlock = new Block<Document>() {
		@Override
		public void apply(final Document document) {
			System.out.println(document.toJson());
		}
	};

	// A singleton design pattern
	public static DBManager getInstance()
	{
		if(mongoDBmgr == null)
			mongoDBmgr = new DBManager();

		return mongoDBmgr;
	}

	public MongoCollection<Document> getCollection()
	{
		return this.coll;
	}

	public MongoDatabase getDatabase()
	{
		return this.database;
	}

	public Collection<DBGame> findAllGames(Integer gameID)
	{
		return gmDao.findGames(gameID);
	}

}
