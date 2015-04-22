package com.jacobncalvert.icdb;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Toolkit;

public class ICDB extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6118411711318898840L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private MainWindowTableModel tableModel;
	private DatabaseObject dbObject;
	private JLabel lblSearchFilter;
	private JPopupMenu rtClickContext;
	static private ArrayList<ICDB> instances =  new ArrayList<ICDB>();
	private HashMap<Integer, Integer> keypressHoldoff = new HashMap<Integer, Integer> ();
	private Integer holdoffPeriod = 1; //in seconds

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ICDB frame = new ICDB();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ICDB()
	{
		
		initGUI();
		initNonGUIComponents();
		ICDB.instances.add(this);
	}
	
	static public void update()
	{
		for(ICDB icdb: ICDB.instances)
		{
			icdb.updateTable();
		}
	}
	
	
	private void updateTable()
	{
		this.tableModel.setRowCount(0);
		this.tableModel.loadTable(DatabaseController.instance().getDB().getAllItems());
	}
	
	public void initNonGUIComponents()
	{
		this.tableModel = new MainWindowTableModel();
		txtSearch.addKeyListener(new SearchInputKeyListener(this.tableModel, txtSearch, lblSearchFilter));
		this.table.setModel(this.tableModel);
		
		DatabaseController dbCont = DatabaseController.instance();
		if(!dbCont.databaseExists())
		{
			dbCont.createNewDatabase();
		}
		
		dbObject = dbCont.getDB();
		table.getColumn("ID").setMaxWidth(75);
		table.setEnabled(false);
		table.setAutoCreateRowSorter(true);
		this.tableModel.loadTable(dbObject.getAllItems());
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		        
		    	  handleKeyEvent(e);
    	
		    	
		        return false;
		      }
		});
	}
	static private int currentTime()
	{
		return (int) (System.currentTimeMillis() / 1000L);
	}
	private void handleKeyEvent(KeyEvent event)
	{
		if(!event.isControlDown())
		{
			return;
		}
		
		if(this.keypressHoldoff.containsKey(event.getKeyCode()))
		{
			if((currentTime() - this.keypressHoldoff.get(event.getKeyCode())) < this.holdoffPeriod)
			{
				return;
			}
			else
			{
				this.keypressHoldoff.remove(event.getKeyCode());
				this.keypressHoldoff.put(event.getKeyCode(), currentTime());
			}
		}
		else
		{
			this.keypressHoldoff.put(event.getKeyCode(), currentTime());
		}
		
		switch(event.getKeyCode())
		{
			case KeyEvent.VK_N:
			{
				NewItemWindow window = new NewItemWindow();
				window.setVisible(true);
				break;
			}
			case KeyEvent.VK_M:
			{
				this.setExtendedState(MAXIMIZED_BOTH);    
				break;
			}
		}
	}
	
	public void initGUI()
	{
		setTitle("ICDB");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ICDB.class.getResource("/resources/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnIcdb = new JMenu("ICDB");
		menuBar.add(mnIcdb);
		
		JMenu mnExportAs = new JMenu("Export as...");
		mnIcdb.add(mnExportAs);
		
		JMenuItem mntmSql = new JMenuItem("SQL");
		mnExportAs.add(mntmSql);
		mntmSql.addActionListener(new MenuClickActions("Export-SQL"));
		
		JMenuItem mntmCSV = new JMenuItem("CSV");
		mnExportAs.add(mntmCSV);
		mntmCSV.addActionListener(new MenuClickActions("Export-CSV"));
		JMenuItem mntmText = new JMenuItem("Text");
		mntmText.addActionListener(new MenuClickActions("Export-Text"));
		mnExportAs.add(mntmText);
		
		JMenuItem mntmSaveDatabase = new JMenuItem("Save database");
		mnIcdb.add(mntmSaveDatabase);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new MenuClickActions("Exit"));
		mnIcdb.add(mntmExit);
		
		JMenu mnItems = new JMenu("Items");
		menuBar.add(mnItems);
		
		JMenuItem mntmNewItem = new JMenuItem("New item");
		mntmNewItem.addActionListener(new MenuClickActions("New Item"));
		mnItems.add(mntmNewItem);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		mnItems.add(mntmStatistics);
		mntmStatistics.addActionListener(new MenuClickActions("Statistics"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{120, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSearch = new JLabel("Search:");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 0;
		contentPane.add(lblSearch, gbc_lblSearch);
		
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 0;
		contentPane.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		
		
		JLabel lblFilterResults = new JLabel("Filter Results:");
		GridBagConstraints gbc_lblFilterResults = new GridBagConstraints();
		gbc_lblFilterResults.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterResults.gridx = 0;
		gbc_lblFilterResults.gridy = 1;
		contentPane.add(lblFilterResults, gbc_lblFilterResults);
		
		lblSearchFilter = new JLabel("");
		GridBagConstraints gbc_lblSearchFilter = new GridBagConstraints();
		gbc_lblSearchFilter.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchFilter.gridx = 1;
		gbc_lblSearchFilter.gridy = 1;
		contentPane.add(lblSearchFilter, gbc_lblSearchFilter);
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		rtClickContext = new JPopupMenu();
		JMenuItem mntmViewPart = new JMenuItem("View Item");
		rtClickContext.add(mntmViewPart);
		mntmViewPart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int row = table.getSelectedRow();
				int id = (int) tableModel.getValueAt(row, 0);
				ViewItemWindow window = new ViewItemWindow(id);
				window.setVisible(true);
				
			}
			
		});
		JMenuItem mntmEditPart = new JMenuItem("Edit Item");
		mntmEditPart.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int row = table.getSelectedRow();
				int id = (int) tableModel.getValueAt(row, 0);
				EditItemWindow window = new EditItemWindow(id);
				window.setVisible(true);
				
			}
			
		});
		rtClickContext.add(mntmEditPart);
		JMenuItem mntmDelPart = new JMenuItem("Delete Item");
		rtClickContext.add(mntmDelPart);
		mntmDelPart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int row = table.getSelectedRow();
				int id = (int) tableModel.getValueAt(row, 0);
				String title = (String) tableModel.getValueAt(row, 1);
				int result = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to delete '%s' ?", title));
				if(result == JOptionPane.OK_OPTION)
				{
					DatabaseController.instance().getDB().deleteItem(id);
				}
				
			}
			
		});
		
		table.addMouseListener(new RowSelectorMouseAdapter(this.rtClickContext)
		{
			@Override
			public void dbl_click()
			{
				int row = table.getSelectedRow();
				int id = (int) tableModel.getValueAt(row, 0);
				EditItemWindow window = new EditItemWindow(id);
				window.setVisible(true);
			}
		});
		
	}

}
