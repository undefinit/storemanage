package ckgl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Detail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JLabel label_5;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Detail frame = new Detail();
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
	public Detail(ProductInfo proinfo1,int xz) {
		setTitle("查看详情");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 236, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("产品编号");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(74, 7, 142, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("产品名称");
		label_1.setBounds(10, 41, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(74, 38, 142, 21);
		contentPane.add(textField_1);
		
		label_2 = new JLabel("产品价格");
		label_2.setBounds(10, 76, 54, 15);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(74, 73, 142, 21);
		contentPane.add(textField_2);
		
		label_3 = new JLabel("产品库存");
		label_3.setBounds(10, 110, 54, 15);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(74, 107, 142, 21);
		contentPane.add(textField_3);
		
		label_4 = new JLabel("产品类别");
		label_4.setBounds(10, 148, 54, 15);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(74, 145, 142, 21);
		contentPane.add(textField_4);
		
		label_5 = new JLabel("供货商 ：");
		label_5.setBounds(10, 184, 54, 15);
		contentPane.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(74, 181, 142, 21);
		contentPane.add(textField_5);
		textField.setText((String)proinfo1.getValueAt(xz,0));
		textField_1.setText((String)proinfo1.getValueAt(xz, 1));
		textField_2.setText((String)proinfo1.getValueAt(xz, 2));
		textField_3.setText((String)proinfo1.getValueAt(xz, 3));
		textField_4.setText((String)proinfo1.getValueAt(xz, 4));
		textField_5.setText((String)proinfo1.getValueAt(xz, 5));
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		textField_5.setEditable(false);
		this.setVisible(true);
		this.setLocation(600, 380);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}
}
