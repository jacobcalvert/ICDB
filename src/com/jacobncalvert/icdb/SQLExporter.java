package com.jacobncalvert.icdb;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLExporter extends Exporter
{

	public SQLExporter()
	{
		super();
	}
	
	public void export()
	{
		ResultSet rs = DatabaseController.instance().getDB().query("SELECT sql FROM sqlite_master WHERE tbl_name='items';");
		
		try
		{
			if(rs.next())
			{
				exportResults = rs.getString("sql") + ";\n";
			}
			rs = DatabaseController.instance().getDB().query("SELECT * FROM items;");
			while(rs.next())
			{
				String sql = String.format("INSERT INTO items(id,title,description,category,part_id,manufacturer,unit_price,qty,url) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s');\n", 
						rs.getString("id"),rs.getString("title"),rs.getString("description"),rs.getString("category"),rs.getString("part_id"),
						rs.getString("manufacturer"),rs.getString("unit_price"),rs.getString("qty"),rs.getString("url"));
			
				exportResults += sql;
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
