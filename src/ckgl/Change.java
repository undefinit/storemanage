package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Change extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change frame = new Change();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Change() {
		setTitle("密码修改");
		JPanel imagePanel;
		ImageIcon backImageIcon=new ImageIcon("背景2.jpg");
		JLabel imageLabel=new JLabel(backImageIcon);
		imageLabel.setBounds(0,0,backImageIcon.getIconWidth(),backImageIcon.getIconHeight());
		imagePanel=(JPanel)this.getContentPane();
		this.getLayeredPane().add(imageLabel,new Integer(Integer.MIN_VALUE));
		imagePanel.setOpaque(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(26, 13, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(98, 10, 180, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("原始密码");
		label_1.setBounds(26, 41, 68, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 43, 180, 21);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("新密码");
		label_2.setBounds(26, 74, 68, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(98, 71, 180, 21);
		contentPane.add(textField_2);
		
		JLabel label_3 = new JLabel("确认密码");
		label_3.setBounds(26, 101, 68, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(98, 102, 180, 21);
		contentPane.add(textField_3);
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_2.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "请输入你要修改的密码");
					return;
				}else {
					java.sql.Connection connection=null;
					java.sql.Statement statement=null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String url="jdbc:mysql://localhost:3306/cangkuguanli";
						String userString="root";
						String passwString="leikewei000";
						connection=DriverManager.getConnection(url,userString,passwString);
						statement=connection.createStatement();
						String sql="select * from mima where user='"+textField.getText()+"' and password='"+textField_1.getText()+"'";
						ResultSet resultSet=statement .executeQuery(sql);
						/**
						 * 遍历查询
						 */
						if (resultSet.next()) {
							if (textField_2.getText().equals(textField_3.getText())) {
								String sql2="update mima set password='"+textField_3.getText()+"'where user='"+textField.getText()+"'";
								statement.executeUpdate(sql2);
								JOptionPane.showMessageDialog(null, "修改成功");
							}else {
								JOptionPane.showMessageDialog(null, "前后密码不一致", "警告", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}else{
							JOptionPane.showMessageDialog(null, "用户名或密码错误");
						}
						statement.close();
						connection.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(34, 158, 93, 23);
		contentPane.add(btnNewButton);
		
		button = new JButton("取消");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(185, 158, 93, 23);
		contentPane.add(button);
		this.setVisible(true);
		contentPane.setOpaque(false);
		this.setLocation(620, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

}
