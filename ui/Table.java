package ktpm.ui;

import java.awt.BorderLayout;
import java.sql.Statement;
import java.awt.Container;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class Table  extends JFrame {
	DefaultTableModel dtm;
	JTable tblCorona;
	JButton btnThem, btnXoa;
	Connection con;
	Statement stm ;

	public Table(String tieude) {
		super("Table");
		addControlls();
		getDB();

	}
	public void addControlls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		con.add(pnMain);
		//pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		JPanel pnTable = new JPanel();
		dtm = new DefaultTableModel();
		dtm.addColumn("STT");
		dtm.addColumn("Họ và tên");
		dtm.addColumn("MSSV");
		tblCorona = new JTable(dtm);
		JScrollPane scTable = new JScrollPane(tblCorona
				,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS 
			
				,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMain.setLayout(new BorderLayout());
		pnMain.add(scTable, BorderLayout.CENTER);
		
	}
	public void getDB() {
        
        try {
            String dbURL = "jdbc:mysql://localhost/ktmp";
            String username = "root";
            String password = "";
            
            Connection conn =(Connection) DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            
            String sql = "select * from sinhvien";
            
            Statement st = (Statement) conn.createStatement();
            
            ResultSet rs =   st.executeQuery(sql);            

            dtm.setRowCount(0);
            while (rs.next()) {
            Vector<Object>vec = new Vector<Object>();
            vec.add(rs.getInt("id"));
            vec.add(rs.getString("ten"));
            vec.add(rs.getInt("mssv"));
            dtm.addRow(vec);
            
        	}
        
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void showWindowns() {
		this.setSize(600,400);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
