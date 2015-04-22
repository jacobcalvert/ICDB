package com.jacobncalvert.icdb;
import java.io.File;
import java.sql.*;
public class DatabaseController {

	private String[] databaseCreationSQL = new String[2];
	String defaultDBPath;
	static private DatabaseController instance = null;
	private DatabaseObject dbObj = null;
	private DatabaseController() {
		this.databaseCreationSQL[0] = "CREATE TABLE items ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "title VARCHAR,"
				+ "description VARCHAR,"
				+ "category VARCHAR,"
				+ "part_id VARCHAR,"
				+ "manufacturer VARCHAR,"
				+ "unit_price REAL,"
				+ "qty INTEGER,"
				+ "url VARCHAR"
				+ "); ";
		this.databaseCreationSQL[1] = "CREATE TABLE items_meta ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "item_id INTEGER NOT NULL,"
				+ "meta_key VARCHAR,"
				+ "meta_value VARCHAR"
				+ ");";
		
		this.defaultDBPath = "icdb.db";
	}
	static public DatabaseController instance()
	{
		if(DatabaseController.instance == null)
		{
			DatabaseController.instance = new DatabaseController();
		}
		return DatabaseController.instance;
	}
	public void createNewDatabase(String dbPath)
	{
		Connection c = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
			Statement s = c.createStatement();
			s.execute(this.databaseCreationSQL[0]);
			s.execute(this.databaseCreationSQL[1]);
			s.close();
			c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createNewDatabase()
	{
		if(fileExists(this.defaultDBPath))
		{
			deleteDBFile();
		}
		this.createNewDatabase(this.defaultDBPath);
	}
	
	public DatabaseObject getDB()
	{
		if(dbObj==null)
		{
			dbObj = new DatabaseObject(this.defaultDBPath);
		}
		return dbObj;
	}
	
	public Boolean databaseExists()
	{
		return fileExists(this.defaultDBPath);
	}
	
	private Boolean fileExists(String path)
	{
		File file = new File(path);
		return file.exists();
	}
	
	private void deleteDBFile()
	{
		File file = new File(this.defaultDBPath);
		file.delete();
	}
	
	
	
	

}
