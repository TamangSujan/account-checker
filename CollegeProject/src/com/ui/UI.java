package com.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UI {

	private ColorClass cc = new ColorClass();

	//Public
	public Font serif16 = new Font("sansserif",Font.PLAIN,16);
	public Font serif11 = new Font("sansserif",Font.PLAIN,11);
	public Font serif14 = new Font("sansserif",Font.PLAIN,14);

	//LogIn UI
	public JTextField UITextField(String text,Color bg,Color fg,Color caret,Border border,Color focus) {
		JTextField field = new JTextField(text);
		field.setFont(serif14);
		if(border!=null)
			field.setBorder(border);
		if(bg!=null)
			field.setBackground(bg);
		if(fg!=null)
			field.setForeground(fg);
		if(caret!=null)
			field.setCaretColor(caret);
		field.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				field.setBackground(focus);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e) {
				field.setBackground(bg);
				super.mouseExited(e);
			}
		});
		return field;
	}
	public JPasswordField UIPasswordField() {
		JPasswordField field = new JPasswordField();
		field.setFont(serif16);
		field.setBorder(cc.tdBorderW);
		field.setBackground(cc.defaultBlack);
		field.setForeground(Color.white);
		field.setCaretColor(Color.white);
		return field;
	}
	public JLabel UILabel(ImageIcon icon) {
		JLabel label = new JLabel(icon);
		label.setForeground(Color.white);
		return label;
	}

	public JLabel UILabel(String text,Color bgColor,Color fgColor) {
		JLabel label = new JLabel(text);
		if(bgColor!=null) {
			label.setOpaque(true);
			label.setBackground(bgColor);
		}
		else
			label.setOpaque(false);
		label.setForeground(fgColor);
		label.setFont(serif14);
		return label;
	}

	public JButton UIButton(String text,ImageIcon icon,ImageIcon rollOver,Color bg, Color fg,Color overlayBg) {
		JButton button = new JButton(text,icon);
		button.setForeground(fg);
		button.setBackground(bg);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		if(overlayBg!=null) {
			button.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					button.setBackground(overlayBg);
					button.setIcon(rollOver);
					super.mouseEntered(e);
				}
				public void mouseExited(MouseEvent e) {
					button.setBackground(bg);
					button.setIcon(icon);
					super.mouseExited(e);
				}
			});
		}
		button.setFont(serif14);
		return button;
	}

	public JMenuBar UIBar(Color bg) {
		JMenuBar bar = new JMenuBar();
		bar.setBorderPainted(false);
		bar.setBackground(bg);
		return bar;
	}

	public JPanel UIPanel(Color bg) {
		JPanel panel = new JPanel();
		panel.setBackground(bg);
		return panel;
	}

	public JPanel UIPanel(Color bg,LayoutManager layout) {
		JPanel panel = new JPanel(layout);
		panel.setBackground(bg);
		return panel;
	}
	
	int offset = 100;
	public JPanel UIPanel(ImageIcon image,LayoutManager layout,JPanel size,Color bgColor) {
		JPanel panel = new JPanel(layout) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public void setBackground(Color bg) {
				bg = bgColor;
				super.setBackground(bg);
			}
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getImage(), size.getWidth()/4-image.getIconWidth()/2, size.getHeight()/2-image.getIconHeight()/2, 
						size.getWidth()/4+image.getIconWidth()/2, size.getHeight()/2+image.getIconHeight()/2,
						0, 0, 
						image.getIconWidth(),image.getIconHeight(),
						null);
			}
		};
		return panel;
	}

	public JScrollPane UIScrollPane(JTable table,Color thumb, Color track, Color buttonBg,Color buttonArrow) {
		JScrollPane pane = new JScrollPane(table);
		pane.setBackground(track);
		pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			public Dimension getPreferredSize(JComponent c) {
				return new Dimension(10,c.getHeight());
			}
			protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
				super.paintThumb(g, c, thumbBounds);
				g.setColor(thumb);
				g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
			}
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				super.paintTrack(g, c, trackBounds);
				g.setColor(track);
				g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
			}
			protected JButton createDecreaseButton(int orientation) {
				return customDecreaseButton();
			}
			protected JButton createIncreaseButton(int orientation) {
				return customIncreaseButton();
			}

			protected JButton customIncreaseButton() {
				return new BasicArrowButton(BasicArrowButton.SOUTH,buttonBg,null,buttonArrow,null) {
					private static final long serialVersionUID = 1L;
					public void setBorder(Border border) {
						border = BorderFactory.createLineBorder(buttonBg);
						super.setBorder(border);
					}
				};
			}

			protected JButton customDecreaseButton() {
				return new BasicArrowButton(BasicArrowButton.NORTH,buttonBg,null,buttonArrow,null) {
					private static final long serialVersionUID = 1L;
					public void setBorder(Border border) {
						border = BorderFactory.createLineBorder(buttonBg);
						super.setBorder(border);
					}
				};
			}
		});
		pane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			public Dimension getPreferredSize(JComponent c) {
				return new Dimension(c.getWidth(),10);
			}
			protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
				super.paintThumb(g, c, thumbBounds);
				g.setColor(thumb);
				g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
			}
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				super.paintTrack(g, c, trackBounds);
				g.setColor(track);
				g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
			}
			protected JButton createDecreaseButton(int orientation) {
				return customDecreaseButton();
			}
			protected JButton createIncreaseButton(int orientation) {
				return customIncreaseButton();
			}

			protected JButton customIncreaseButton() {
				return new BasicArrowButton(BasicArrowButton.EAST,buttonBg,null,buttonArrow,null) {
					private static final long serialVersionUID = 1L;
					public void setBorder(Border border) {
						border = BorderFactory.createLineBorder(buttonBg);
						super.setBorder(border);
					}
				};
			}

			protected JButton customDecreaseButton() {
				return new BasicArrowButton(BasicArrowButton.WEST,buttonBg,null,buttonArrow,null) {
					private static final long serialVersionUID = 1L;
					public void setBorder(Border border) {
						border = BorderFactory.createLineBorder(buttonBg);
						super.setBorder(border);
					}
				};
			}
		});
		return pane;
	}
	
	public JTable UITable(Object[] headerName,Object[][] rowData,int cellWidth,int cellHeight,Color headBg,Color headFg,Color cellBg, Color cellFg) {
		//Color cell;
		
		JTable table = new JTable(rowData, headerName);
		table.setSize(new Dimension(headerName.length*cellWidth, table.getRowCount()*cellHeight));
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setHorizontalAlignment(int alignment) {
				alignment = SwingConstants.CENTER;
				super.setHorizontalAlignment(alignment);
			}
			
			@Override
			public void setBorder(Border border) {
				border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
				super.setBorder(border);
			}
			
			@Override
			public void setBackground(Color c) {
				c = headBg;
				super.setBackground(c);
			}
			
			@Override
			public void setForeground(Color c) {
				c = headFg;
				super.setForeground(c);
			}
		});
		
		
		table.setIntercellSpacing(new Dimension(0,0));
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				setHorizontalAlignment(CENTER);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
			
			@Override
			public void setBorder(Border border) {
				border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
				super.setBorder(border);
			}
			
			@Override
			public void setBackground(Color c) {
				c = cellBg;
				super.setBackground(c);
			}
			
			@Override
			public void setForeground(Color c) {
				c = cellFg;
				super.setForeground(c);
			}
			
		});
		table.setShowGrid(false);
		table.setFont(serif14);
		return table;
	}
}
