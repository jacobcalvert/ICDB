package com.jacobncalvert.icdb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

public class MetaTablePopupMenu extends JPopupMenu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417341006441001838L;
	
	private int row;
	private MetaInfoTableModel model;

	public MetaTablePopupMenu()
	{
		JMenuItem add = new JMenuItem("Add Meta Value");
		this.add(add);
		add.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						ArrayList<String> keyval = NewKeyValPairPopup.display();
						if(keyval != null)
						{
							model.addRow(new Object[]{
									keyval.get(0),
									keyval.get(1)
							});
						}
						
					}
			
				});;
		JMenuItem remove = new JMenuItem("Remove Meta Value");
		this.add(remove);
		
		remove.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String key = (String) model.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, String.format("Are you sure you to delete the '%s' key pair?", key));
				
				
				if(result == JOptionPane.OK_OPTION)
				{
					model.removeRow(row);
				}
				
				
			}
	
		});;
	}

	public void setRC(int r, int c)
	{
		row = r;
	}
	
	public void setModel(TableModel model)
	{
		this.model = (MetaInfoTableModel)model;
	}
	
}
