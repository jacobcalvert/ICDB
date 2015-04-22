package com.jacobncalvert.icdb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuClickActions implements ActionListener
{

	private String menuClicked;
	public MenuClickActions(String menuClicked)
	{
		this.menuClicked = menuClicked;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(menuClicked)
		{
		case "New Item":newItemAction();break;
		case "Exit":exitAction();break;
		case "Export-Text":exportAsTextAction();break;
		case "Export-CSV":exportAsCSVAction();break;
		case "Export-SQL":exportAsSQLAction();break;
		case "Statistics":runStats();break;
		}

	}
	
	private void runStats()
	{
		ResultSet rs = DatabaseController.instance().getDB().getAllItems();
		try
		{
			Double totalPrice = 0.0;
			Integer totalItems = 0, totalPcs=0;
			
			while(rs.next())
			{
				totalPrice+= rs.getDouble("qty")*rs.getDouble("unit_price");
				totalItems++;
				totalPcs += rs.getInt("qty");
			}
			
			StatsWindow window = new StatsWindow(totalItems, totalPcs, totalPrice);
			window.setVisible(true);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void newItemAction()
	{
		NewItemWindow window = new NewItemWindow();
		window.setVisible(true);
	}
	
	private void exitAction()
	{
		System.exit(0);
	}

	private void exportAsTextAction()
	{
		Exporter exporter = new TextExporter();
		exporter.run();
	}
	
	private void exportAsCSVAction()
	{
		Exporter exporter = new CSVExporter();
		exporter.run();
	}
	
	private void exportAsSQLAction()
	{
		Exporter exporter = new SQLExporter();
		exporter.run();
	}
	
	
}
