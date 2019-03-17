
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.*;

public class ShowTestdb {

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		MongoClient mongoClient = null;
		try {
			mongoClient = MongoClients.create(
					MongoClientSettings.builder()
							.applyToClusterSettings(builder ->
									builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
							.build()
			)/*new MongoClient( "localhost" , 27017 )*/;
			MongoCollection<Document> coll = null;
			MongoDatabase database = mongoClient.getDatabase("testdb");
			if(database!=null) {
				
				System.out.println("Connect to Database Successful");
				coll = database.getCollection("dbcollec");
		        if(coll!=null)
		        	System.out.println("Select Collection Successful");
		        else System.out.println("Collection NOT found!");
			}
			else System.out.println("Database NOT found!");
			
 	    	Document myDoc = coll.find().first();
 	    	if(myDoc !=null)
 	    		System.out.println(myDoc.toJson());
 	    	else System.out.println("First document NOT found");
 	    		    	
		} catch (Exception e) {
			e.printStackTrace();
 		} finally {
 			if(mongoClient != null)
 				mongoClient.close();
 		}
	}
}
