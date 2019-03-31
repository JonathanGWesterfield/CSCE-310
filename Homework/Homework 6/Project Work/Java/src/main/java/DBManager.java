import java.awt.List;
import java.util.Collection;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;

public class DBManager
{
	private final String dbName = "playsDB";
	private final String collName = "plays";
	private MongoClient client;

	// Constructor creates connection to the DB
	public DBManager()
	{
		try
		{
			client = MongoClients.create(
					MongoClientSettings.builder()
							.applyToClusterSettings(builder ->
									builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
							.build()
			)/*new MongoClient( "localhost" , 27017 )*/;
			MongoCollection<Document> coll = null;
			MongoDatabase database = client.getDatabase(dbName);
			if (database != null)
			{
				System.out.println("Connect to Database Successful");
				coll = database.getCollection(collName);
				if (coll != null)
					System.out.println("Select Collection Successful");
				else
				{
					System.out.println("Collection NOT found!");
					System.exit(-1);
				}
			}
			else
			{
				System.out.println("Database NOT found!");
				System.exit(-1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (client != null)
				client.close();
		}
	}


}
