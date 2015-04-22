package com.jacobncalvert.icdb;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StatsWindow extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1024221373553879586L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public StatsWindow(Integer numItems, Integer numPcs, Double worth)
	{
		setTitle("Statistics");
		setBounds(100, 100, 290, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTotalItems = new JLabel("Total Items:");
		GridBagConstraints gbc_lblTotalItems = new GridBagConstraints();
		gbc_lblTotalItems.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalItems.gridx = 1;
		gbc_lblTotalItems.gridy = 1;
		contentPane.add(lblTotalItems, gbc_lblTotalItems);
		
		JLabel lblNumitems = new JLabel("...");
		GridBagConstraints gbc_lblNumitems = new GridBagConstraints();
		gbc_lblNumitems.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumitems.gridx = 2;
		gbc_lblNumitems.gridy = 1;
		contentPane.add(lblNumitems, gbc_lblNumitems);
		
		JLabel lblTotalpieces = new JLabel("Total Pieces:");
		GridBagConstraints gbc_lblTotalpieces = new GridBagConstraints();
		gbc_lblTotalpieces.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalpieces.gridx = 1;
		gbc_lblTotalpieces.gridy = 2;
		contentPane.add(lblTotalpieces, gbc_lblTotalpieces);
		
		JLabel lblNumpcs = new JLabel("...");
		GridBagConstraints gbc_lblNumpcs = new GridBagConstraints();
		gbc_lblNumpcs.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumpcs.gridx = 2;
		gbc_lblNumpcs.gridy = 2;
		contentPane.add(lblNumpcs, gbc_lblNumpcs);
		
		JLabel lblTotalWorth = new JLabel("Total Worth:");
		GridBagConstraints gbc_lblTotalWorth = new GridBagConstraints();
		gbc_lblTotalWorth.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalWorth.gridx = 1;
		gbc_lblTotalWorth.gridy = 3;
		contentPane.add(lblTotalWorth, gbc_lblTotalWorth);
		
		JLabel lblWorth = new JLabel("");
		GridBagConstraints gbc_lblWorth = new GridBagConstraints();
		gbc_lblWorth.gridx = 2;
		gbc_lblWorth.gridy = 3;
		contentPane.add(lblWorth, gbc_lblWorth);
		lblWorth.setText(String.format("$%.2f", worth));
		lblNumpcs.setText(numPcs.toString());
		lblNumitems.setText(numItems.toString());
	}

}
