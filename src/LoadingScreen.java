import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.SystemColor;

/**
 * @ Description: loadingScreen class, displays when user is loading up game
 * @ Author: Ryan Wang
 * @ Version: v1.0
 * June 14th, 2015
 */

public class LoadingScreen extends JFrame implements ActionListener, PropertyChangeListener {

	//Variables needed
	private JPanel contentPane;
	private Task Task;
	private JProgressBar progressBar = new JProgressBar();
	private JButton btnStart = new JButton("Start");
	private int menu;

	private Square[] objectArray = new Square[10];

	//Opens frame
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame = new LoadingScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor for frame
	public LoadingScreen() {
		setTitle("Loading...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblLoading = new JLabel("Loading...");
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblLoading.setBounds(114, 21, 196, 60);
		contentPane.add(lblLoading);

		//Creation of buttons
		JButton btnMenu = new JButton("Menu");
		btnMenu.setForeground(Color.BLUE);
		btnMenu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnMenu.setBounds(73, 193, 112, 36);
		btnMenu.addActionListener(this);
		contentPane.setLayout(null);
		btnMenu.setActionCommand("Menu");
		contentPane.add(btnMenu);

		btnStart.setForeground(Color.BLUE);
		btnStart.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnStart.setEnabled(false);
		btnStart.setBounds(253, 193, 112, 36);
		btnStart.addActionListener(this);
		btnStart.setActionCommand("Start");
		contentPane.add(btnStart);

		for (int i = 0; i < objectArray.length; i++) {
			if (i == 0) {
				objectArray[i] = new Square(100, 100, "Right");
			}
			else {
				if (i < 4) {
					objectArray[i] = new Square((objectArray[i-1].getX() + objectArray[i-1].getWidth() + 5), objectArray[i-1].getY(), "Right");
				}
				else {
					objectArray[i] = new Square(objectArray[i-1].getX(), (objectArray[i-1].getY() + objectArray[i-1].getWidth() + 5), "Down");
				}
			}
		}



		//Runs the task
		Task = new Task();
		Task.addPropertyChangeListener(this);
		Task.execute();
	}

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */

		public Void doInBackground() {

			int progress = 0;
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//Loading bar will fill
			while (progress < 10000) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}

				progress += 10;
				this.paint(Graphics g);

			}
			return null;
		}
		
		public void paint(Graphics g) {
			for (int i = 0; i < objectArray.length; i++) {
				objectArray[i].paint(g);
			}
		}

		/*
		 * Executed in event dispatching thread
		 */

		public void done() {
			btnStart.setEnabled(true);
			setCursor(null); //turn off the wait cursor
		}
	}


	public void propertyChange(PropertyChangeEvent evt) {

	}

	//Method that is called when buttons are pressed
	public void actionPerformed(ActionEvent evt) {
		//Calls the different frames based on whichever action is called
		if (evt.getActionCommand() .equals ("Menu")) {
			menu = JOptionPane.showConfirmDialog(null, "Are you sure you want to return to menu?");
			if (menu == 0) {
				this.dispose();
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
			}
		}
		if (evt.getActionCommand() .equals("Start")) {
			this.dispose();
			GameFrame gameOuterFrame = new GameFrame();
			gameOuterFrame.setVisible(true);
		}
	}//End actionPerformed method


	///////////////////////////////////////////////////////////


}//End loadingScreen class

//Class for square objects

class Square {

	private int xPosition;
	private int yPosition;
	private int width = 20;
	private int height = 20;
	private String direction;

	Square(int x, int y, String d) {
		xPosition = x;
		yPosition = y;
		direction = d;
	}

	//Method for getting xPosition
	public int getX() {
		return xPosition;
	}//End getX method

	//Method for getting yPosition
	public int getY() {
		return yPosition;
	}//End getY position


	public int getWidth() {
		return width;
	}//End getX method

	public void moveSquare() {

	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(xPosition, yPosition, width, height);
	}

}

