package com.jacobncalvert.icdb;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class MainWindowTableModel extends DefaultTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2337387050967213765L;
	public MainWindowTableModel()
	{
		this.addColumn("ID");
		this.addColumn("Title");
		this.addColumn("Category");
		this.addColumn("Manufacturer");
		this.addColumn("Part Number");
		this.addColumn("Qty");
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column)
	{
        switch (column)
        {
            case 0:return Integer.class;
            case 1:return String.class;
            case 2:return String.class;
            case 3:return String.class;
            case 4:return String.class;
            case 5:return Integer.class;
            default:return String.class;
        }
	}
	
	public void loadTable(ResultSet tableData)
	{
		try
		{
			while(tableData.next())
			{
				this.addRow(
						new Object[]
								{
									tableData.getInt("id"),
									tableData.getString("title"),
									tableData.getString("category"),
									tableData.getString("manufacturer"),
									tableData.getString("part_id"),
									tableData.getInt("qty"),		
								});
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
