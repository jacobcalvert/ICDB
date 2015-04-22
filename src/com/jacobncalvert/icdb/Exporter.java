package com.jacobncalvert.icdb;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Exporter
{

	public Exporter()
	{
		//blank
	}
	protected String exportResults;	
	public void showSaveDialog()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(null);
		if(userSelection == JFileChooser.APPROVE_OPTION)
		{
			File toSave = fileChooser.getSelectedFile();
			try
			{
				PrintWriter out = new PrintWriter(toSave);
				out.write(exportResults);
				out.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void run()
	{
		export();
		showSaveDialog();
	}


	public void export()
	{
		// blank
		
	}



}
