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
		term = "%"+term+"%";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM items WHERE title LIKE ? OR description LIKE ? OR "
					+ "category LIKE ? OR manufacturer LIKE ? OR part_id LIKE ?;");
			stmt.setString(1, term);
			stmt.setString(2, term);
			stmt.setString(3, term);
			stmt.setString(4, term);
			stmt.setString(5, term);
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet query(String sql)
	{
		sql = sql.replaceAll("'", "\'");
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
	
	public void update(int id,
			String title,
			String desc,
			String category,
			String manufacturer,
			String part_id,
			String url,
			float unit_price,
			int qty)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("UPDATE items SET title=?,description=?,category=?,manufacturer=?,"
					+ "part_id=?,url=?,unit_price=?,qty=? WHERE id=?");
			int c = 1;
			stmt.setString(c++, title);
			stmt.setString(c++, desc);
			stmt.setString(c++, category);
			stmt.setString(c++, manufacturer);
			stmt.setString(c++, part_id);
			stmt.setString(c++, url);
			stmt.setFloat(c++, unit_price);
			stmt.setInt(c++, qty);
			stmt.setInt(c++, id);
			stmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getAllItems()
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM items;");
			
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getAllMetaInfo(Integer item_id)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM items WHERE id=?;");
			stmt.setInt(1, item_id);
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getAllCategories()
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT DISTINCT category FROM items;");
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet getAllManufacturers()
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT DISTINCT manufacturer FROM items;");
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getItem(int id)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM items WHERE id=?");
			stmt.setInt(1, id);
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getMeta(int id)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM items_meta WHERE item_id=?");
			stmt.setInt(1, id);
			return stmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteItem(int id)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM items WHERE id=?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteItemMeta(int id)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM items_meta WHERE id=?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public long insert(String title,
			String desc,
			String category,
			String manufacturer,
			String part_id,
			String url,
			float unit_price,
			int qty)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("INSERT INTO items(qty,title,description,part_id,unit_price,url,manufacturer,category) VALUES("
				+ "?,?,?,?,?,?,?,?)");
			int c = 1;
			stmt.setString(c++, title);
			stmt.setString(c++, desc);
			stmt.setString(c++, category);
			stmt.setString(c++, manufacturer);
			stmt.setString(c++, part_id);
			stmt.setString(c++, url);
			stmt.setFloat(c++, unit_price);
			stmt.setInt(c++, qty);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
			{
				long id = rs.getLong(1);
				return id;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
	
	public void insertMeta(int item_id, String k, String v)
	{
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("INSERT INTO items_meta(item_id, meta_key, meta_value) VALUES(?,?,?);");
			stmt.setInt(1, item_id);
			stmt.setString(2, k);
			stmt.setString(3, v);
			stmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
