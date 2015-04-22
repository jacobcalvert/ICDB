package com.jacobncalvert.icdb;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupNotification extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 464932422039353675L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public PopupNotification(String header, String message)
	{
		
		setBounds(100, 100, 313, 169);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblHeader = new JLabel("");
		GridBagConstraints gbc_lblHeader = new GridBagConstraints();
		gbc_lblHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeader.gridx = 4;
		gbc_lblHeader.gridy = 0;
		contentPane.add(lblHeader, gbc_lblHeader);
		lblHeader.setText(header);
		
		JLabel lblMessage = new JLabel("");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.insets = new Insets(0, 0, 0, 5);
		gbc_lblMessage.gridx = 4;
		gbc_lblMessage.gridy = 1;
		contentPane.add(lblMessage, gbc_lblMessage);
		
		lblMessage.setText(message);
		
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());// height of the task bar
		setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());
	}
	
	public void close()
	{
		dispose();
	}
	
	static public void show(final String header, final String message)
	{
		new Thread(){
		      @Override
		      public void run() {
		           try {
		        	   	PopupNotification pop = new PopupNotification(header, message);
		        	   	pop.setVisible(true);
		                  Thread.sleep(5000); // time after which pop up will be disappeared.
		                  pop.close();
		           } catch (InterruptedException e) {
		                  e.printStackTrace();
		           }
		      };
		}.start();
	}

}
