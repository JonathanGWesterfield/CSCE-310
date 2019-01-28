package shipsClient.simpledb;

import java.sql.*;
import simpledb.remote.SimpleDriver;

public class CreateShipsTables {
    public static void main(String[] args) {
		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);
			Statement stmt = conn.createStatement();

			String s = "CREATE TABLE classes (class varchar(20),type varchar(2),country varchar(20),"
					+ "numGuns int,bore int,disp int);";
			stmt.executeUpdate(s);
			System.out.println("Table 'classes' created.");
			
			s = "CREATE TABLE battles (name varchar(20), date varchar(20));";
			stmt.executeUpdate(s);
			System.out.println("Table 'battles' created.");
			
			s = "CREATE TABLE outcomes (ship varchar(20),battle varchar(20),result varchar(10));";
			stmt.executeUpdate(s);
			System.out.println("Table 'outcomes' created.");
			
			s = "CREATE TABLE ships (name varchar(20), class varchar(20), launched int);";
			stmt.executeUpdate(s);
			System.out.println("Table 'ships' created.");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
