import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Catalogue extends JFrame {

	private JPanel contentPane;
	ArrayList<String>array= new ArrayList<>();
	DefaultListModel m=new DefaultListModel();
	private JTextField textField;
	private int cnt=0;
	private JTextField textField_1;
	
public void addYourProducts(Sellers x,String s) throws Exception{
	String url="jdbc:mysql://localhost:3306/studentcart";
	String uname="root";
	String password="Qwerty@1";
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,password);
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery("select * from category where name="+"'"+s+"'");
	int y=0;
	while(rs.next())
	y=rs.getInt("id");
	System.out.println(y);
	Product pdct=new Product(x,y);
	pdct.setVisible(true);
	setVisible(false);
	st.close();
	con.close();
	
}
public void LogOut() {
	StartingPage strt=new StartingPage();
	strt.frame.setVisible(true);
	setVisible(false);
}

	public void addCategory(Sellers xo) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from category");
		while(rs.next())
			cnt=rs.getInt("id");
		cnt++;
		String cat=textField.getText();
		String des=textField_1.getText();
		if(cat.compareTo("")==0) {
			JOptionPane.showMessageDialog(null,"Category Name should not be empty!!!");
		}
		else {
		int x=cnt;
		Category c=new Category();
		c.setId(x);
		c.setName(cat);
		c.setDes(des);
		st.executeUpdate("insert into category (id,name,description) values ("+Integer.toString(cnt)+",'"+cat+"','"+des+"')");
		Catalogue p=new Catalogue(xo);
		p.setVisible(true);
		setVisible(false);
		st.close();
		con.close();}
	}
	
	
	
	public Catalogue(Sellers x) throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    contentPane.setLayout(null);
	    JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
	    lblNewLabel.setBounds(24, 22, 154, 36);
	    lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      contentPane.add(lblNewLabel);
	
	    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(162, 205, 213, 178);
	      contentPane.add(scrollPane);
	      
	      JList lt = new JList();
	      lt.setModel(m);
	      ResultSet rs=st.executeQuery("select * from category");
	      while(rs.next()) {
	    	  m.addElement(rs.getString("name"));
	      }
	      
	      lt.setFont(new Font("SansSerif", Font.PLAIN, 20));
	      scrollPane.setViewportView(lt);
	      
	      textField = new JTextField();
	      textField.setBounds(210, 59, 223, 36);
	      contentPane.add(textField);
	      textField.setColumns(10);
	      
	      JButton btnNewButton = new JButton("Add");
	      btnNewButton.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		try {
					addCategory(x);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	      	}
	      });
	      btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
	      btnNewButton.setBounds(187, 164, 116, 30);
	      contentPane.add(btnNewButton);
	      
	      JLabel lblNewLabel_1 = new JLabel("Category Name");
	      lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      lblNewLabel_1.setBounds(50, 69, 116, 26);
	      contentPane.add(lblNewLabel_1);
	      
	      JLabel lblNewLabel_2 = new JLabel("Description");
	      lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      lblNewLabel_2.setBounds(50, 116, 116, 26);
	      contentPane.add(lblNewLabel_2);
	      
	      textField_1 = new JTextField();
	      textField_1.setBounds(210, 106, 223, 36);
	      contentPane.add(textField_1);
	      textField_1.setColumns(10);
	      
	      JButton btnNewButton_1 = new JButton("LogOut");
	      btnNewButton_1.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		LogOut();
	      	}
	      });
	      btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
	      btnNewButton_1.setBounds(427, 11, 109, 30);
	      contentPane.add(btnNewButton_1);
	      
	      lt.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					return;
				}
				String SelectedCategory=(String) lt.getSelectedValue();
				try {
					addYourProducts(x,SelectedCategory);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public void showAllCategory() throws Exception {
		String url="jdbc:mysql://localhost:3306/studentcart";
		String uname="root";
		String password="Qwerty@1";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from category");
		while(rs.next()) {
			m.addElement(rs.getString("name"));
		}
	}

	public void LogOut2() {
		StartingPage strt=new StartingPage();
		strt.frame.setVisible(true);
		setVisible(false);
		
	}

	public void chooseCategory(Customers x,String y) {
		Product cpl;
		try {
			cpl = new Product(x,y);
			cpl.setVisible(true);
			setVisible(false);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	public void trackOrders(Customers x) {
		Orders ts=new Orders(x);
		setVisible(false);
		ts.setVisible(true);
		
	}
	public Catalogue(Customers x) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 667, 531);
			contentPane = new JPanel();
			contentPane.setBackground(Color.CYAN);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Welcome "+x.getName());
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblNewLabel.setBounds(31, 22, 191, 31);
			contentPane.add(lblNewLabel);
			
			JList list = new JList();
			list.setModel(m);
			try {
				showAllCategory();
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.setBounds(210, 135, 191, 195);
			
			 JScrollPane scrollPane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			    scrollPane.setBounds(154, 111, 305, 307);
			      contentPane.add(scrollPane);
			
			JLabel lblNewLabel_1 = new JLabel("Select Your Item Category");
			lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(154, 64, 214, 36);
			contentPane.add(lblNewLabel_1);
			
			JButton btnNewButton = new JButton("LogOut");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LogOut2();
				}
			});
			btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnNewButton.setBounds(535, 22, 95, 29);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Track Order");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trackOrders(x);
				}
			});
			btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnNewButton_1.setBounds(381, 22, 124, 29);
			contentPane.add(btnNewButton_1);
			  list.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if(e.getValueIsAdjusting()) {
							return;
						}
						String SelectedCategory=(String) list.getSelectedValue();
						try {
							chooseCategory(x,SelectedCategory);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		}

}
