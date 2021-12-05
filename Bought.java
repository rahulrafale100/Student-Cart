import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;

public class Bought extends JFrame {

	private JPanel contentPane;
	DefaultListModel m=new DefaultListModel();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Bought frame = new Bought(null);
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
	public Bought(Customers x) throws Exception  {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from users_details where name='"+x.getName()+"'");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		rs.next();
		int k=rs.getInt("id");
		Statement sp=con.createStatement();
		ResultSet rp=sp.executeQuery("select * from products where buyerid='"+Integer.toString(k)+"'");
		
		JList list = new JList();
		list.setBounds(102, 56, 149, 153);
		list.setModel(m);
		while(rp.next()) {
			String pu=rp.getString("productName");
			m.addElement(pu);
		}
		contentPane.add(list);
	}

}
