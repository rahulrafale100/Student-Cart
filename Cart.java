import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cart extends JFrame {

	private JPanel contentPane;
	DefaultListModel m=new DefaultListModel();
	
public void showCartList(Customers x) throws Exception {
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from carts where customerid="+Integer.toString(x.getId()));
	while(rs.next()) {
		int k=rs.getInt("productid");
		Statement sk=con.createStatement();
		ResultSet as=sk.executeQuery("select * from products where id="+Integer.toString(k));
		while(as.next())
		m.addElement(as.getString("productName"));
	}
}
public void clearCart(Customers x, String y) throws Exception {
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	st.executeUpdate("delete from carts where customerid="+Integer.toString(x.getId()));
	setVisible(false);
	Cart ty=new Cart(x,y);
	ty.setVisible(true);
}
public void placeOrder(Customers x,String y) throws Exception{
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from carts where customerid="+Integer.toString(x.getId()));
	Statement su=con.createStatement();
	ResultSet ru=su.executeQuery("select count(*) from carts");
	String pattern = "MM/dd/yyyy HH:mm:ss";
	DateFormat df = new SimpleDateFormat(pattern);
	Date today = Calendar.getInstance().getTime(); 
	String td= df.format(today);
	int ko=-1;
	if(ru.next()) {
		 ko=ru.getInt("count(*)");
	}
	if(ko!=0) {
	while(rs.next()) {
		Statement sk=con.createStatement();
		int t=rs.getInt("productid");
		sk.executeUpdate("update products set ordDate='"+td+"',"+"stats='SOLD',buyerid="+Integer.toString(x.getId())+" where id="+Integer.toString(t));
	}
	Statement sp=con.createStatement();
	sp.executeUpdate("delete from carts where customerid="+x.getId());
	JOptionPane.showMessageDialog(null,"Order Placed ");
	setVisible(false);
	Catalogue cy=new Catalogue(x);
	cy.setVisible(true);}
	else {
		JOptionPane.showMessageDialog(null,"Cart is empty!!!");
	}
}
	public Cart(Customers x,String y) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 517);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello "+x.getName());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 102, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Here is your Choosed items");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(129, 86, 195, 33);
		contentPane.add(lblNewLabel_1);
		
		JList list = new JList();
		list.setModel(m);
		try {
			showCartList(x);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		JScrollPane scrollPane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(66, 130, 305, 294);
	      contentPane.add(scrollPane);
	      
	      JButton btnNewButton = new JButton("Add more Items");
	      btnNewButton.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		try {
					Product frm=new Product(x,y);
					frm.setVisible(true);
					setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	      	}
	      });
	      btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      btnNewButton.setBounds(296, 11, 188, 40);
	      contentPane.add(btnNewButton);
	      
	      JButton btnNewButton_1 = new JButton("Clear Cart");
	      btnNewButton_1.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		try {
					clearCart(x,y);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	      	}
	      });
	      btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      btnNewButton_1.setBounds(423, 107, 111, 33);
	      contentPane.add(btnNewButton_1);
	      
	      JButton btnNewButton_2 = new JButton("Place Order");
	      btnNewButton_2.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		try {
					placeOrder(x,y);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	      	}
	      });
	      btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      btnNewButton_2.setBounds(423, 202, 123, 33);
	      contentPane.add(btnNewButton_2);
	}
}
