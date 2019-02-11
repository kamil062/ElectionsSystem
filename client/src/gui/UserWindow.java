package gui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import main.Config;
import main.Main;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.Elections;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public abstract class UserWindow implements Window {
	public JFrame frame;
	public JButton btnNewButton;
	public JButton btnSprawdListKandydatw;
	public JButton btnSprawdListOkrgw;
	public JButton btnSprawdListKomisji;
	public JButton btnWyloguj;
	public JButton btnPrzejrzyjWybory;
	public JPanel sidePanel;
	public JPanel panel;
	public JTable table;
	public JTable table_1;
	public JButton btnGosuj;
	public JButton button_1;
	public JButton button_2;
	public JScrollPane scrollPane;
	private JButton btnSprawdListPartii;


	@Override
	public boolean isRunning() {
		return this.getFrame().isVisible();
	}

	public UserWindow() {
		this.init();
	}
	
	@Override
	public void initialize() {
	}

	public void init() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(
				Config.getWindowOffset().width, 
				Config.getWindowOffset().height, 
				Config.getWindowSize().width, 
				Config.getWindowSize().height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 228, 550);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnNewButton = new JButton("Sprawd\u017A list\u0119 komitet\u00F3w wyborczych");
		btnNewButton.setBounds(10, 118, 208, 23);
		panel.add(btnNewButton);
		
		btnSprawdListKandydatw = new JButton("Sprawd\u017A list\u0119 kandydat\u00F3w");
		btnSprawdListKandydatw.setBounds(10, 152, 208, 23);
		panel.add(btnSprawdListKandydatw);
		
		btnSprawdListOkrgw = new JButton("Sprawd\u017A list\u0119 okr\u0119g\u00F3w wyborczych");
		btnSprawdListOkrgw.setBounds(10, 186, 208, 23);
		panel.add(btnSprawdListOkrgw);
		
		btnSprawdListKomisji = new JButton("Sprawd\u017A list\u0119 komisji wyborczych");
		btnSprawdListKomisji.setBounds(10, 220, 208, 23);
		panel.add(btnSprawdListKomisji);
		
		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.changeWindow(WindowsTypes.LOGIN_WINDOW);
				close();
			}
		});
		btnWyloguj.setBounds(10, 516, 208, 23);
		panel.add(btnWyloguj);
		
		btnPrzejrzyjWybory = new JButton("Przejrzyj wybory");
		btnPrzejrzyjWybory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table_1.setModel(Elections.getElections());
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
				table_1.setRowSorter(sorter);
				
				JButton[] voteButtons = new JButton[table_1.getRowCount()];
		
				for(int i = 0; i < table_1.getRowCount(); i++){
					table_1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
						@Override
					    public Component getTableCellRendererComponent(JTable table,
					            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

					        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
					        
					        setForeground(Color.BLACK);
					        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
					        setHorizontalAlignment(JLabel.CENTER);
					      
					        if (table_1.getModel().getValueAt(row, 4).toString().equals("Zakoñczone"))
					            setBackground(Color.RED);
					        else if (table_1.getModel().getValueAt(row, 4).toString().equals("Trwaj¹ce"))
					            setBackground(Color.GREEN);
					        else
					            setBackground(Color.WHITE);
					        
					        return this;
					    }   
					});
					
					voteButtons[i] = new JButton();
					voteButtons[i].setText("Wiêcej");
					voteButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 9));
					voteButtons[i].setBounds(0, 16 * i + 25, 80, 15);
					int ii = Integer.parseInt((String) table_1.getValueAt(i, 0));
					ActionListener al = new ActionListener() {
						private int i = ii; 
						@Override
						public void actionPerformed(ActionEvent e) {
							sidePanel.setVisible(false);
							scrollPane.setViewportView(Elections.getMore(i));
						}
					};
					voteButtons[i].addActionListener(al);
					sidePanel.add(voteButtons[i]);
				}
				
				sidePanel.setVisible(true);
				scrollPane.setViewportView(table_1);
				
				frame.revalidate();
				frame.repaint();
			}
		});
		btnPrzejrzyjWybory.setBounds(10, 57, 208, 23);
		panel.add(btnPrzejrzyjWybory);
		
		btnSprawdListPartii = new JButton("Sprawd\u017A list\u0119 partii");
		btnSprawdListPartii.setBounds(10, 254, 208, 23);
		panel.add(btnSprawdListPartii);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(248, 11, 446, 550);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
			    return false;
			}
			@Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		    	Component component = super.prepareRenderer(renderer, row, column);
		    	int rendererWidth = component.getPreferredSize().width;
		      	TableColumn tableColumn = getColumnModel().getColumn(column);
		     	tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		     	return component;
			}
		};
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		sidePanel = new JPanel();
		sidePanel.setBounds(704, 11, 80, 550);
		frame.getContentPane().add(sidePanel);
		sidePanel.setLayout(null);
	}
	
	@Override
	public void close(){
		this.getFrame().dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame framee) {
		this.frame = framee;
	}
}
