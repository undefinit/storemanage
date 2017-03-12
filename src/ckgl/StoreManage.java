package ckgl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.java.swing.plaf.windows.resources.windows;

import sun.awt.image.ImageWatched.Link;
import sun.util.logging.resources.logging;


public class StoreManage extends JFrame implements ActionListener{
	JTable bgTable,bg2Table,bg3Table;
	JPanel ProductManage;
	ProductInfo  proinfo1;
	SupplierInfo suppinfo1;
	ViewInfo viewinfo1;
	int combo;
	Timer timeAction;
	String ww;

	public StoreManage(){
		//InitGlobalFont(new Font("华文行楷", Font.PLAIN, 12));
		//JLabel time=new JLabel();
		/**
		 * 设置菜单
		 */
		JMenuBar cdlBar=new JMenuBar();
		JMenu cdMenu=new JMenu("用户管理");
		JMenu cdMenu2=new JMenu("系统设置");
		JMenu cdMenu3=new JMenu("帮助");

		//帮助菜单设置
		JMenuItem help=new JMenuItem("联系作者");
		help.setAccelerator(KeyStroke.getKeyStroke("Ctrl"+'L'));
		help.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null , "作者QQ：741047261");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.browse(new URI("http://leikewei521.tk"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

		});
		//用户管理菜单设置
		JMenuItem zhuceItem=new JMenuItem("产品注册");
		zhuceItem.setMnemonic('Z');
		zhuceItem.setAccelerator(KeyStroke.getKeyStroke("Ctrl"+'Z'));
		JMenuItem adduser=new JMenuItem("添加用户");
		adduser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddUser add=new AddUser();
				//设置这个，窗口点关闭后，整个程序不会退出
				add.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}

		});
		JMenuItem changeItem=new JMenuItem("密码修改");
		changeItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Change change=new Change();
				change.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}

		});
		//系统设置菜单设置
		JMenuItem window=new JMenuItem("Window外观");
		window.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}	
			}

		});
		JMenuItem xitongItem=new JMenuItem("Nimbus外观");
		xitongItem.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

			}

		});
		JMenuItem winclassItem=new JMenuItem("WindowsClassic外观");
		winclassItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});
		cdMenu.add(adduser);cdMenu.add(changeItem);
		cdMenu2.add(window);cdMenu2.add(xitongItem);cdMenu2.add(winclassItem);
		cdMenu3 .add(help);cdMenu3.add(zhuceItem);
		cdlBar.add(cdMenu);cdlBar.add(cdMenu2);cdlBar.add(cdMenu3);


		/**
		 * 主页面
		 */


		JLabel hyLabel=new JLabel(new ImageIcon("hy2.jpg"));//欢迎图片
		JTabbedPane xxk=new JTabbedPane();
		JButton exitButton=new JButton("退出");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/**
		 * 产品管理页面
		 */

		//添加表格
		ProductManage=new JPanel();
		proinfo1=new ProductInfo();
		bgTable=new JTable(proinfo1);

		JScrollPane gdScrollPane=new JScrollPane(bgTable);
		//产品管理弹出菜单
		final JPopupMenu popupMenu=new JPopupMenu();
		JMenu jMenu=new JMenu("菜单");
		JMenuItem  jMenuItem[]={new JMenuItem("查看详情"),new JMenuItem("刷新"),new JMenuItem("添加商品"),
				new JMenuItem("产品进货"),new JMenuItem("产品出货"),new JMenuItem("退出")};
		for (int i = 0; i <2; i++) {
			popupMenu.add(jMenuItem[i]);
		}
		popupMenu.addSeparator();
		for (int i = 2; i <5 ; i++) {
			popupMenu.add(jMenuItem[i]);	
		}
		popupMenu.addSeparator();
		popupMenu.add(jMenuItem[5]);
		jMenuItem[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//查看详情
				int xz=bgTable.getSelectedRow();
				if (xz==-1) {
					JOptionPane.showMessageDialog(null, "请选中你想要查看的商品");
				}else {
					new Detail(proinfo1, xz);

				}
			}
		});
		jMenuItem[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//刷新
				proinfo1=new ProductInfo();
				bgTable.setModel(proinfo1);
			}
		});
		jMenuItem[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加商品
				new TianJia(); 

			}
		});
		jMenuItem[3].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//产品进货
				final int xz3=bgTable.getSelectedRow();
				if (xz3==-1) {
					JOptionPane.showMessageDialog(null, "你选中你要进货的商品");
					return;
				}
				new AddCargob(proinfo1, xz3);
				proinfo1=new ProductInfo();
				bgTable.setModel(proinfo1);
			}
		});
		jMenuItem[4].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// 产品出货
				int xz3=bgTable.getSelectedRow();
				if (xz3==-1) {
					JOptionPane.showMessageDialog(null, "请选中你要出货的商品");
					return;
				}else {
					new GoCargob(proinfo1,xz3);
				}

			}
		});
		//退出产品管理菜单
		jMenuItem[5].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(false);
			}
		});
		bgTable.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(true);
					popupMenu.show(e.getComponent(),e.getX(),e.getY());
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);

			}

		});
		//按钮组件
		JButton addButton=new JButton("添加产品");
		addButton.addActionListener(this);
		addButton.setActionCommand("tianjia");
		JButton deletButton=new JButton("删除产品");
		deletButton.addActionListener(this);
		deletButton.setActionCommand("shanchu");
		JButton addcargobButton=new JButton("产品进货");
		addcargobButton.addActionListener(this);
		addcargobButton.setActionCommand("jinhuo");
		JButton gocargoButton=new JButton("产品出货");
		gocargoButton.addActionListener(this);
		gocargoButton.setActionCommand("chuhuo");
		//将表格和按钮组合一起
		JPanel mb2=new JPanel();
		JPanel mb1=new JPanel();
		mb1.add(gdScrollPane);
		mb2.add(addButton);mb2.add(deletButton);mb2.add(addcargobButton);mb2.add(gocargoButton);
		//设置此选项卡的布局
		ProductManage.setLayout(new FlowLayout());
		ProductManage.add(mb1);ProductManage.add(mb2);
		ProductManage.setSize(450,500);
		ProductManage.setVisible(true);

		/**
		 * 供应商管理页面
		 */

		JPanel supplier=new JPanel();
		suppinfo1=new SupplierInfo();
		bg2Table=new JTable(suppinfo1);

		JScrollPane gd2=new JScrollPane(bg2Table);
		final JPopupMenu popupMenu2=new JPopupMenu();
		JMenu jMenu2=new JMenu("菜单");
		JMenuItem  jMenuItem2[]={new JMenuItem("查看详情"),new JMenuItem("刷新"),new JMenuItem("添加供货商"),
				new JMenuItem("修改供货商"),new JMenuItem("退出")};
		for (int i = 0; i <2; i++) {
			popupMenu2.add(jMenuItem2[i]);
		}
		popupMenu2.addSeparator();
		for (int i = 2; i <4 ; i++) {
			popupMenu2.add(jMenuItem2[i]);	
		}
		popupMenu2.addSeparator();
		popupMenu2.add(jMenuItem2[4]);
		/**
		 * 对菜单进行监听，实现其功能
		 */
		jMenuItem2[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//查看详情
				int xz2=bg2Table.getSelectedRow();
				if (xz2==-1) {
					JOptionPane.showMessageDialog(null, "请选中你要查看的供货商", "警告", JOptionPane.WARNING_MESSAGE);
				}else {
					new Detail2(suppinfo1, xz2);
				}

			}
		});
		jMenuItem2[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//刷新
				suppinfo1=new SupplierInfo();
				bg2Table.setModel(suppinfo1);
			}
		});
		jMenuItem2[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加供货商
				new addsupplier(); 

			}
		});
		jMenuItem2[3].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final int xz2=bg2Table.getSelectedRow();
				if (xz2==-1) {
					JOptionPane.showMessageDialog(null, "请选中你要删除的行");
					return;
				} else {
					new ChangeSupplier(suppinfo1, xz2);
				}
			}
		});
		//退出菜单
		jMenuItem2[4].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu2.show(false);
			}
		});
		/*
		 * 对表格中加监听弹出菜单
		 */
		bg2Table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu2.show(true);
					popupMenu2.show(e.getComponent(),e.getX(),e.getY());
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);

			}

		});


		//按钮组件
		JButton addsupplierButton=new JButton("添加供应商");
		addsupplierButton.addActionListener(this);
		addsupplierButton.setActionCommand("tianjiagonghuo");
		JButton deletesupplierButton=new JButton("删除供应商");
		deletesupplierButton.addActionListener(this);
		deletesupplierButton.setActionCommand("shanchugonghuo");
		JButton changesupplierButton=new JButton("修改供应商");
		changesupplierButton.addActionListener(this);
		changesupplierButton.setActionCommand("xiugaigonghuo");
		//将组件添加到面板上
		JPanel supplierPanel=new JPanel();
		supplierPanel.add(addsupplierButton);
		supplierPanel.add(deletesupplierButton);
		supplierPanel.add(changesupplierButton);
		supplier.add(gd2);
		supplier.add(supplierPanel,BorderLayout.SOUTH);

		/**
		 *  产品视图
		 * 
		 */
		JPanel view=new JPanel();
		viewinfo1=new ViewInfo();
		bg3Table=new JTable(viewinfo1);
		JScrollPane gd3=new JScrollPane(bg3Table);
		view.add(gd3);
		JLabel label=new JLabel("按：");
		String string[]={"编号","供货商","高于此价格","低于此价格"};
		final JComboBox<String> comboBox=new JComboBox<String>(string);
		final JTextField wriTextField=new JTextField(10);
		ww=wriTextField.getText();
		combo=comboBox.getSelectedIndex();	
		JButton exportButton=new JButton("导出数据");
		exportButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//导出数据到文本
				selectFromDb();
				JOptionPane.showMessageDialog(null, "导入成功");

			}
		});
		JButton lookButton=new JButton("查询");
		//对查询按钮加监听
		lookButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int combo=comboBox.getSelectedIndex();
				String ss=null;
				if (wriTextField.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "请输入你要查询的数据","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (combo==0) {
					ss="select * from productinfo where id='"+wriTextField.getText()+"'";
				}else if (combo==1) {
					ss="select * from productinfo where supplier='"+wriTextField.getText()+"'";
				}else if (combo==2) {
					//高于此价格
					int ii=Integer.parseInt(wriTextField.getText());
					ss="select * from productinfo where price>'"+ii+"'";
				}else if (combo==3) {
					//低于此价格
					int ii=Integer.parseInt(wriTextField.getText());
					ss="select * from productinfo where price<'"+ii+"'";
				}
				viewinfo1=new ViewInfo(ss);
				bg3Table.setModel(viewinfo1);
			}	
		});

		JPanel mb=new JPanel();
		mb.setLayout(new FlowLayout());
		mb.add(label);mb.add(comboBox);mb.add(wriTextField);mb.add(lookButton);mb.add(exportButton);
		view.add(mb,BorderLayout.SOUTH);

		/**
		 * 功能设置
		 */
		JPanel functionPanel=new JPanel();
		ImageIcon background=new ImageIcon("路飞小.jpg");
		JLabel image=new JLabel(background);
		JCheckBox remindBox=new JCheckBox("进货提醒          ");
		final JCheckBox noChange=new JCheckBox("锁定数据          ");
		final JCheckBox lockBox=new JCheckBox("锁定用户          ");
		final JCheckBox timeBox=new JCheckBox("显示时间          ");
		JRadioButton windowButton=new JRadioButton("Window 风格         ");
		JRadioButton numisButton=new JRadioButton("Nimbus 风格      ");
		JRadioButton windowClassButton=new JRadioButton("Window Classic 风格");
		final JButton logoutButton=new JButton("用户注销");
		JButton exitButton2=new JButton("用户注册");
		ButtonGroup styleButtonGroup=new ButtonGroup();
		styleButtonGroup.add(windowButton);styleButtonGroup.add(numisButton);styleButtonGroup.add(windowClassButton);
		//风格监听
		windowButton.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}	
			}
		});
		numisButton.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});
		winclassItem.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});

		//进货提醒监听
		remindBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cangkuguanli","root","leikewei000");
					statement=connection.createStatement();
					String sql="select * from productinfo where inver<'100'";
					ResultSet resultSet=statement.executeQuery(sql);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		//用户注销监听
		logoutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Longing();
				setVisible(false);
			}
		});
		//锁定数据监听
		noChange.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//设置表格不可更改
				boolean nochange= noChange.isSelected();
				if (nochange) {
					bgTable.setEnabled(false);
					bg2Table.setEnabled(false);
					bg3Table.setEnabled(false);
				}else {
					bgTable.setEnabled(true);
					bg2Table.setEnabled(true);
					bg3Table.setEnabled(true);
				}
			}
		});
		//显示时间监听
		timeBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//启动时间
				final boolean box=timeBox.isSelected();
				if (box==true) {
					JLabel time=new JLabel();
					setTimer(time);
				}else if (box==false) {
					setTitle("仓库管理系统");
					timeAction.stop();
					return;
				}	
			}
		});
		//锁定用户监听
		lockBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				boolean lock=lockBox.isSelected();
				if (lock) {
					logoutButton.setEnabled(false);
				}else {
					logoutButton.setEnabled(true);
				}
			}
		});
		//用户注册监听
		exitButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new AddUser();
			}
		});
		JLabel fontLabel=new JLabel("                      字体选择   ");
		String fontString[]={"宋体","楷体","华文行楷"};
		final JComboBox<String> fontComboBox=new JComboBox<String>(fontString);
		//对字体选择加监听
		JButton confirmButton=new JButton("选择");
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//修改字体
				if (fontComboBox.getSelectedIndex()==0) {
					InitGlobalFont(new Font("华文行楷", Font.PLAIN, 12));
				}
			}
		});
		functionPanel.setLayout(new FlowLayout());
		functionPanel.add(image,BorderLayout.NORTH);
		functionPanel.add(remindBox);functionPanel.add(noChange);
		functionPanel.add(lockBox);functionPanel.add(timeBox);
		functionPanel.add(windowButton);functionPanel.add(windowClassButton);functionPanel.add(numisButton);
		functionPanel.add(fontLabel);functionPanel.add(fontComboBox);functionPanel.add(confirmButton);
		JPanel mbfPanel=new JPanel();
		mbfPanel.add(logoutButton);mbfPanel.add(exitButton2);
		functionPanel.add(mbfPanel,BorderLayout.SOUTH);


		/**
		 * 主界面添加
		 */
		xxk.add(ProductManage,"产品管理");
		xxk.add(supplier,"供应商管理");
		xxk.add(view,"产品视图");
		xxk.add(functionPanel,"功能设置");
		this.add(hyLabel,BorderLayout.NORTH);
		this.add(xxk,BorderLayout.CENTER);
		this.add(exitButton,BorderLayout.SOUTH);
		//设置主页面的布局
		//this.setTimer(time);
		this.setTitle("仓库管理系统");
		this.setJMenuBar(cdlBar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(465, 750);
		this.setLocation(530, 80);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("保存.png").getImage());

	}

	/**
	 * 监听管理
	 */
	public void actionPerformed(ActionEvent e) {
		//添加产品
		if(e.getActionCommand().equals("tianjia")){
			TianJia tianJia=new TianJia();
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);
			tianJia.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		//删除产品
		else if (e.getActionCommand().equals("shanchu")) {
			int xz=this.bgTable.getSelectedRow();
			//判断是否选中
			if(xz==-1){
				JOptionPane.showMessageDialog(null, "请选中你要删除的产品");
				return;
			}else {
				//开始删除产品
				if (JOptionPane.showConfirmDialog(null, "确认删除此产品？")==JOptionPane.YES_OPTION) {
					deleteproduct(xz);
				}else {
					return;
				}					
			}

			//产品进货
		}else if (e.getActionCommand().equals("jinhuo")) {
			int xz3=this.bgTable.getSelectedRow();
			if (xz3==-1) {
				JOptionPane.showMessageDialog(null, "你选中你要进货的商品");
			}
			new AddCargob(proinfo1, xz3);
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);

		}else if (e.getActionCommand().equals("chuhuo")) {
			int xz3=this.bgTable.getSelectedRow();
			if (xz3==-1) {
				JOptionPane.showMessageDialog(null, "请选中你要出货的商品");
				return;
			}else {
				new GoCargob(proinfo1,xz3);
			}
		}
		//添加供货商	
		else if(e.getActionCommand().equals("tianjiagonghuo")){
			addsupplier addsupplier=new addsupplier();
			addsupplier.setDefaultCloseOperation(HIDE_ON_CLOSE);

			//删除供货商
		}else if (e.getActionCommand().equals("shanchugonghuo")) {
			int xz2=this.bg2Table.getSelectedRow();
			if (xz2==-1) {
				JOptionPane.showMessageDialog(null, "请选中你要删除的供货商");
				return;
			}else {
				if (JOptionPane.showConfirmDialog(null, "确认删除此供货商？")==JOptionPane.YES_OPTION) {
					DeleteSupplier(xz2);
				}else {
					return;
				}			
			}

			//修改供货商
		}else if (e.getActionCommand().equals("xiugaigonghuo")) {
			int xz2=this.bg2Table.getSelectedRow();
			if (xz2==-1) {
				JOptionPane.showMessageDialog(null, "请选中你要删除的行");
				return;
			} else {
				new ChangeSupplier(suppinfo1, xz2);
			}
		}

	}
	/**
	 * 
	 * 连接数据库
	 * @return 
	 */
	public Connection LinkData(){
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://loaclhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwordString);
			statement=connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}



	/**
	 * 执行删除产品
	 * @param xz现行选中行
	 */
	public void deleteproduct (int xz) {
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		PreparedStatement preparedStatement=null;
		String st=(String)proinfo1.getValueAt(xz,0);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwString);
			statement=connection.createStatement();
			//动态删除选中项
			preparedStatement=connection.prepareStatement("delete from productinfo where id=?");
			preparedStatement.setString(1,st);
			preparedStatement.executeUpdate();		
			JOptionPane.showMessageDialog(null, "删除成功");
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);
			connection.close();
			statement.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 执行删除供货商
	 * 
	 */
	public void DeleteSupplier(int xz2) {
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		String st=(String)suppinfo1.getValueAt(xz2,0);
		PreparedStatement preparedStatement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url, userString, passwordString);
			statement=connection.createStatement();
			preparedStatement=connection.prepareStatement("delete from supplierinfo where name=?");
			preparedStatement.setString(1,st);
			preparedStatement.executeUpdate();		
			JOptionPane.showMessageDialog(null, "删除成功");
			suppinfo1=new SupplierInfo();
			bg2Table.setModel(suppinfo1);
			statement.close();
			connection.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/*
	 * 显示时间
	 * 
	 */
	//设置Timer 1000ms实现一次动作 实际是一个线程   
	private void setTimer(JLabel time){   
		final JLabel varTime = time;   
		timeAction = new Timer(1000, new ActionListener() {          

			public void actionPerformed(ActionEvent e) { 
				//返回以毫秒为单位的当前时间
				long timemillis = System.currentTimeMillis();   
				//转换日期显示格式   
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				//format是设置格式的
				varTime.setText(df.format(new Date(timemillis)));   

				String timeString=varTime.getText();
				setTitle("仓库管理系统          "+timeString);
			}      
		});            
		timeAction.start();  
	}

	/**
	 * 改变字体
	 * @param args
	 */
	private void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements(); )
		{Object key = keys.nextElement();Object value = UIManager.get(key);
		if (value instanceof FontUIResource){UIManager.put(key, fontRes);}}
	}
	/**
	 * 将数据导入到文本中
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public void selectFromDb(){

		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url, userString, passwordString);
			statement=connection.createStatement();
			String sql="select * from productinfo";
			ResultSet resultSet=statement.executeQuery(sql);
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("c.txt"),"UTF-8");
			BufferedWriter bw = new BufferedWriter(output);
		
			while (resultSet.next()) {
				String id=resultSet.getString(1);	
				String name=resultSet.getString(2);
				String price=resultSet.getString(3);
				
				
				bw.write("id="+id+"   "+"name="+name+"    "+"price="+price+"\n");
				
			}
			bw.close();
			output.close();
			



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String args[]){
		StoreManage appManage=new StoreManage();
	}

}
