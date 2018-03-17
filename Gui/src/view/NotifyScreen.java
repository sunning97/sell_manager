/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;

/**
 * @author Giang Nguyễn
 *
 * Nhìn cái giề, đọc code kìa!
 */
public class NotifyScreen extends JFrame {

	private int SCREEN_WIDTH = 350;
	private int SCREEN_HEIGHT = 100;
	private JPanel contentPane;
	private JLabel lblNotify;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotifyScreen frame = new NotifyScreen();
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
	public NotifyScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH,SCREEN_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(44, 62, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\Java Project\\StudentManager\\data\\warring.png"));
		lblNewLabel.setBounds(37, 11, 63, 51);
		contentPane.add(lblNewLabel);
		
		lblNotify = new JLabel("");
		lblNotify.setFont(new Font("Meiryo UI", Font.BOLD, 13));
		lblNotify.setForeground(Color.WHITE);
		lblNotify.setBounds(80, 25, 241, 25);
		contentPane.add(lblNotify);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setState(Frame.ICONIFIED);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(140, 72, 70, 25);
		btnNewButton.setBorder(null);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setBackground(new Color(46, 204, 113));
				btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(39, 174, 96));
			}
		});
		btnNewButton.setBackground(new Color(39, 174, 96));
		contentPane.add(btnNewButton);
	}
	public void setNotify(String content){
		lblNotify.setText(content);
	}
}
