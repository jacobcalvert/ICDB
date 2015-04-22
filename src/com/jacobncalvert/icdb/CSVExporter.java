package com.jacobncalvert.icdb;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVExporter extends Exporter
{

	public CSVExporter()
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	public void export()
	{
		ResultSet rs = DatabaseController.instance().getDB().getAllItems();
		exportResults = "Title,Description,Category,Part ID,Manufacturer,Unit Price,Qty,URL\n";
		try
		{
			while(rs.next())
			{
				exportResults += String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n", rs.getString("title"),
						rs.getString("description"), rs.getString("category"), rs.getString("part_id"),
						rs.getString("manufacturer"), rs.getString("unit_price"), rs.getString("qty"),
						rs.getString("url"));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
