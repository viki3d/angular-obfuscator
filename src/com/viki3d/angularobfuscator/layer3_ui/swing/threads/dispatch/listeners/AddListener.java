package com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 * Context menu AddFile listener
 * 
 * @see <code>cmMain</code> in <code>MainForm</code>  
 * 
 * @author Victor Kirov
 *
 */
public class AddListener implements ActionListener {
	
	private JFrame jFrame;
	private JList<String> jList;
	
	public AddListener(JFrame jFrame, JList<String> jList) {
		this.jFrame = jFrame;
		this.jList = jList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(jFrame)==JFileChooser.APPROVE_OPTION) {
			//	Debug selected filename:		
			//  JOptionPane.showMessageDialog(jFrame, jfc.getSelectedFile()
			//			.getAbsoluteFile());
			
			@SuppressWarnings("all")
	        DefaultListModel<String> dlm = (DefaultListModel) jList.getModel();
	        dlm.addElement(jfc.getSelectedFile().getAbsolutePath());
	        
	        //  Sort dlm alphabetically:
	        Enumeration<String> en = dlm.elements();
	        List<String> listItems = Collections.list(en);
	        Collections.sort( listItems );

	        jList.setModel(dlm);			
		}
		
	}	

}
