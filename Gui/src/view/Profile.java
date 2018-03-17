/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import model.User;
import utli.DataDriver;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;

/**
 * @author Giang Nguyễn
 *
 * Nhìn giề! đọc code kìa!
 */
public class Profile extends JFrame {

	private JPanel contentPane;
	private final int SCREEN_WIDTH = 450;
	private final int SCREEN_HEIGHT = 600;
	private int xx,xy;
	DataDriver dataDriver;
	User user;
	static String token = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		initUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,
				SCREEN_HEIGHT);
		contentPane = new JPanel();
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
				Profile.this.setLocation(x - xx, y - xy);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		panel.setBounds(0, 0, 450, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("data\\user.png"));
		lblNewLabel.setBounds(37, 54, 150, 150);
		panel.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel(user.getName());
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblUserName.setBounds(224, 79, 195, 49);
		panel.add(lblUserName);
		
		JLabel lblUserRole = new JLabel(user.getRole());
		lblUserRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserRole.setForeground(Color.LIGHT_GRAY);
		lblUserRole.setBounds(224, 139, 46, 14);
		panel.add(lblUserRole);
		
		JLabel close_btn = new JLabel("X");
		close_btn.setIcon(new ImageIcon("data\\cancel.png"));
		close_btn.setFont(new Font("Tahoma", Font.BOLD, 16));
		close_btn.setForeground(Color.WHITE);
		close_btn.setBounds(425, 11, 15, 15);
		close_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close_btn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(false);
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
		panel.add(close_btn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 266, 450, 334);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Full name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(24, 39, 92, 36);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(user.getName());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(200, 50, 229, 23);
		panel_1.add(lblNewLabel_4);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(10, 79, 434, 9);
		panel_1.add(separator_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(24, 111, 92, 36);
		panel_1.add(lblEmail);
		
		JLabel lblUserEmail = new JLabel(user.getEmail());
		lblUserEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUserEmail.setBounds(200, 122, 229, 23);
		panel_1.add(lblUserEmail);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBounds(0, 160, 440, 9);
		panel_1.add(separator_2);
		
		JLabel lblAdress = new JLabel("Phone");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdress.setBounds(24, 196, 92, 36);
		panel_1.add(lblAdress);
		
		JLabel lblUserPhone = new JLabel(user.getPhone());
		lblUserPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUserPhone.setBounds(200, 207, 229, 23);
		panel_1.add(lblUserPhone);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBounds(10, 245, 434, 2);
		panel_1.add(separator_3);
		
		JLabel label_4 = new JLabel("Adress");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_4.setBounds(20, 264, 92, 36);
		panel_1.add(label_4);
		
		JLabel lblUserAddress = new JLabel(user.getAddress());
		lblUserAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUserAddress.setBounds(196, 275, 229, 23);
		panel_1.add(lblUserAddress);
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
}
