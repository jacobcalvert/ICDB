package com.jacobncalvert.icdb;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class EditItemWindow extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -453535654220993776L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtDesc;
	private JTextField txtPartNum;
	private JTextField txtUnitPrice;
	private JTextField txtWebsite;
	private JTable table;
	private MetaInfoTableModel tableModel;
	private JComboBox<String> cmbCategory, cmbManufacturer;
	private JTextField txtQty;
	private int itemID;

	/**
	 * Create the frame.
	 */
	public EditItemWindow(Integer item_id)
	{
		itemID = item_id;
		initGUI();
		initNonGUIComponents();
	}
	private void initGUI()
	{
		setTitle("Edit Item");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ICDB.class.getResource("/resources/icon.png")));
		setBounds(100, 100, 450, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("Title:");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		txtTitle = new JTextField();
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 1;
		gbc_txtTitle.gridy = 0;
		contentPane.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblDescriptions = new JLabel("Description:");
		GridBagConstraints gbc_lblDescriptions = new GridBagConstraints();
		gbc_lblDescriptions.anchor = GridBagConstraints.EAST;
		gbc_lblDescriptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptions.gridx = 0;
		gbc_lblDescriptions.gridy = 1;
		contentPane.add(lblDescriptions, gbc_lblDescriptions);
		
		txtDesc = new JTextField();
		txtDesc.setText("");
		GridBagConstraints gbc_txtDesc = new GridBagConstraints();
		gbc_txtDesc.insets = new Insets(0, 0, 5, 0);
		gbc_txtDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDesc.gridx = 1;
		gbc_txtDesc.gridy = 1;
		contentPane.add(txtDesc, gbc_txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.EAST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 2;
		contentPane.add(lblCategory, gbc_lblCategory);
		
		 cmbCategory = new JComboBox<String>();
		cmbCategory.setEditable(true);
		GridBagConstraints gbc_cmbCategory = new GridBagConstraints();
		gbc_cmbCategory.insets = new Insets(0, 0, 5, 0);
		gbc_cmbCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategory.gridx = 1;
		gbc_cmbCategory.gridy = 2;
		contentPane.add(cmbCategory, gbc_cmbCategory);
		
		JLabel lblQty = new JLabel("Qty:");
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.anchor = GridBagConstraints.EAST;
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 0;
		gbc_lblQty.gridy = 3;
		contentPane.add(lblQty, gbc_lblQty);
		
		txtQty = new JTextField();
		GridBagConstraints gbc_txtQty = new GridBagConstraints();
		gbc_txtQty.insets = new Insets(0, 0, 5, 0);
		gbc_txtQty.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQty.gridx = 1;
		gbc_txtQty.gridy = 3;
		contentPane.add(txtQty, gbc_txtQty);
		txtQty.setColumns(10);
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		GridBagConstraints gbc_lblManufacturer = new GridBagConstraints();
		gbc_lblManufacturer.anchor = GridBagConstraints.EAST;
		gbc_lblManufacturer.insets = new Insets(0, 0, 5, 5);
		gbc_lblManufacturer.gridx = 0;
		gbc_lblManufacturer.gridy = 4;
		contentPane.add(lblManufacturer, gbc_lblManufacturer);
		
		cmbManufacturer = new JComboBox<String>();
		cmbManufacturer.setEditable(true);
		GridBagConstraints gbc_cmbManufacturer = new GridBagConstraints();
		gbc_cmbManufacturer.insets = new Insets(0, 0, 5, 0);
		gbc_cmbManufacturer.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbManufacturer.gridx = 1;
		gbc_cmbManufacturer.gridy = 4;
		contentPane.add(cmbManufacturer, gbc_cmbManufacturer);
		
		JLabel lblPartNumber = new JLabel("Part Number:");
		GridBagConstraints gbc_lblPartNumber = new GridBagConstraints();
		gbc_lblPartNumber.anchor = GridBagConstraints.EAST;
		gbc_lblPartNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPartNumber.gridx = 0;
		gbc_lblPartNumber.gridy = 5;
		contentPane.add(lblPartNumber, gbc_lblPartNumber);
		
		txtPartNum = new JTextField();
		txtPartNum.setText("");
		GridBagConstraints gbc_txtPartNum = new GridBagConstraints();
		gbc_txtPartNum.insets = new Insets(0, 0, 5, 0);
		gbc_txtPartNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPartNum.gridx = 1;
		gbc_txtPartNum.gridy = 5;
		contentPane.add(txtPartNum, gbc_txtPartNum);
		txtPartNum.setColumns(10);
		
		JLabel lblUnitPrive = new JLabel("Unit Price:");
		GridBagConstraints gbc_lblUnitPrive = new GridBagConstraints();
		gbc_lblUnitPrive.anchor = GridBagConstraints.EAST;
		gbc_lblUnitPrive.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnitPrive.gridx = 0;
		gbc_lblUnitPrive.gridy = 6;
		contentPane.add(lblUnitPrive, gbc_lblUnitPrive);
		
		txtUnitPrice = new JTextField();
		GridBagConstraints gbc_txtUnitPrice = new GridBagConstraints();
		gbc_txtUnitPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtUnitPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUnitPrice.gridx = 1;
		gbc_txtUnitPrice.gridy = 6;
		contentPane.add(txtUnitPrice, gbc_txtUnitPrice);
		txtUnitPrice.setColumns(10);
		
		JLabel lblWebsite = new JLabel("Website:");
		GridBagConstraints gbc_lblWebsite = new GridBagConstraints();
		gbc_lblWebsite.anchor = GridBagConstraints.EAST;
		gbc_lblWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebsite.gridx = 0;
		gbc_lblWebsite.gridy = 7;
		contentPane.add(lblWebsite, gbc_lblWebsite);
		
		txtWebsite = new JTextField();
		GridBagConstraints gbc_txtWebsite = new GridBagConstraints();
		gbc_txtWebsite.insets = new Insets(0, 0, 5, 0);
		gbc_txtWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWebsite.gridx = 1;
		gbc_txtWebsite.gridy = 7;
		contentPane.add(txtWebsite, gbc_txtWebsite);
		txtWebsite.setColumns(10);
		
		JButton btnAddMeta = new JButton("Add Meta");
		GridBagConstraints gbc_btnAddMeta = new GridBagConstraints();
		gbc_btnAddMeta.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMeta.gridx = 0;
		gbc_btnAddMeta.gridy = 8;
		contentPane.add(btnAddMeta, gbc_btnAddMeta);
		
		btnAddMeta.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<String> keyval = NewKeyValPairPopup.display();
				if(keyval != null)
				{
					tableModel.addRow(new Object[]{
							keyval.get(0),
							keyval.get(1)
					});
				}
				
			}
	
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				
			}
			
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 9;
		contentPane.add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				save();
				
			}
			
		});
	}
	private void initNonGUIComponents()
	{
		tableModel = new MetaInfoTableModel();
		this.table.setModel(tableModel);
		this.table.addMouseListener(new MetaInfoTableMouseAdapter(new MetaTablePopupMenu()));
		
		ResultSet rs = DatabaseController.instance().getDB().getAllCategories();
		try
		{
			while(rs.next())
			{
				
					cmbCategory.addItem(rs.getString("category"));
				
				
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs = DatabaseController.instance().getDB().getAllManufacturers();
		try
		{
			while(rs.next())
			{
				
					cmbManufacturer.addItem(rs.getString("manufacturer"));
				
				
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = DatabaseController.instance().getDB().getItem(itemID);
		try
		{
			if(rs.next())
			{
				
					txtTitle.setText(rs.getString("title"));
					txtDesc.setText(rs.getString("description"));
					txtPartNum.setText(rs.getString("part_id"));
					txtUnitPrice.setText(rs.getString("unit_price"));
					txtWebsite.setText(rs.getString("url"));
					txtQty.setText(rs.getString("qty"));
					cmbCategory.setSelectedItem(rs.getString("category"));
					cmbManufacturer.setSelectedItem(rs.getString("manufacturer"));
					
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = DatabaseController.instance().getDB().getMeta(itemID);
		try
		{
			
		
			while(rs.next())
			{
				tableModel.addRow(new Object[]{
						rs.getString("meta_key"),
						rs.getString("meta_value")
						
				});
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void save()
	{
		String title = txtTitle.getText(), desc = txtDesc.getText(), partnum = txtPartNum.getText(),
				unitprice = txtUnitPrice.getText(), website = txtWebsite.getText(), manufacturer = cmbManufacturer.getSelectedItem().toString(),
				category = cmbCategory.getSelectedItem().toString(), qty = txtQty.getText();
		DatabaseController.instance().getDB().update(this.itemID, title,desc, category,
				manufacturer,partnum,website, Float.parseFloat(unitprice),
				Integer.parseInt(qty));
		
		DatabaseController.instance().getDB().deleteItemMeta(this.itemID);
		
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			String key = (String)tableModel.getValueAt(i, 0), value = (String)tableModel.getValueAt(i, 1);
			DatabaseController.instance().getDB().insertMeta(this.itemID, key, value);
		}
			
	
		ICDB.update();
		dispose();
	}
}
