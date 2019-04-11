import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

import static com.mongodb.client.model.Updates.*;


public class PullAndInsert
{
	//@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception
	{
		Block<Document> printBlock = new Block<Document>()
		{
			@Override
			public void apply(final Document document)
			{
				System.out.println(document.toJson());
			}
		};

		MongoClient mongoClient = null;
		try
		{
			Map<Integer, String> IDName = new HashMap<Integer, String>();

			mongoClient = MongoClients.create(); /*new MongoClient( "localhost" , 27017 )*/
			MongoCollection<Document> profilesColl = null;
			MongoCollection<Document> gamesColl = null;
			MongoDatabase database = mongoClient.getDatabase("csce310");
			if (database != null)
			{
				System.out.println("Connect to Database Successful");
				profilesColl = database.getCollection("profiles");
				gamesColl = database.getCollection("games");
				if (profilesColl != null && gamesColl !=null)
					System.out.println("Select Collections Successful");
				else
					System.out.println("Collections NOT found!");
			}
			else
				System.out.println("Database NOT found!");


			/** Get all of the players names and their ID's */
			// BasicDBObject query = new BasicDBObject();

			FindIterable<Document> docs = profilesColl.find();

			if(docs != null)
			{
				for (Document d : docs)
				{
					int id = d.getInteger("player_id");
					String name = d.getString("name");

					// Fill our Hashmap with id's and names if the id isn't there already
					if (!IDName.containsKey(id))
						IDName.put(id, name);
				}
			}
			System.out.println("Done pulling info");

			/** Now insert these ID name mappings that we just found */

			for (Map.Entry<Integer, String> entry : IDName.entrySet())
			{
				Integer playerID = entry.getKey();
				String playerName = entry.getValue();

				gamesColl.updateMany(
					Filters.eq("player_id", playerID),
					set("player_name", playerName)
				);

				System.out.printf("Size: %d\t%d\t%s\n", IDName.size(), playerID, playerName);
			}

			System.out.println("Finised Updating!");

			/*Document myDoc = coll.find().first(); *//** ITS THIS LINE *//*
            if (myDoc != null)
                System.out.println(myDoc.toJson());
            else System.out.println("First document NOT found");*/

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (mongoClient != null)
				mongoClient.close();
		}
	}
}
