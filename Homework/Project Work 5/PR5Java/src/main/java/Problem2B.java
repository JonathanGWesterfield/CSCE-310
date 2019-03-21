import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.Arrays;

public class Problem2B {
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
            if (database != null) {

                System.out.println("Connect to Database Successful");
                coll = database.getCollection("games");
                if (coll != null)
                    System.out.println("Select Collection Successful");
                else System.out.println("Collection NOT found!");
            } else System.out.println("Database NOT found!");

            /**
             db.games.aggregate([{$match:{year:"1990", team: "SEA", defense_interceptions:{$ne:0}}},
             {"$project":{_id:0, player:"$player_id", gameNum:"$game_number"}}])
             */

            coll.aggregate(Arrays.asList(
                    Aggregates.match(
                            Filters.and(
                                    Filters.ne("defense_interceptions", 0),
                                    Filters.eq("team", "SEA"),
                                    Filters.eq("year", "1990")
                            )
                    ),
                    Aggregates.project(
                            Projections.fields(
                                    Projections.include("player_id", "game_number"),
                                    Projections.excludeId()
                            )
                    )
                    )).forEach(printBlock);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null)
                mongoClient.close();
        }
    }
}

