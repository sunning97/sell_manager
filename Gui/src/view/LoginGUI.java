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

import io.IOFile;
import model.User;
import utli.DataDriver;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Giang Nguyễn
 *
 *         Nhìn cái giề, đọc code kìa!
 */
public class LoginGUI extends JFrame {

	private int SCREEN_WIDTH = 800;
	private int SCREEN_HEIGHT = 379;
	private JPanel contentPane;
	private JTextField txfInputUserName;
	private JPasswordField txfInputPassword;
	private JCheckBox chckbxRememberPassword;
	private int xx, xy;

	private IOFile ioFile;
	private List<String> saveAccount;

	private DataDriver dataDriver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Manager");
		setUndecorated(true);
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,
				SCREEN_HEIGHT);
		try {
			setIconImage(ImageIO.read(new File("data\\icon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				LoginGUI.this.setLocation(x - xx, y - xy);
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		panel.setBounds(399, 0, 401, 379);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(63, 118, 271, 11);
		panel.add(separator);

		txfInputUserName = new JTextField();
		txfInputUserName.setFont(new Font("Meiryo", Font.BOLD, 14));
		txfInputUserName.setForeground(Color.WHITE);
		txfInputUserName.setBackground(new Color(44, 62, 80));
		txfInputUserName.setBounds(63, 95, 271, 23);
		txfInputUserName.setBorder(null);
		txfInputUserName.setCaretColor(Color.WHITE);
		panel.add(txfInputUserName);
		txfInputUserName.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(63, 216, 271, 11);
		panel.add(separator_1);

		txfInputPassword = new JPasswordField();
		txfInputPassword.setForeground(Color.WHITE);
		txfInputPassword.setBackground(new Color(44, 62, 80));
		txfInputPassword.setBounds(63, 193, 271, 23);
		txfInputPassword.setBorder(null);
		txfInputPassword.setCaretColor(Color.WHITE);
		panel.add(txfInputPassword);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setBackground(new Color(46, 204, 113));
				btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(39, 174, 96));
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginProcess();
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(39, 174, 96));
		btnNewButton_1.setBounds(137, 295, 139, 44);
		btnNewButton_1.setBorder(null);
		panel.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(63, 70, 49, 14);
		panel.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		lblPassword.setBounds(63, 168, 73, 14);
		panel.add(lblPassword);

		chckbxRememberPassword = new JCheckBox("Remember me");
		chckbxRememberPassword.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 11));
		chckbxRememberPassword.setBackground(new Color(44, 62, 80));
		chckbxRememberPassword.setForeground(new Color(52, 152, 219));
		chckbxRememberPassword.setBounds(62, 240, 139, 23);
		chckbxRememberPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(chckbxRememberPassword);

		JLabel lblForgotYourPassword = new JLabel("Forgot your password?");
		lblForgotYourPassword.setForeground(new Color(52, 152, 219));
		lblForgotYourPassword.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 11));
		lblForgotYourPassword.setBounds(212, 243, 122, 14);
		lblForgotYourPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(lblForgotYourPassword);

		JLabel lblExitButton = new JLabel("");
		lblExitButton.setIcon(new ImageIcon("data\\cancel.png"));
		lblExitButton.setFont(new Font("STXinwei", Font.BOLD, 21));
		lblExitButton.setForeground(Color.WHITE);
		lblExitButton.setBounds(378, 10, 15, 15);
		lblExitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExitButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				checkSaveWhenClose();
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
		panel.add(lblExitButton);

		JLabel lblMinimize = new JLabel("");
		lblMinimize.setIcon(new ImageIcon("data\\minimize.png"));
		lblMinimize.setForeground(Color.WHITE);
		lblMinimize.setFont(new Font("STXinwei", Font.BOLD, 21));
		lblMinimize.setBounds(352, 10, 15, 15);
		lblMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMinimize.addMouseListener(new MouseListener() {

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
		panel.add(lblMinimize);

		JPanel pnlOverlay = new JPanel();
		pnlOverlay.setBackground(new Color(0, 0, 0, 150));
		pnlOverlay.setBounds(0, 0, 400, 379);
		contentPane.add(pnlOverlay);
		pnlOverlay.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sale Manager");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Bellerose", Font.BOLD, 40));
		lblNewLabel_1.setBounds(39, 175, 244, 82);
		pnlOverlay.add(lblNewLabel_1);

		JLabel lblMakeYour = new JLabel("Manage your product easily");
		lblMakeYour.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblMakeYour.setForeground(Color.WHITE);
		lblMakeYour.setBounds(160, 268, 206, 14);
		pnlOverlay.add(lblMakeYour);

		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("data\\bglg.jpg"));
		lblBackground.setBounds(0, 0, 400, 379);
		contentPane.add(lblBackground);

		ioFile = new IOFile();
		checkSaveWhenOpen();
	}

	protected void loginProcess() {
		dataDriver = new DataDriver();
		String email = txfInputUserName.getText().toString().trim();
		String password = txfInputPassword.getText().toString().trim();
		JSONObject obj = dataDriver.getToken(email, password);
		
		LoadingScreen loadingScreen = new LoadingScreen();
		loadingScreen.setVisible(true);
		
		if (obj == null) {
		} else {
			System.out.println(obj.toString());
			String token = (String) obj.get("token");
			loadingScreen.token = token;
			loadingScreen.setLoginGUI(this);
		}
		checkSaveWhenClose();
	}

	private void checkSaveWhenClose() {
		saveAccount = new ArrayList<>();
		String name = txfInputUserName.getText().toString();
		String pass = txfInputPassword.getText().toString();
		String isSave = String.valueOf(chckbxRememberPassword.isSelected());
		saveAccount.add(isSave);
		saveAccount.add(name);
		saveAccount.add(pass);
		ioFile.writeData(saveAccount, "data/data.txt");
	}

	private void checkSaveWhenOpen() {
		saveAccount = ioFile.readData("data/data.txt");
		boolean isChecker = Boolean.parseBoolean(saveAccount.get(0));
		String name = saveAccount.get(1);
		String pass = saveAccount.get(2);
		if (name.length() != 0) {
			if (isChecker) {
				chckbxRememberPassword.setSelected(isChecker);
				txfInputPassword.setText(pass);
				txfInputUserName.setText(name);
			}
		}
	}
}
