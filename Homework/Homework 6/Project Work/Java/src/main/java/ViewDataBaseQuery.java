package ORMexample;

import ORMexample.model.DBprofile;

import java.util.HashMap;

import ORMexample.model.DBgame;

public class ViewDataBaseQuery {

	public static void main(String[] args) throws Exception {
		long ctm1, ctm2;
        ctm1 = java.lang.System.nanoTime();//get current timer value
		MongoDBmanager mDBmgr = new MongoDBmanager();
		
		// Notice our client is accessing the DAO objects thru the DB manager 
		// rather than coding JDBC or Mongo commands
        String name = "Tom Brady ";
        DBprofile pro = mDBmgr.findProfile(name); 
        Integer playerId = pro.getPlayerId();
        
        HashMap<String, Integer> yc = new HashMap<String, Integer>();    
        for(DBgame pg: mDBmgr.findAllGames(playerId)) {//get games played in
        	
			String year = pg.getString("year");
			Integer pa = pg.getInteger("passing_attempts");
			if(yc.containsKey(year)){
				yc.put(year, pa+yc.get(year)); //count games for each year
			}
			else yc.put(year,  pa);
        }
            	
		System.out.println("Year\tCount of Passing Attempts");
	    for (String year: yc.keySet()){
	    	Integer entry = yc.get(year);
	    	System.out.println(year+"\t"+entry);
	    }
        ctm2 = java.lang.System.nanoTime();//get current timer value
		System.out.println("Seconds to run View: "+ (double)(ctm2 - ctm1)/1000000000d);//+"\n"
	}
}
