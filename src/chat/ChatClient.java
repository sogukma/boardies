
package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Der ChatClient stellt die GUI des Chats für den Benutzer dar. Diese wurde mit
 * Swing umgesetzt. Hier werden die Anzeigeelemente intialisiert und mit
 * KeyListenern versehen. Hier werden weiter die ein- und ausgehende Nachrichten
 * vom Server abgearbeitet.
 *
 * @author Van Necati
 * @version 1.0
 * @since 2017-12-22
 */
public class ChatClient {

	JFrame frame;
	JPanel panel;
	JTextArea area;
	JTextField field, name;
	JButton sendB;
	JLabel label;
	JScrollPane scroll;

	Socket clients;
	PrintWriter print;
	BufferedReader buffer;

	/**
	 * Neue Instanz der Klasse ChatClient wird angelegt und davon die Methode
	 * Gui erzeugt.
	 */
	public static void main(String[] args) {
		ChatClient c = new ChatClient();
		c.Gui();
	}

	/**
	 * GUI und serverConnect() wird ausgeführt und ein boolean wird
	 * zurückgegeben.
	 */
	public void Gui() {
		frame = new JFrame("DOMINION - Chat");
		frame.setSize(615, 515);
		frame.setResizable(false);

		panel = new JPanel();
		Color pb = new Color(224, 224, 224);
		panel.setBackground(pb);

		area = new JTextArea();
		area.setBackground(Color.gray);
		area.setEditable(false);

		name = new JTextField(8);

		scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(525, 400));
		scroll.setMinimumSize(new Dimension(525, 400));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		field = new JTextField(35);
		field.addKeyListener(new enterSend());

		sendB = new JButton("Send");
		label = new JLabel();
		sendB.addActionListener(new buttonSend());

		Font fontA = new Font("Verdana", Font.BOLD, 14);
		area.setForeground(Color.orange);
		area.setFont(fontA);
		Font fontB = new Font("Helvetica", Font.BOLD, 16);
		name.setForeground(Color.red);
		name.setFont(fontB);
		Font fontC = new Font("Helvetica", Font.PLAIN, 13);
		field.setForeground(Color.black);
		field.setFont(fontC);
		Font fontD = new Font("Helvetica", Font.BOLD, 16);
		area.setForeground(Color.orange);
		area.setFont(fontD);

		panel.add(scroll);
		panel.add(name, BorderLayout.PAGE_END);
		panel.add(field);
		panel.add(sendB);
		panel.add(label);

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

		if (!serverConnect()) {
			label.setText("Not Connected with server");
			label.setForeground(Color.red);
			label.setFont(fontD);
		} else {
			label.setText("Connected with server");
			Color lc = new Color(0, 153, 0);
			label.setForeground(lc);
			label.setFont(fontD);
		}

		Thread thread = new Thread(new serverMessages());
		thread.start();
	}

	/**
	 * Verbindung zum Server
	 * 
	 * clients: Socket wird definiert 
	 * buffer: BufferedReader wird angelegt. Dafür wird Clients InputStream wird benötigt. 
	 * print: Outputstream des Clients wird auch gebraucht: PrintWriter wurde angelegt.
	 */
	public boolean serverConnect() {
		try {
			clients = new Socket(ChatServer.CHAT_IP_ADRESS, ChatServer.CHAT_PORT);
			buffer = new BufferedReader(new InputStreamReader(clients.getInputStream()));
			print = new PrintWriter(clients.getOutputStream());
			messageToArea("Enter your name in the left field!" + "\n");

			return true;
		} catch (Exception e) {
			messageToArea("Network connection could not be etablished");
			e.printStackTrace();

			return false;
		}
	}

	/**
	 * Die Nachrichten werden dem Server übergeben.
	 * 
	 *  d: gebrauch für Datum.
	 */
	public void sendingToServer() {
		DateFormat dFormat = new SimpleDateFormat("dd.MM.yyy  HH:mm");
		Date d = new Date();

		print.println(name.getText() + ":  " + field.getText() + "   -   " + dFormat.format(d));
		print.flush();

		field.setText("");
	}

	public void messageToArea(String message) {
		area.append(message + "\n");
	}

	/**
	 * Beim Druecken der Enter-Taste, wird sendingToServer aufgerufen.
	 */
	public class enterSend implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				sendingToServer();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	/**
	 * Bei Button-Klick, wird sendingToServer aufgerufen.
	 */
	public class buttonSend implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sendingToServer();
		}
	}

	/**
	 * Der Thread des Clients prüft stets, ob Nachrichten vom Server angekommen
	 * sind.
	 * 
	 * note:  die Nachticht soll in die TextArea reingeschrieben werden.
	 * 
	 */
	public class serverMessages implements Runnable {

		@Override
		public void run() {
			String note;

			try {
				while ((note = buffer.readLine()) != null) {
					messageToArea(note);
					area.setCaretPosition(area.getText().length());
				}
			} catch (IOException e) {
				messageToArea("Message could not sent");
				e.printStackTrace();
			}
		}
	}
}