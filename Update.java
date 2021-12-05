import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Update extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String naam;
	private String price;
	private String des;
	int pid;
public void updateIt(String a,String b,String c,Sellers  x,int z,int k)throws Exception {
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	if(a.compareTo("")==0||b.compareTo("")==0||c.compareTo("")==0) {
		JOptionPane.showMessageDialog(null,"Please fill up all details!!!");
	}
	else {
	st.executeUpdate("update products set productName='"+a+"',price='"+b+"', details='"+c+"' "+"where id="+Integer.toString(k));
	Product pd=new Product(x,z);
	pd.setVisible(true);
	setVisible(false);
	}
}
public void goBack(Sellers x,int y) {
	try {
		Product pd=new Product (x,y);
		pd.setVisible(true);
		setVisible(false);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	public Update(Sellers x,int y,String z) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where owner='"+x.getName()+"'");
		while(rs.next()) {
			String ps=rs.getString("productName");
			if(z.contains(ps)) {
			naam=rs.getString("productName");
			price=rs.getString("price");
			pid=rs.getInt("id");
			des=rs.getString("details");
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 464);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(x,y);
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Product Name :");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(73, 63, 115, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price(in Rs) :");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(73, 113, 105, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(73, 167, 89, 30);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(188, 60, 318, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(naam);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 110, 318, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(price);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 164, 318, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(des);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateIt(textField.getText(),textField_1.getText(),textField_2.getText(),x,y,pid);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnNewButton_1.setBounds(207, 257, 174, 38);
		contentPane.add(btnNewButton_1);
	}
}
