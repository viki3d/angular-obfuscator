package com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Obfuscate button action code.
 * 
 * @see <code>btnExit</code> in <code>MainForm</code>  
 * 
 * @author Victor Kirov
 *
 */
public class ExitListener implements ActionListener {
	
	private JFrame jFrame;
	
	public ExitListener(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));		
	}	
	
}
