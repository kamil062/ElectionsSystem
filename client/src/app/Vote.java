package app;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.omg.CORBA.FREE_MEM;

import gui.UserWindow;
import main.Config;
import main.Main;
import main.User;
import net.Net;

public class Vote {
	private static JFrame frame = null;
	private static int electionsId;
	private static int electionsType;
	
	private static void setNewFrame(){
		if(frame != null)
			frame.dispose();
			
		frame = new JFrame();
		frame.setBounds(
				Config.getWindowOffset().width, 
				Config.getWindowOffset().height, 
				400, 
				400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private static void displayForm1(){
		switch(electionsType){
			default:
			case 1:
				Net.sendData("getPresidentialElectionsCandidates");
				System.out.println("getPresidentialElectionsCandidates");
				break;
			case 2:
				Net.sendData("getParliamentaryElectionsCandidates");
				System.out.println("getParliamentaryElectionsCandidates");
				break;
			case 3:
				Net.sendData("getSenateElectionsCandidates");
				System.out.println("getSenateElectionsCandidates");
				break;
		}

		Net.sendData(Integer.toString(electionsId));
		Net.sendData(Integer.toString(electionsType));
		
		try {
			int size = Integer.parseInt(Net.receiveData());
			
			Object[][] obj = new Object[size][4];
			
			for(int i = 0; i < size; i++){
				obj[i][0] = Net.receiveData();
				obj[i][1] = Net.receiveData();
				obj[i][2] = Net.receiveData();
				obj[i][3] = Net.receiveData();
			}
			
			DefaultTableModel tableModel = new DefaultTableModel(
					obj,
					new String[] {
						"ID", "Imiê", "Nazwisko", "Partia"
					}
				);
			
			JTable table = new JTable(tableModel){
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
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
			table.setBounds(0, 0, 400, 400);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportBorder(null);
			scrollPane.setViewportView(table);
			scrollPane.setBounds(0, 0, 300, 375);
			
			JPanel sidePanel = new JPanel();
			sidePanel.setBounds(300, 0, 100, 400);
			sidePanel.setLayout(null);
			
			for(int i = 0; i < table.getRowCount(); i++){
				JButton button = new JButton("Zag³osuj");
				button.setFont(new Font("Tahoma", Font.PLAIN, 9));
				button.setBounds(0, 16 * i + 25, 80, 15);
				final int ii = i;
				button.addActionListener(new ActionListener() {
					int iii = ii;
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println((String) table.getValueAt(iii, 0));
						switch(electionsType){
							default:
							case 1:
								Net.sendData("voteInPresidentialElections");
								break;
							case 2:
								Net.sendData("voteInParliamentaryElections");
								break;
							case 3:
								Net.sendData("voteInSenateElections");
								break;
						}
						
						Net.sendData(Integer.toString(electionsType));
						Net.sendData(Integer.toString(User.getUserID()));
						Net.sendData((String) table.getValueAt(iii, 0));
						
						JOptionPane.showMessageDialog(null, "Zag³osowano!");
						frame.dispose();
					}
				});
				sidePanel.add(button);
			}
			
			frame.add(sidePanel);
			frame.add(scrollPane);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean canUserVote(){
		Net.sendData("canUserVote");
		Net.sendData(Integer.toString(User.getUserID()));
		try {
			if(Integer.parseInt(Net.receiveData()) > 0)
				return false;
			else
				return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void vote(int electionsId, int electionsType){
		Vote.electionsId = electionsId;
		Vote.electionsType = electionsType;
		
		if(!canUserVote()){
			JOptionPane.showMessageDialog(null, "Ju¿ g³osowano w tych wyborach");
			return;
		}
		
		setNewFrame();

		if(electionsType <= 3)
			displayForm1();
		
		frame.revalidate();
		frame.repaint();
	}
}
