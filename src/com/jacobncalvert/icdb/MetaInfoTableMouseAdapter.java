package com.jacobncalvert.icdb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class MetaInfoTableMouseAdapter extends MouseAdapter
{
	MetaTablePopupMenu popup;
	public MetaInfoTableMouseAdapter(MetaTablePopupMenu popup)
	{
		// TODO Auto-generated constructor stub
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
        if(e.getButton() == MouseEvent.BUTTON3)
        {
        	this.popup.setRC(row,column);
    		this.popup.setModel(source.getModel());
    		this.popup.show(e.getComponent(), e.getX(), e.getY());
        }
	}

}
