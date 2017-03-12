package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ChangeSupplier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ChangeSupplier frame = new ChangeSupplier(suppinfo1,xz2);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeSupplier(SupplierInfo suppinfo,int xz2) {
		setTitle("修改供应商");
		JPanel imagePanel;
		ImageIcon backImageIcon=new ImageIcon("背景3.jpg");
		JLabel imageLabel=new JLabel(backImageIcon);
		imageLabel.setBounds(0,0,backImageIcon.getIconWidth(),backImageIcon.getIconHeight());
		imagePanel=(JPanel)this.getContentPane();
		this.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE));
		imagePanel.setOpaque(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(68, 10, 159, 21);
		contentPane.add(textField);
		
		JLabel label = new JLabel("供应商");
		label.setBounds(22, 13, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("供应商品");
		label_1.setBounds(10, 52, 54, 18);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(68, 51, 159, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(68, 94, 159, 21);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("价格");
		label_2.setBounds(22, 97, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("联系方式");
		label_3.setBounds(10, 139, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(68, 136, 159, 21);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(68, 172, 159, 21);
		contentPane.add(textField_4);
		
		JLabel label_4 = new JLabel("取货地址");
		label_4.setBounds(10, 175, 54, 15);
		contentPane.add(label_4);
		
		JButton button = new JButton("修改");
		button.setBounds(10, 218, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();//将此页面退出，但整个程序不会结束
			}
			
		});
		button_1.setBounds(153, 218, 93, 23);
		contentPane.add(button_1);
		textField.setText((String)suppinfo.getValueAt(xz2, 0));
		textField.setEditable(false);
		textField_1.setText((String)suppinfo.getValueAt(xz2, 1));
		textField_2.setText((String)suppinfo.getValueAt(xz2, 2));
		textField_3.setText((String)suppinfo.getValueAt(xz2, 3));
		textField_4.setText((String)suppinfo.getValueAt(xz2, 4));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/cangkuguanli";
					String userString="root";
					String passwString="leikewei000";
					connection=DriverManager.getConnection(url,userString,passwString);
					statement=connection.createStatement();
					String sql="update supplierinfo set tradename='"+textField_1.getText()+"',"+"tradeprice='"+textField_2.getText()+"',"+"contact='"+
					textField_3.getText()+"',"+"address='"+textField_4.getText()+"'where name='"+textField.getText()+"'";
					statement .executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "修改成功");
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "修改失败");
				ex.printStackTrace();
			}
			}
			});
		
		this.setVisible(true);
		contentPane.setOpaque(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);//将程序设置为点击退出，只是本界面退出，而不是整个程序退出
		this.setLocation(600, 300);
		this.setResizable(false);
	}

}
