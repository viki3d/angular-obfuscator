package com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.viki3d.angularobfuscator.layer2_logical.Obfuscator;

/**
 * Obfuscate button action code.
 * 
 * @see <code>btnObfuscate</code> in <code>MainForm</code>  
 * 
 * @author Victor Kirov
 *
 */
public class ObfuscateListener implements ActionListener {

	private JFrame jFrame;	
	private JList<String> jList;	
	
	public ObfuscateListener(JFrame jFrame, JList<String> jList) {
		this.jFrame = jFrame;
		this.jList = jList;
	}		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel<String> dlm = (DefaultListModel<String>) jList.getModel();
		ArrayList<String> fileNames = new ArrayList<String>(); 
		for (int i=0; i<dlm.size(); i++) {
			fileNames.add(dlm.get(i));
		}
		
		try {
			new Obfuscator(fileNames).obfuscate();
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(jFrame, ex.getMessage());
		}
	}	

}
