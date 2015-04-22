package com.jacobncalvert.icdb;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TextExporter extends Exporter
{

	public TextExporter()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	public void export()
	{
		ResultSet rs = DatabaseController.instance().getDB().getAllItems();
		exportResults = "ICDB TEXT EXPORT\n";
		try
		{
			while(rs.next())
			{
				exportResults += String.format("Title: %s\n", rs.getString("title"));
				exportResults += String.format("	Record #: %s\n", rs.getString("id"));
				exportResults += String.format("	Description: %s\n", rs.getString("description"));
				exportResults += String.format("	Category: %s\n", rs.getString("category"));
				exportResults += String.format("	Manufacturer: %s\n", rs.getString("manufacturer"));
				exportResults += String.format("	Part ID: %s\n", rs.getString("part_id"));
				exportResults += String.format("	Unit Price: %s\n", rs.getString("unit_price"));
				exportResults += String.format("	Qty: %s\n", rs.getString("qty"));
				exportResults += String.format("	URL: %s\n", rs.getString("url"));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
