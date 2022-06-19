package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.Textures.Textures;
import com.controller.Controller;
import com.ui.ColorClass;
import com.ui.UI;
import com.values.Values;

public class MainView implements ActionListener{

	private EmployeeView e;
	private Textures textures;
	private JFrame frame;
	private Controller c;
	private UI ui;
	private ColorClass cc;
	private int cardNumber = 1;

	public MainView(Controller c) {
		this.c = c;
		textures = new Textures();
		ui = new UI();
		cc = new ColorClass();
		e = new EmployeeView(ui,cc,textures);
		init();
	}

	public void init() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(Values.width, Values.height);
		frame.setLayout(new BorderLayout());
		titleBar = ui.UIBar(cc.defaultBlack);
		minimize = ui.UIButton("-",null,null,cc.defaultBlack, Color.white, cc.overlayBlack);
		cross = ui.UIButton("X",null, null,cc.defaultBlack, Color.white, Color.red);
		title = ui.UILabel(" Salary Management", null, Color.white);
		ui();
		featuresPanel();
		features();
		frame.setVisible(true);
	}

	private JMenuBar titleBar;
	private JLabel title;
	private JButton minimize, cross;
	private JPanel sidePanel,sidePanelInner;
	private JButton register,search,salary;
	private JPanel centerPanel;
	private CardLayout card;
	private JPanel registerPanel,searchPanel,salaryPanel;

	private ImageIcon titleLogo;
	private JLabel logoLabel;

	private void ui() {
		titleLogo = textures.titleLogo;
		logoLabel = new JLabel(titleLogo);
		titleBar.add(logoLabel);
		titleBar.add(title);
		titleBar.add(Box.createHorizontalGlue());
		titleBar.add(minimize);
		titleBar.add(cross);
		/*Listeners*/
		minimize.addActionListener(this);
		cross.addActionListener(this);
		frame.add(titleBar,BorderLayout.NORTH);
	}

	private void features() {
		sidePanel = ui.UIPanel(cc.lightBlack, new BorderLayout());
		sidePanelInner = ui.UIPanel(cc.lightBlack, new GridLayout(15, 1));
		JLabel dashboard = ui.UILabel(textures.logoFrontTw);
		register = ui.UIButton("Register Employee",textures.addIconW,textures.addIconB, cc.lightBlack, Color.white, null);
		search = ui.UIButton("Search Employee",textures.searchIconW,textures.searchIconB,cc.lightBlack, Color.white, null);
		salary = ui.UIButton("Salary & Update",textures.moneyIconW,textures.updateIconW, cc.lightBlack, Color.white, null);

		uiCard(cardNumber);

		register.addActionListener(this);
		search.addActionListener(this);
		salary.addActionListener(this);

		sidePanelInner.add(register);
		sidePanelInner.add(search);
		sidePanelInner.add(salary);

		sidePanel.add(dashboard,BorderLayout.NORTH);
		sidePanel.add(sidePanelInner);
		frame.add(sidePanel,BorderLayout.WEST);
	}

	private void featuresPanel() {
		card = new CardLayout(10,10);
		centerPanel = ui.UIPanel(Color.white,card);
		registerPanel = ui.UIPanel(Color.white);
		searchPanel = ui.UIPanel(Color.white);
		salaryPanel = ui.UIPanel(Color.white);
		/*Test UI*/
		salaryPanel.setBackground(Color.green);
		/*EmployeeView*/
		e.registerEmployee(registerPanel,c);
		e.searchEmployee(searchPanel,c);
		e.salary(salaryPanel,c);
		/*Adding*/
		centerPanel.add("register",registerPanel);
		centerPanel.add("search",searchPanel);
		centerPanel.add("salary",salaryPanel);
		frame.add(centerPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==minimize) {
			frame.setState(JFrame.ICONIFIED);
		}else if(e.getSource()==cross) {
			System.exit(0);
		}else if(e.getSource()==register) {
			card.show(centerPanel, "register");
			cardNumber=1;
		}else if(e.getSource()==search) {
			card.show(centerPanel, "search");
			cardNumber=2;
		}else if(e.getSource()==salary) {
			card.show(centerPanel, "salary");
			cardNumber=3;
		}
		uiCard(cardNumber);
	}

	private void uiCard(int cardNumber) {
		register.setBackground(cc.lightBlack);
		search.setBackground(cc.lightBlack);
		salary.setBackground(cc.lightBlack);
		register.setForeground(Color.white);
		search.setForeground(Color.white);
		salary.setForeground(Color.white);
		register.setIcon(textures.addIconW);
		search.setIcon(textures.searchIconW);
		salary.setIcon(textures.moneyIconW);
		if(cardNumber==1) {
			register.setBackground(Color.white);
			register.setForeground(cc.defaultBlack);
			register.setIcon(textures.addIconB);
		}else if(cardNumber==2) {
			search.setBackground(Color.white);
			search.setForeground(cc.defaultBlack);
			search.setIcon(textures.searchIconB);
		}else if(cardNumber==3) {
			salary.setBackground(Color.white);
			salary.setForeground(cc.defaultBlack);
			salary.setIcon(textures.moneyIconB);
		}
	}
}
