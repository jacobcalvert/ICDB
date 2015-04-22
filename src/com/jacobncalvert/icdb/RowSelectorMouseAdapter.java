package com.jacobncalvert.icdb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class RowSelectorMouseAdapter extends MouseAdapter
{
	private JPopupMenu popup;
	
	public RowSelectorMouseAdapter(JPopupMenu popup)
	{
		this.popup = popup;
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		JTable source = (JTable) e.getSource();
		int row = source.rowAtPoint( e.getPoint() );
        int column = source.columnAtPoint( e.getPoint() );

        if (! source.isRowSelected(row))
        {
        	source.changeSelection(row, column, false, false);
        }
        if(e.getButton()== MouseEvent.BUTTON3)   
        {
        	if(this.popup != null)
        	{
        		this.popup.show(e.getComponent(), e.getX(), e.getY());
        	}
        	
        }
        else
        {
        	if(e.getClickCount()>=2)
        	{
        		this.dbl_click();
        	}
        }
	}
	public void dbl_click()
	{
		
	}

}
