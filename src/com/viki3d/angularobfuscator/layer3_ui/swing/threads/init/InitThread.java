package com.viki3d.angularobfuscator.layer3_ui.swing.threads.init;

import javax.swing.SwingUtilities;

import com.viki3d.angularobfuscator.layer3_ui.swing.MainForm;

/**
 * The Init thread of this Swing Application.
 * 
 * @author Victor Kirov
 *
 */
public class InitThread {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	new MainForm().setVisible(true);
		    }
		});

	}	
	
}
