package com.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.Textures.Textures;
import com.controller.Controller;
import com.print.PrintFile;
import com.ui.ColorClass;
import com.ui.UI;
import com.view.EmployeeView;

public class Message{

	private int xOffset = 15;

	private static JDialog globalInfo;
	
	private JPanel globalInfoPanel;
	
	private static JLabel information;
	private Dimension dialogSize = new Dimension(300,120);
	
	private UI ui;
	private ColorClass cc;
	private Textures textures;
	
	public Message() {
		ui = new UI();
		cc = new ColorClass();
		textures = new Textures();
		globalInit();
	}
	
	public static void globalInfo(String message) {
		information.setText(message);
		globalInfo.setVisible(true);
	}
	
	public void globalDialog(EmployeeView e,PrintFile pf,Controller c,String title,String information,int type,JPanel panel) {
		JDialog globalDialog =  new JDialog();
		globalDialog.setIconImage(textures.titleLogo.getImage());
		globalDialog.setLocationRelativeTo(null);
		globalDialog.setSize(dialogSize);
		globalDialog.setLayout(new BorderLayout());
		
		JPanel globalDialogPanel = new JPanel();
		globalDialogPanel.setLayout(null);
		globalDialogPanel.setBackground(Color.white);

		JLabel info = ui.UILabel("", null, cc.defaultBlack);
		JButton no = ui.UIButton("No", null, null, cc.dodgerBlue, Color.white, Color.red);
		JButton yes = ui.UIButton("Yes", null, null, cc.dodgerBlue, Color.white, cc.lightBlack);
		
		info.setBounds(0, 0, globalDialog.getWidth()-xOffset, globalDialog.getHeight()/4);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		yes.setBounds((globalDialog.getWidth()/7)*2-xOffset, (globalDialog.getHeight()/4), globalDialog.getWidth()/5, 30);
		no.setBounds((globalDialog.getWidth()/7)*4-xOffset, (globalDialog.getHeight()/4), globalDialog.getWidth()/5, 30);
		
		globalDialogPanel.add(info);
		globalDialogPanel.add(yes);
		globalDialogPanel.add(no);
		globalDialog.add(globalDialogPanel);
		info.setText(information);
		globalDialog.setTitle(title);

		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				if(type==1)
					c.registerEmployee(e);
				else if(type==2)
					c.updateEmployee(e);
				else if(type==3)
					c.printFile(pf,panel);
				else if(type==4)
					c.deductSalary(e);
				globalDialog.dispose();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				globalDialog.dispose();
			}
		});
		globalDialog.setVisible(true);
	}
	
	public void globalInit(){
		//Global Information
		globalInfo = new JDialog();
		globalInfo.getContentPane().setBackground(Color.white);
		information = ui.UILabel("", null, cc.defaultBlack);
		globalInfo.setSize(dialogSize );
		globalInfo.setTitle("Info");
		globalInfo.setIconImage(textures.titleLogo.getImage());
		globalInfo.setLocationRelativeTo(null);
		information.setHorizontalAlignment(SwingConstants.CENTER);
		
		globalInfoPanel = new JPanel(new BorderLayout());
		globalInfoPanel.setBackground(Color.white);
		
		globalInfoPanel.add(information);
		globalInfo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					globalInfo.dispose();
				super.keyPressed(e);
			}
		});
		globalInfo.add(globalInfoPanel);
		//Global Condition
		
	}

	public void search(EmployeeView e,Controller c,String[][] row, String compareName) {
		c.searchEmployee(e,row,compareName);
	}

	public void updateSearch(EmployeeView e, Controller c) {
		c.updateSearch(e);
	}
}
