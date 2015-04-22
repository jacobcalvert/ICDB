package com.jacobncalvert.icdb;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class NewKeyValPairPopup
{

    public static ArrayList<String> display()
    {
        JTextField key = new JTextField();
        JTextField value = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Key:"));
        panel.add(key);
        panel.add(new JLabel("Value:"));
        panel.add(value);
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter new fields",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
        	ArrayList<String> res = new ArrayList<String>();
        	String key_str = key.getText(), val_str = value.getText();
        	if(key_str.length() > 0 && val_str.length() > 0)
        	{
        		res.add(key_str);
        		res.add(val_str);
        	}
        	return res;
        }
		return null;
    }

    
}