package com.jacobncalvert.icdb;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchInputKeyListener implements KeyListener
{
	private MainWindowTableModel model;
	private JTextField searchBox;
	private JLabel label;
	public SearchInputKeyListener(MainWindowTableModel model, JTextField search, JLabel label)
	{
		this.model = model;
		this.searchBox = search;
		this.label = label;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		String search = searchBox.getText();
		this.model.setRowCount(0);
		label.setText("");
		if(search.length() == 0)
		{
			ICDB.update();
		}
		else
		{
			ResultSet rs = DatabaseController.instance().getDB().search(search);
			this.model.loadTable(rs);
			Integer count = this.model.getRowCount();
			label.setText(String.format("%d results for term '%s'", count, search));
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{

		
		
	}

}
