package com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * Context menu RemoveFile listener
 * 
 * @see <code>cmMain</code> in <code>MainForm</code>  
 * 
 * @author Victor Kirov
 *
 */
public class RemoveListener implements ActionListener {
	
	private JFrame jFrame;	
	private JList<String> jList;
	
	public RemoveListener(JFrame jFrame, JList<String> jList) {
		this.jFrame = jFrame;
		this.jList = jList;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {

	       int selectedIndex = jList.getSelectedIndex();
	        if (selectedIndex==-1) {
	            JOptionPane.showMessageDialog(jFrame, "Please, select what to remove!");
	        }
	        else {
	            //doNotObfuscateList.remove(selectedIndex); //wrong!
	            DefaultListModel<String> dlm = (DefaultListModel<String>) jList.getModel();
	            dlm.remove(selectedIndex);
	            jList.setModel(dlm);
	        }		
	}	

}
