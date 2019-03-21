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

public class Problem2A
{
    //@SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder ->
                                    builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                            .build()
            )/*new MongoClient( "localhost" , 27017 )*/;
            MongoCollection<Document> coll = null;
            MongoDatabase database = mongoClient.getDatabase("csce310");
            if (database != null)
            {
                System.out.println("Connect to Database Successful");
                coll = database.getCollection("games");
                if (coll != null)
                    System.out.println("Select Collection Successful");
                else
                    System.out.println("Collection NOT found!");
            }
            else
                System.out.println("Database NOT found!");

            /**
             * db.games.aggregate([{$match:{player_id:4847}},{$group:{_id:"$year",count:{$sum:1}}}])
             */

            coll.aggregate(
                   Arrays.asList(
                           Aggregates.match(Filters.eq("player_id", 4847)),
                           Aggregates.group("$year", Accumulators.sum("count", 1))
                   )
           ).forEach(printBlock);

            /*Document myDoc = coll.find().first(); *//** ITS THIS LINE *//*
            if (myDoc != null)
                System.out.println(myDoc.toJson());
            else System.out.println("First document NOT found");*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null)
                mongoClient.close();
        }
    }
}


