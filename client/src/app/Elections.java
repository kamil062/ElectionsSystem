package app;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import net.Net;

public class Elections {
	public static DefaultTableModel getElections(){
		Net.sendData("electionsList");
		try {
			int size = Integer.parseInt(Net.receiveData());
			
			Object[][] obj = new Object[size][5];
			
			for(int i = 0; i < size; i++){
				obj[i][0] = Net.receiveData();
				
				switch(Integer.parseInt(Net.receiveData())){
					default:
					case 1:
						obj[i][1] = "Wybory prezydenckie";
						break;
					case 2:
						obj[i][1] = "Wybory do sejmu";
						break;
					case 3:
						obj[i][1] = "Wybory do senatu";
						break;
					case 4:
						obj[i][1] = "Referendum";
						break;
				}
				
				obj[i][2] = Net.receiveData();
				obj[i][3] = Net.receiveData();	
				
				Object tmp = Net.receiveData();
				if(((String) tmp).compareTo((String) obj[i][2]) < 0 && ((String) tmp).compareTo((String) obj[i][3]) < 0)
					obj[i][4] = "Przysz³e";
				if(((String) tmp).compareTo((String) obj[i][2]) > 0 && ((String) tmp).compareTo((String) obj[i][3]) < 0)
					obj[i][4] = "Trwaj¹ce";
				if(((String) tmp).compareTo((String) obj[i][2]) > 0 && ((String) tmp).compareTo((String) obj[i][3]) > 0)
					obj[i][4] = "Zakoñczone";
			}
			
			return new DefaultTableModel(
					obj,
					new String[] {
						"Lp.", "Typ", "Data rozpoczêcia", "Data zakoñczenia", "Stan"
					}
				);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JPanel getMore(int electionsId){
		JPanel panel = new JPanel();

		JLabel label1 = new JLabel();
		label1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label1.setBounds(10, 10, 400, 20);
		
		JLabel label2 = new JLabel();
		label2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label2.setBounds(10, 25, 400, 20);
		
		JLabel label3 = new JLabel();
		label3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label3.setBounds(10, 40, 400, 20);
		
		JLabel label4 = new JLabel();
		label4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label4.setBounds(10, 55, 400, 20);
		
		JLabel label5 = new JLabel();
		label5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label5.setBounds(10, 70, 400, 20);
	
		
		Net.sendData("getInfoAboutElections");
		Net.sendData(Integer.toString(electionsId));
		try {
			label1.setText("Id: " + Net.receiveData());
			
			int electionsType = Integer.parseInt(Net.receiveData());
			switch(electionsType){
				default:
				case 1:
					label2.setText("Typ: wybory prezydenckie");
					break;
				case 2:
					label2.setText("Typ: wybory do sejmu");
					break;
				case 3:
					label2.setText("Typ: wybory do senatu");
					break;
				case 4:
					label2.setText("Typ: referendum");
					break;
			}
			
			String dateStarted = Net.receiveData();
			String dateEnded = Net.receiveData();
			label3.setText("Data rozpoczêcia: " + dateStarted);
			label4.setText("Data zakoñczenia: " + dateEnded);
		
			Object tmp = Net.receiveData();
			if(((String) tmp).compareTo(dateStarted) < 0 && ((String) tmp).compareTo(dateEnded) < 0)
				label5.setText("Stan: przysz³e");
			if(((String) tmp).compareTo(dateStarted) > 0 && ((String) tmp).compareTo(dateEnded) < 0) {
				label5.setText("Stan: trwaj¹ce");

				JButton button1 = new JButton("Oddaj g³os");
				button1.setBounds(10, 100, 200, 20);
				button1.addActionListener(new ActionListener() {
					private int i1 = electionsId; 
					private int i2 = electionsType;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Vote.vote(i1, i2);
					}
				});
				panel.add(button1);
				
				JButton button2 = new JButton("SprawdŸ aktualne wyniki");
				button2.setBounds(10, 130, 200, 20);
				panel.add(button2);
			}
			if(((String) tmp).compareTo(dateStarted) > 0 && ((String) tmp).compareTo(dateEnded) > 0) {
				label5.setText("Stan: zakoñczone");
				
				JButton button = new JButton("Zobacz wyniki");
				button.setBounds(10, 100, 200, 20);
				panel.add(button);
			}
			
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}

		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.setLayout(null);
		
		return panel;
	}
}
