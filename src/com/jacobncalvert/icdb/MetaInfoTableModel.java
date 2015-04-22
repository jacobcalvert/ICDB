package com.jacobncalvert.icdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MetaInfoTableModel extends DefaultTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224944091519988740L;

	public MetaInfoTableModel()
	{
		this.addColumn("Key");
		this.addColumn("Value");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column)
	{
        switch (column)
        {
            case 0:return String.class;
            case 1:return String.class;
            default:return String.class;
        }
	}
	
	public void loadTable(ResultSet metaInfo)
	{
		try
		{
			while(metaInfo.next())
			{
				this.addRow(
						new Object[]
						{
							metaInfo.getString("meta_key"),
							metaInfo.getString("meta_value")
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
