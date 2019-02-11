package gui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import main.Config;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ElectoralCommisionMemberWindow extends UserWindow {
	private JButton btnSprawdListWyborcw;
	private JButton btnSporzdProtokGosowania;
	private JLabel lblCzonekKomisjiWyborczej;

	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficerWindow window = new OfficerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ElectoralCommisionMemberWindow() {
		initialize();
	}

	@Override
	public void initialize() {
		btnSprawdListWyborcw = new JButton("Sprawd\u017A list\u0119 wyborc\u00F3w");
		btnSprawdListWyborcw.setBounds(10, 266, 209, 23);
		super.panel.add(btnSprawdListWyborcw);
		
		btnSporzdProtokGosowania = new JButton("Sporz\u0105d\u017A protok\u00F3\u0142 g\u0142osowania");
		btnSporzdProtokGosowania.setBounds(10, 300, 209, 23);
		super.panel.add(btnSporzdProtokGosowania);
		
		lblCzonekKomisjiWyborczej = new JLabel("Cz�onek komisji wyborczej");
		lblCzonekKomisjiWyborczej.setHorizontalAlignment(SwingConstants.CENTER);
		lblCzonekKomisjiWyborczej.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCzonekKomisjiWyborczej.setBounds(10, 11, 209, 33);
		super.panel.add(lblCzonekKomisjiWyborczej);
	}
}
