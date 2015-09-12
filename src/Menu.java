import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(78, 233, 215, 34);
		contentPane.add(btnPlay);
		btnPlay.addActionListener(this);
		btnPlay.setActionCommand("Play");
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(78, 291, 215, 34);
		contentPane.add(btnOptions);
		btnOptions.addActionListener(this);
		btnOptions.setActionCommand("Options");
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(78, 350, 215, 34);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);
		btnExit.setActionCommand("Exit");
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
