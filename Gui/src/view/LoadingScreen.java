/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * @author Giang Nguyễn
 *
 *         Nhìn cái giề, đọc code kìa!
 */
public class LoadingScreen extends JFrame {

	private int SCREEN_WIDTH = 200;
	private int SCREEN_HEIGHT = 100;
	private JPanel contentPane;
	static Timer timer;
	static String token = null;
	private LoginGUI loginGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingScreen frame = new LoadingScreen();
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
	public LoadingScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,
				SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 62, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Java Project\\Gui\\data\\loading.gif"));
		lblNewLabel.setBounds(79, 20, 42, 42);
		contentPane.add(lblNewLabel);

		JLabel lblLogin = new JLabel("Login...");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Meiryo UI", Font.BOLD, 12));
		lblLogin.setBounds(78, 73, 58, 16);
		contentPane.add(lblLogin);

		timer = new Timer(1500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(LoadingScreen.token != null) {
					loginGUI.setVisible(false);
					Main.token = LoadingScreen.token;
					Main mainGUi = new Main();
					mainGUi.setVisible(true);
					setVisible(false);
				} else {
					NotifyScreen notifyScreen = new NotifyScreen();
					notifyScreen.setNotify("User name/Password is incorrect");
					notifyScreen.setVisible(true);
					setVisible(false);
					Main mainGUi = new Main();
				}
			}
			
					
		});
		timer.start();

	}

	public void setLoginGUI(LoginGUI gui) {
		this.loginGUI = gui;
	}
}
