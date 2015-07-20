package com.viki3d.angularobfuscator.layer3_ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners.AddListener;
import com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners.ExitListener;
import com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners.ObfuscateListener;
import com.viki3d.angularobfuscator.layer3_ui.swing.threads.dispatch.listeners.RemoveListener;


/**
 * The main interface GUI-form
 * 
 * @author Victor Kirov
 *
 */
public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel northPanel;
	private JLabel lblNote;	
	
	private JPanel centerPanel;
	private JList<String> jList1;
	private JPopupMenu cmMain;
	JMenuItem miMainAdd;
	JMenuItem miMainRemove;	
	
	private JPanel southPanel;
	private JButton btnObfuscate;
	private JButton btnExit;
	
	/**
	 * The defaul constructor of main GUI-form
	 */
	public MainForm() {
		
		this.setTitle("Angular Obfuscator by Victor Kirov");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(480, 320);
		this.setLayout(new BorderLayout());
		
		lblNote = new JLabel("Add files here (Html based and your Angular .js files):");
		northPanel = new JPanel();
		northPanel.add(lblNote);
		
		jList1 = new JList<String>();
        
        ListModel<String> listModel = new DefaultListModel<String>();
        jList1.setModel(listModel);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        centerPanel = new JPanel(new GridLayout(2,1,10,10));
        centerPanel.add(jList1);
        centerPanel.setBackground(Color.WHITE);
        

		//cmMain --------------------------------------------------------------
		cmMain = new JPopupMenu();
		miMainAdd = new JMenuItem("Add file");
		miMainAdd.addActionListener(new AddListener(this, jList1));
		miMainRemove = new JMenuItem("Remove");
		miMainRemove.addActionListener(new RemoveListener(this, jList1));
		cmMain.add(miMainAdd);
		cmMain.add(miMainRemove);
		jList1.setComponentPopupMenu(cmMain);

        
        btnExit = new JButton("Exit");
        btnExit.setAlignmentX(CENTER_ALIGNMENT);
        btnExit.addActionListener(new ExitListener(this));
        
        btnObfuscate = new JButton("Obfuscate");
        btnObfuscate.setAlignmentX(CENTER_ALIGNMENT);
        btnObfuscate.addActionListener(new ObfuscateListener(this, jList1));
        
        southPanel = new JPanel(new GridLayout(2,1,10,10));
        southPanel.add(btnObfuscate);
        southPanel.add(btnExit);
        
        this.add(northPanel, BorderLayout.NORTH);     
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
	}
	
	
	
}
