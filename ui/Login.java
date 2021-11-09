package ktpm.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ktpm.test.Main;


public class Login extends JFrame {
	JPasswordField jptPassword;
	JButton btnLogin, btnResset;
	JTextField txtUser;
	public  Login(String tieude) {
		super("Login");
		addControlls();
		addEvents();
	}
	public void addControlls() {
		Container con = getContentPane();
		//JPanel pnLog = new JPanel();
		
		JPanel pnMain = new JPanel();
		con.add(pnMain);
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		//username
		JPanel pnUser = new JPanel();
		
		pnUser.setLayout(new FlowLayout());
	//	pnUser.setBackground(Color.green);
		JLabel lblUser = new JLabel("Username");
		 txtUser = new JTextField(20);
		pnMain.add(pnUser);
		pnUser.add(lblUser);
		pnUser.add(txtUser);
		
		JPanel pnLogin = new JPanel();
		//pnLogin.setBackground(Color.green);
		pnLogin.setLayout(new FlowLayout() );
		JLabel lblPassword = new JLabel("PassWord");
		 jptPassword = new JPasswordField(20);
		
		
		//pnMain.add(lblLg);
		pnLogin.add(lblPassword);
		pnLogin.add(jptPassword);
		pnMain.add(pnLogin);
		
		JPanel pnCheck = new JPanel();
		JCheckBox check = new JCheckBox("Remember password?");
		//JLabel lblRemember = new JLabel("Remember password?");
		pnCheck.add(check);
		//pnRemember.add(lblRemember);
		pnMain.add(pnCheck);
		
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout() );
		 btnLogin = new JButton("Login");
		 btnResset = new JButton("Resset");
		pnButton.add(btnLogin);
		pnButton.add(btnResset);
		pnMain.add(pnButton);
		
		Border borderLogin = BorderFactory.createLineBorder(Color.blue);
		TitledBorder borderTitleLogin = new TitledBorder(borderLogin, "Corona Virus");
		pnMain.setBorder(borderTitleLogin);
		
		
	}
	public void addEvents() {
		btnLogin.addActionListener(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XuLiDangNhap();
			}
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
		btnResset.addActionListener(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtUser.setText("");
				jptPassword.setText("");
			}
			
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
	}
		
	public void XuLiDangNhap() {
			Connection conn = null;
	        Statement st = null;
	        ResultSet rs = null;
	        String username = "root";
	        String password = "";
	        String UN, PW;
	        UN = txtUser.getText();
	        PW = jptPassword.getText();
	        StringBuilder sb = new StringBuilder();
	        if(UN.equals("")) {
	        	sb.append("Username is empty \n");
	        }
	        if(PW.equals("")) {
	        	sb.append("Password is empty \n");
	        }
	        if(sb.length() > 0) {
	        	JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
	        	return;
	        }
        try {
             conn =(Connection) DriverManager.getConnection(Main.dbURL,username,password);       
            String sql = "select tenDN,matKhau from login where tenDN = '"+UN+"' and matKhau ='"+PW+"' ";
             st = (Statement) conn.createStatement();
             rs =   st.executeQuery(sql); 
            if (rs.next()) {  
            Main.sTenDN = rs.getString("tenDN");
            Main.sMatKhau = rs.getString("matKhau");
            if(Main.sTenDN.equals(rs.getString("tenDN")) && Main.sMatKhau.equals(rs.getString("matKhau")) ) {
            	dispose();
	            Table table = new Table("Table");
	            table.setExtendedState(JFrame.MAXIMIZED_BOTH);    
	            table.showWindowns();
            }      
        }  else {
        	JOptionPane.showMessageDialog(this, "Ivalidation username or password", "Failure ", JOptionPane.ERROR_MESSAGE);	
        }       
        }   
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void showWindowns() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
