package com.jacobncalvert.icdb;
import java.sql.*;
public class DatabaseObject {

	private Connection connection = null;
	public DatabaseObject(String dbPath) {
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ResultSet search(String term)
	{
		String sql = String.format("SELECT * FROM items WHERE title LIKE '%%%s%%' OR description LIKE '%%%s%%' OR "
				+ "category LIKE '%%%s%%' OR manufacturer LIKE '%%%s%%' OR part_id LIKE '%%%s%%';", term, term, term, term, term);
		
		return query(sql);
	}
	
	public ResultSet query(String sql)
	{
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			//stmt.close();
			return set;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(String sql)
	{
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public ResultSet getAllItems()
	{
		return query("SELECT * FROM items;");
	}
	
	public ResultSet getAllMetaInfo(Integer item_id)
	{
		return query(String.format("SELECT * FROM items_meta WHERE item_id = '%d';", item_id));
	}
	
	public ResultSet getAllCategories()
	{
		return query("SELECT DISTINCT category FROM items;");
	}
	public ResultSet getAllManufacturers()
	{
		return query("SELECT DISTINCT manufacturer FROM items;");
	}
	
	public ResultSet getItem(int id)
	{
		return query(String.format("SELECT * FROM items WHERE id='%d'", id));
	}
	public ResultSet getMeta(int id)
	{
		return query(String.format("SELECT * FROM items_meta WHERE item_id='%d'", id));
	}
	
	public void deleteItem(int id)
	{
		update(String.format("DELETE FROM items WHERE id='%d';", id));
		update(String.format("DELETE FROM items_meta WHERE item_id='%d';", id));
		ICDB.update();
		
	}
	
	public long insert(String sql)
	{
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.execute(sql);
			ResultSet set = stmt.getGeneratedKeys();
			if(set.next())
			{
				return set.getLong(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
}
