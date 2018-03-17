/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import model.User;
import utli.DataDriver;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

/**
 * @author Giang Nguyễn
 *
 *         Nhìn giề! đọc code kìa!
 */
public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel order_manager_panel;
	private JPanel all_category_panel;
	private JPanel user_manager_panel;
	private JPanel all_product_panel;
	private JPanel dashboard_panel;

	private final int SCREEN_WIDTH = 1100;
	private final int SCREEN_HEIGHT = 600;

	private int xx, xy;
	private JTable all_product_table;
	private JTable table_1;
	private DataDriver dataDriver;
	static String token = null;
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		LoadingScreen.timer.stop();
		initUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,
				SCREEN_HEIGHT);
		try {
			setIconImage(ImageIO.read(new File("data\\icon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);

		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Main.this.setLocation(x - xx, y - xy);
			}
		});

		JPanel slide = new JPanel();
		slide.setBackground(new Color(44, 62, 80));
		slide.setBounds(0, 0, 250, 600);
		contentPane.add(slide);

		JPanel slide_dashboard = new JPanel();
		slide_dashboard.setBounds(0, 138, 250, 46);
		slide_dashboard.setBackground(new Color(44, 62, 80));
		slide_dashboard.setCursor(new Cursor(Cursor.HAND_CURSOR));
		slide_dashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonBG(slide_dashboard);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButtonBG(slide_dashboard);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				order_manager_panel.setVisible(false);
				all_category_panel.setVisible(false);
				all_product_panel.setVisible(false);
				user_manager_panel.setVisible(false);
				dashboard_panel.setVisible(true);
			}
		});
		slide.setLayout(null);
		slide.add(slide_dashboard);
		slide_dashboard.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("data\\icons8-combo-chart-filled-50.png"));
		lblNewLabel.setBounds(30, 15, 15, 15);
		slide_dashboard.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dashboard");
		lblNewLabel_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(80, 11, 91, 25);
		slide_dashboard.add(lblNewLabel_1);

		JPanel slide_allproduct = new JPanel();
		slide_allproduct.setBounds(0, 184, 250, 46);
		slide_allproduct.setLayout(null);
		slide_allproduct.setBackground(new Color(44, 62, 80));
		slide_allproduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
		slide_allproduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonBG(slide_allproduct);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButtonBG(slide_allproduct);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				order_manager_panel.setVisible(false);
				all_category_panel.setVisible(false);
				all_product_panel.setVisible(true);
				user_manager_panel.setVisible(false);
				dashboard_panel.setVisible(false);
			}
		});
		slide.add(slide_allproduct);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("data\\icons8-product-50.png"));
		label.setBounds(30, 15, 15, 15);
		slide_allproduct.add(label);

		JLabel lblAllProduct = new JLabel("All Product");
		lblAllProduct.setForeground(Color.WHITE);
		lblAllProduct.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblAllProduct.setBounds(80, 11, 91, 25);
		slide_allproduct.add(lblAllProduct);

		JPanel slide_user = new JPanel();
		slide_user.setBounds(0, 230, 250, 46);
		slide_user.setLayout(null);
		slide_user.setBackground(new Color(44, 62, 80));
		slide_user.setCursor(new Cursor(Cursor.HAND_CURSOR));
		slide_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonBG(slide_user);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButtonBG(slide_user);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				order_manager_panel.setVisible(false);
				all_category_panel.setVisible(false);
				all_product_panel.setVisible(false);
				user_manager_panel.setVisible(true);
				dashboard_panel.setVisible(false);
			}
		});
		slide.add(slide_user);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("data\\icons8-account-filled-50.png"));
		label_2.setBounds(30, 15, 15, 15);
		slide_user.add(label_2);

		JLabel lblUserManagement = new JLabel("User Management");
		lblUserManagement.setForeground(Color.WHITE);
		lblUserManagement.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblUserManagement.setBounds(80, 11, 130, 25);
		slide_user.add(lblUserManagement);

		JPanel slide_category = new JPanel();
		slide_category.setBounds(0, 276, 250, 46);
		slide_category.setLayout(null);
		slide_category.setBackground(new Color(44, 62, 80));
		slide_category.setCursor(new Cursor(Cursor.HAND_CURSOR));
		slide_category.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonBG(slide_category);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButtonBG(slide_category);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				order_manager_panel.setVisible(false);
				all_category_panel.setVisible(true);
				all_product_panel.setVisible(false);
				user_manager_panel.setVisible(false);
				dashboard_panel.setVisible(false);
			}
		});
		slide.add(slide_category);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("data\\icons8-shopping-bag-24.png"));
		label_4.setBounds(30, 15, 15, 15);
		slide_category.add(label_4);

		JLabel lblAllCategory = new JLabel("All Category");
		lblAllCategory.setForeground(Color.WHITE);
		lblAllCategory.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblAllCategory.setBounds(80, 11, 91, 25);
		slide_category.add(lblAllCategory);

		JPanel slide_order = new JPanel();
		slide_order.setBounds(0, 322, 250, 46);
		slide_order.setLayout(null);
		slide_order.setBackground(new Color(44, 62, 80));
		slide_order.setCursor(new Cursor(Cursor.HAND_CURSOR));
		slide_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonBG(slide_order);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButtonBG(slide_order);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				order_manager_panel.setVisible(true);
				all_category_panel.setVisible(false);
				all_product_panel.setVisible(false);
				user_manager_panel.setVisible(false);
				dashboard_panel.setVisible(false);
			}
		});
		slide.add(slide_order);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("data\\icons8-truck-26.png"));
		label_6.setBounds(30, 15, 15, 15);
		slide_order.add(label_6);

		JLabel lblOrderManager = new JLabel("Order Manager");
		lblOrderManager.setForeground(Color.WHITE);
		lblOrderManager.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		lblOrderManager.setBounds(80, 11, 131, 25);
		slide_order.add(lblOrderManager);

		JLabel lblQunLKho = new JLabel("Sales Manager");
		lblQunLKho.setBounds(58, 53, 147, 36);
		lblQunLKho.setForeground(Color.WHITE);
		lblQunLKho.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		slide.add(lblQunLKho);

		JSeparator separator = new JSeparator();
		separator.setBounds(31, 100, 188, 9);
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.WHITE);
		slide.add(separator);

		JPanel panel_header = new JPanel();
		panel_header.setBackground(Color.WHITE);
		panel_header.setBounds(249, 0, 851, 75);
		contentPane.add(panel_header);
		panel_header.setLayout(null);

		JLabel lbl_close = new JLabel("X");
		lbl_close.setIcon(new ImageIcon("data\\cancel-black.png"));
		lbl_close.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_close.setBounds(831, 5, 15, 15);
		lbl_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_header.add(lbl_close);
		lbl_close.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lbl_minimize = new JLabel("-");
		lbl_minimize.setIcon(new ImageIcon("data\\minimize-black.png"));
		lbl_minimize.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_minimize.setBounds(809, 5, 15, 15);
		lbl_minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_header.add(lbl_minimize);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Profile.token = Main.token;
				Profile profile = new Profile();
				profile.setVisible(true);
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("data\\user-default.png"));
		lblNewLabel_6.setBounds(10, 7, 57, 57);
		lblNewLabel_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_header.add(lblNewLabel_6);

		JLabel lblUserName = new JLabel(user.getName());
		lblUserName.setForeground(new Color(51, 0, 102));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserName.setBounds(68, 11, 88, 24);
		panel_header.add(lblUserName);

		JLabel lblAdmin = new JLabel(user.getRole());
		lblAdmin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAdmin.setForeground(Color.GRAY);
		lblAdmin.setBounds(68, 36, 47, 14);
		panel_header.add(lblAdmin);
		lbl_minimize.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				setState(Frame.ICONIFIED);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		dashboard_panel = new JPanel();
		dashboard_panel.setBounds(249, 75, 851, 525);
		contentPane.add(dashboard_panel);
		dashboard_panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Welcome Back User");
		lblNewLabel_2.setForeground(new Color(51, 0, 153));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(39, 26, 170, 33);
		dashboard_panel.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(39, 129, 161, 90);
		dashboard_panel.add(panel_7);
		panel_7.setLayout(null);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(204, 102, 204));
		panel_8.setBounds(0, 0, 161, 10);
		panel_7.add(panel_8);

		JLabel label_1 = new JLabel("500");
		label_1.setForeground(new Color(51, 0, 102));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(23, 30, 98, 28);
		panel_7.add(label_1);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(new Color(204, 153, 204));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(23, 65, 51, 25);
		panel_7.add(lblTotal);

		JLabel lblNewLabel_3 = new JLabel("Complete project");
		lblNewLabel_3.setForeground(new Color(102, 51, 153));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(39, 96, 112, 22);
		dashboard_panel.add(lblNewLabel_3);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(226, 129, 161, 90);
		dashboard_panel.add(panel_9);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(204, 102, 204));
		panel_10.setBounds(0, 0, 161, 10);
		panel_9.add(panel_10);

		JLabel label_3 = new JLabel("500");
		label_3.setForeground(new Color(51, 0, 102));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_3.setBounds(23, 30, 98, 28);
		panel_9.add(label_3);

		JLabel label_8 = new JLabel("Total");
		label_8.setForeground(new Color(204, 153, 204));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(23, 65, 51, 25);
		panel_9.add(label_8);

		JLabel label_9 = new JLabel("Complete project");
		label_9.setForeground(new Color(102, 51, 153));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(226, 96, 112, 22);
		dashboard_panel.add(label_9);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(411, 129, 161, 90);
		dashboard_panel.add(panel_11);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(204, 102, 204));
		panel_12.setBounds(0, 0, 161, 10);
		panel_11.add(panel_12);

		JLabel label_10 = new JLabel("$2000");
		label_10.setForeground(new Color(51, 0, 102));
		label_10.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_10.setBounds(23, 30, 98, 28);
		panel_11.add(label_10);

		JLabel label_11 = new JLabel("Total");
		label_11.setForeground(new Color(204, 153, 204));
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(23, 65, 51, 25);
		panel_11.add(label_11);

		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setForeground(new Color(102, 51, 153));
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPayment.setBounds(411, 96, 112, 22);
		dashboard_panel.add(lblPayment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 230, 534, 266);
		dashboard_panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(580, 96, 271, 390);
		dashboard_panel.add(panel_13);
		panel_13.setLayout(null);

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(255, 255, 255));
		panel_14.setBounds(0, 33, 261, 152);
		panel_13.add(panel_14);
		panel_14.setLayout(null);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_15.setBounds(0, 211, 261, 152);
		panel_13.add(panel_15);

		JLabel lblNewLabel_4 = new JLabel("Lorem Ipsum is simply");
		lblNewLabel_4.setForeground(new Color(102, 51, 153));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(0, 11, 157, 14);
		panel_13.add(lblNewLabel_4);

		user_manager_panel = new JPanel();
		user_manager_panel.setLayout(null);
		user_manager_panel.setBounds(249, 75, 851, 525);
		user_manager_panel.setVisible(false);
		contentPane.add(user_manager_panel);

		JLabel lblUserManagement_1 = new JLabel("User Management");
		lblUserManagement_1.setForeground(new Color(51, 0, 153));
		lblUserManagement_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserManagement_1.setBounds(39, 26, 170, 33);
		user_manager_panel.add(lblUserManagement_1);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 226, 831, 299);
		user_manager_panel.add(scrollPane_4);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, },
				new String[] { "#", "Full name", "Phone", "Address", "Email", "Create time", "Update time" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(30);
		scrollPane_4.setViewportView(table_1);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "Name", "Create time", "Update time" }));
		comboBox_3.setBounds(739, 182, 80, 20);
		user_manager_panel.add(comboBox_3);

		JLabel label_13 = new JLabel("Sort By:");
		label_13.setBounds(683, 185, 46, 14);
		user_manager_panel.add(label_13);

		JButton btnUserDetail = new JButton("User detail");
		btnUserDetail.setBackground(new Color(102, 51, 153));
		btnUserDetail.setBounds(54, 163, 128, 34);
		user_manager_panel.add(btnUserDetail);

		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBackground(new Color(102, 51, 153));
		btnCreateUser.setBounds(218, 163, 128, 34);
		user_manager_panel.add(btnCreateUser);

		all_category_panel = new JPanel();
		all_category_panel.setLayout(null);
		all_category_panel.setBounds(249, 75, 851, 525);
		all_category_panel.setVisible(false);
		contentPane.add(all_category_panel);

		JLabel lblAllCategory_1 = new JLabel("All Category");
		lblAllCategory_1.setForeground(new Color(51, 0, 153));
		lblAllCategory_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAllCategory_1.setBounds(39, 26, 170, 33);
		all_category_panel.add(lblAllCategory_1);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 226, 831, 299);
		all_category_panel.add(scrollPane_3);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(739, 182, 80, 20);
		all_category_panel.add(comboBox_2);

		JLabel label_12 = new JLabel("Sort By:");
		label_12.setBounds(683, 185, 46, 14);
		all_category_panel.add(label_12);

		JButton button_2 = new JButton("Product detail");
		button_2.setBackground(new Color(102, 51, 153));
		button_2.setBounds(54, 163, 128, 34);
		all_category_panel.add(button_2);

		JButton button_3 = new JButton("Create Product");
		button_3.setBackground(new Color(102, 51, 153));
		button_3.setBounds(218, 163, 128, 34);
		all_category_panel.add(button_3);

		order_manager_panel = new JPanel();
		order_manager_panel.setLayout(null);
		order_manager_panel.setBounds(249, 75, 851, 525);
		order_manager_panel.setVisible(false);
		contentPane.add(order_manager_panel);

		JLabel lblOrderManager_1 = new JLabel("Order Manager");
		lblOrderManager_1.setForeground(new Color(51, 0, 153));
		lblOrderManager_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrderManager_1.setBounds(39, 26, 170, 33);
		order_manager_panel.add(lblOrderManager_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 226, 831, 299);
		order_manager_panel.add(scrollPane_2);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(739, 182, 80, 20);
		order_manager_panel.add(comboBox_1);

		JLabel label_7 = new JLabel("Sort By:");
		label_7.setBounds(683, 185, 46, 14);
		order_manager_panel.add(label_7);

		JButton button = new JButton("Product detail");
		button.setBackground(new Color(102, 51, 153));
		button.setBounds(54, 163, 128, 34);
		order_manager_panel.add(button);

		JButton button_1 = new JButton("Create Product");
		button_1.setBackground(new Color(102, 51, 153));
		button_1.setBounds(218, 163, 128, 34);
		order_manager_panel.add(button_1);

		all_product_panel = new JPanel();
		all_product_panel.setLayout(null);
		all_product_panel.setBounds(249, 75, 851, 525);
		all_product_panel.setVisible(false);
		contentPane.add(all_product_panel);

		JLabel lblAllProduct_1 = new JLabel("All Product");
		lblAllProduct_1.setForeground(new Color(51, 0, 153));
		lblAllProduct_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAllProduct_1.setBounds(39, 26, 170, 33);
		all_product_panel.add(lblAllProduct_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 215, 831, 299);
		all_product_panel.add(scrollPane_1);

		all_product_table = new JTable();
		all_product_table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "#", "Product name", "Description", "Import price", "Price", "Quantity", "Product Image",
						"Creator", "Category", "Create time", "Update time" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false, false, false, false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		all_product_table.getColumnModel().getColumn(0).setPreferredWidth(34);
		all_product_table.getColumnModel().getColumn(1).setPreferredWidth(86);
		all_product_table.getColumnModel().getColumn(5).setPreferredWidth(55);
		all_product_table.getColumnModel().getColumn(6).setPreferredWidth(82);
		scrollPane_1.setViewportView(all_product_table);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Name", "Price", "Category", "Update time" }));
		comboBox.setBounds(739, 171, 80, 20);
		all_product_panel.add(comboBox);

		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setBounds(683, 174, 46, 14);
		all_product_panel.add(lblSortBy);

		JButton btnProductDetail = new JButton("Product detail");
		btnProductDetail.setBackground(new Color(102, 51, 153));
		btnProductDetail.setBounds(54, 152, 128, 34);
		all_product_panel.add(btnProductDetail);

		JButton btnCreateProduct = new JButton("Create Product");
		btnCreateProduct.setBackground(new Color(102, 51, 153));
		btnCreateProduct.setBounds(218, 152, 128, 34);
		all_product_panel.add(btnCreateProduct);
		
		
	}
	
	private void initUser() {
		dataDriver = new DataDriver();
		JSONObject tmp = dataDriver.getUser(Main.token);
		
		JSONArray array = tmp.getJSONArray("data");
		JSONObject object = new JSONObject();
		for(int i = 0; i< array.length();i++) {
			object = array.getJSONObject(i);
		}
		
		int id = object.getInt("id");
		String name = object.getString("name");
		String address = object.getString("address");
		String role = object.getString("role");
		String phone = object.getString("phone");
		String created = object.getString("created");
		String email = object.getString("email");
		
		user = new User(id,name,phone,address,email,role,created);
	}
	// set panel bg color
	private void setButtonBG(JPanel jPanel) {
		jPanel.setBackground(new Color(52, 73, 94));
	}

	private void resetButtonBG(JPanel jPanel) {
		jPanel.setBackground(new Color(44, 62, 80));
	}
}
