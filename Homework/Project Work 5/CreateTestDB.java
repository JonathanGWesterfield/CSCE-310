
import java.util.Arrays;

import org.bson.Document;

//http://mongodb.github.io/mongo-java-driver/3.6/driver/getting-started/quick-start/
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CreateTestDB {

	public static void main(String args[]) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost");// 27017
			MongoCollection<Document> coll = null;
			MongoDatabase db = mongoClient.getDatabase("testdb");
			System.out.println("Database Connection Successful!");
			MongoCollection<Document> collec = db.getCollection("dbcollec");
			Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
					.append("versions", Arrays.asList("v4.0", "v3.6", "v2.6"))
					.append("info", new Document("x", 203).append("y", 102));
			collec.insertOne(doc);
			System.out.println("Document is inserted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			if (mongoClient != null)
				mongoClient.close();
		}

	}
}
/**
 * db.profiles.find({name:"Johnny Manziel"},{player_id:1, name:1,
 * _id:0}).pretty()
 * 
 * db.games.find({player_id:2240}).count()
 * 
 * db.games.aggregate([{$match:{player_id:3950}},{"$group" : {_id:"$player_id",
 * count:{$sum:1}}}])
 * 
 * db.games.aggregate([{$match:{player_id:2648}},{"$group" :
 * {_id:"$player_id",BrownpTDs:{$sum:"$receiving_touchdowns"
 * },BrownrTDs:{$sum:"$rushing_touchdowns" }}},{"$project" : {_id:"$player_id",
 * BrownTDs:{$sum:["$BrownpTDs","$BrownrTDs"] }}}])
 * 
 * db.games.aggregate([{$match:{player_id:4847}},{$group:{_id:"$year",count:{$sum:1}}}])
 * 
 * db.games.aggregate([{$match:{year:"1990", team: "SEA",
 * defense_interceptions:{$ne:0}}}, {"$project":{_id:0, player:"$player_id",
 * gameNum:"$game_number"}}])
 */
