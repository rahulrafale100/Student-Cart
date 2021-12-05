import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartingPage {

	JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingPage window = new StartingPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	  //Create the application.
	 
	public StartingPage() {
		initialize();
	}

	
	 //Initialize the contents of the frame.
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("StudentCart");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblNewLabel.setBounds(191, 40, 178, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				OptionPage p1=new OptionPage("Register");
				p1.setVisible(true);
			}
			
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton.setBounds(102, 186, 136, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				OptionPage p1=new OptionPage("Login");
				p1.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton_1.setBounds(321, 186, 136, 50);
		frame.getContentPane().add(btnNewButton_1);
		frame.setBounds(100, 100, 579, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
