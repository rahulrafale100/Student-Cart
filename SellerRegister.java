import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellerRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private int cnt=0;
	private JTextField textField_5;
	private String naam;
	private String mobile;
	private String emlid;
	private String usrname;
	private String pswd;

	

	public SellerRegister(Sellers x) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 572);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seller Registration");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel.setBounds(197, 29, 228, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(109, 111, 88, 27);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(280, 105, 222, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(109, 290, 106, 27);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(280, 284, 222, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(109, 351, 88, 27);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(280, 352, 222, 33);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 naam=textField.getText();
				 emlid=textField_3.getText();
				 mobile=textField_5.getText();
				 usrname=textField_1.getText();
				pswd=textField_2.getText();
				ResultSet rs = null;
				try {
					 rs=st.executeQuery("select id from users_details");
				} catch (SQLException e2) {
					
					e2.printStackTrace();
				}
				if(emlid.contains("@nitc.ac.in")) {
					try {
						while(rs.next())
							try {
								String k=rs.getString("id");
								cnt=Integer.parseInt(k);
							} catch (NumberFormatException e2) {
								
								e2.printStackTrace();
							} catch (SQLException e2) {
								
								e2.printStackTrace();
							}
					} catch (SQLException e2) {
						
						e2.printStackTrace();
					}
					cnt++;
String query="insert into users_details (id,name,mobile,emailid,username,password) values ("+Integer.toString(cnt)+",'"+naam+"','"+mobile+"','"+emlid+"','"+usrname+"','"+pswd+"')";
					try {
						st.executeUpdate(query);
						st.close();
						con.close();
						x.setId(cnt);
						x.setName(naam);
						x.setEmailid(emlid);
						x.setmobileNo(mobile);
						x.setUserName(usrname);
						x.setPassword(pswd);
						try {
							Catalogue pd=new Catalogue(x);
							pd.setVisible(true);
							setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Please Use NITC mail id");
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnNewButton.setBounds(280, 408, 185, 42);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("EmailID");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(109, 223, 88, 27);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(280, 217, 222, 33);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mobile");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(109, 160, 88, 32);
		contentPane.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(279, 159, 222, 33);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
	
}
}
