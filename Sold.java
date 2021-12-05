import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JButton;

public class Sold extends JFrame {

	private JPanel contentPane;
	DefaultListModel m=new DefaultListModel();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sold frame = new Sold(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Sold(Sellers x)throws Exception  {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from products where owner='"+x.getName()+"' "+"and "+"stats='SOLD'");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList lis = new JList();
		lis.setBounds(105, 52, 142, 110);
		lis.setModel(m);
		while(rs.next()) {
			String pdt=rs.getString("productName");
			m.addElement(pdt);
		}
		contentPane.add(lis);
		
	      
	      JButton btnNewButton = new JButton("Back");
	      btnNewButton.setBounds(25, 11, 89, 23);
	      contentPane.add(btnNewButton);
	}
}
