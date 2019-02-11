package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

import main.Config;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import app.Login;

public class LoginWindow implements Window{
	private JFrame frame;
	private JTextField PESELTextField;
	private JLabel PESELLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordTextField;


	@Override
	public boolean isRunning() {
		return this.getFrame().isEnabled();
	}
	
	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWindow() {
		initialize();
	}

	@Override
	public void initialize() {
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
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(270, 110));
		panel.setMaximumSize(new Dimension(270, 110));
		panel.setMinimumSize(new Dimension(270, 110));
		
		PESELTextField = new JTextField();
		PESELTextField.setText("94102011110");
		PESELTextField.setBounds(107, 11, 150, 20);
		panel.add(PESELTextField);
		PESELTextField.setColumns(10);
		
		PESELLabel = new JLabel("PESEL");
		PESELLabel.setBounds(10, 14, 87, 14);
		panel.add(PESELLabel);
		
		passwordLabel = new JLabel("Has\u0142o");
		passwordLabel.setBounds(10, 45, 87, 14);
		panel.add(passwordLabel);
		
		JButton loginButton = new JButton("Zaloguj");
		loginButton.setBounds(107, 76, 150, 23);
		loginButton.addActionListener(new ActionListener() {
			private boolean ret;

			public void actionPerformed(ActionEvent arg0) {
				ret = Login.login(PESELTextField.getText(), passwordTextField.getPassword());
				if(ret == true)
					close();
			}
		});
		frame.getContentPane().setLayout(new MigLayout("", "[1264px]", "[682px]"));
		panel.add(loginButton);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(107, 42, 150, 20);
		panel.add(passwordTextField);
		frame.getContentPane().add(panel, "cell 0 0,alignx center,aligny center");
	}
	
	public void close(){
		this.getFrame().dispose();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
