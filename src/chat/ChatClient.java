/**
* Der ChatClient stellt die GUI des Chats für den Benutzer dar. Diese wurde mit Swing umgesetzt.
* Hier werden die Anzeigeelemente intialisiert und mit KeyListenern versehen.
* Hier werden weiter die ein- und ausgehende Nachrichten vom Server abgearbeitet.
*
* @author  Van Necati
* @version 1.0
* @since   2017-12-22 
*/

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import backend.BoardModel;

public class ChatClient {

	private BoardModel BM1;
	
	JFrame frame;
	JPanel panel;
	JTextArea area;
	JTextField field, name; 
	JButton sendB, okB;
	JScrollPane scroll;

	Socket clients;
	PrintWriter print;
	BufferedReader buffer;

	public static void main(String[] args) {
		ChatClient c = new ChatClient();
		c.GUI();
	}
	/**
	 * GUI und serverConnect() wird ausgeführt und ein boolean
	 * wird zurückgegeben.
	 */
	public void GUI() {
		frame = new JFrame("DOMINION - Chat");
		frame.setSize(600, 530);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		
		// Panel erzeugen, welches alle anderen Inhalte enthï¿½lt
		panel = new JPanel();

		area = new JTextArea();
		area.setBackground(Color.gray);
		area.setEditable(false);
		
		name = new JTextField(8);		
		
		scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(500, 400));
		scroll.setMinimumSize(new Dimension(500, 400));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		field = new JTextField(35);
		field.addKeyListener(new enterSend());

		sendB = new JButton("Send");
		okB = new JButton();
		sendB.addActionListener(new buttonSending());
		
		Font font1 = new Font("Verdana", Font.BOLD, 14);
		area.setForeground(Color.orange);
		area.setFont(font1);
		Font font2 = new Font("Helvetica", Font.BOLD, 12);
		name.setForeground(Color.red);
		name.setFont(font2);
		Font font3 = new Font("Helvetica", Font.PLAIN, 12);
		field.setForeground(Color.black);
		field.setFont(font3);
				
		panel.add(scroll);
		panel.add(name, BorderLayout.PAGE_END);
		panel.add(field);
		panel.add(sendB);
		panel.add(okB);

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		if(!serverConnect()){
			okB.setText("Not Connected");				
		}else{
			okB.setText("Connected with Server");
		}
		
		Thread thread = new Thread(new serverMessages());
		thread.start();
	}

	/**
	 * Verbindung zum Server
	 * @param Socket wird definiert
	 * @param BuffredReader wird angelegt. Clients InputStream wird gebraucht
	 * @param Outputstream des Clients wird auch gebraucht: PrintWriter wurde angelegt.
	 * @param Anzeige in der TextArea.
	 */
	public boolean serverConnect() {
		try {
			clients = new Socket("localhost", 5555);
			buffer = new BufferedReader(new InputStreamReader(clients.getInputStream()));
			print = new PrintWriter(clients.getOutputStream());
			messageToArea("Enter your name in the left field!"+"\n");

			return true;
		} catch (Exception e) {
			messageToArea("Network connection could not be etablished");
			e.printStackTrace();

			return false;
		}
	}
	/**
	 * Die Nachrichten werden dem Server übergeben.
	 */
	public void sendingToServer() {
		DateFormat dFormat = new SimpleDateFormat("dd.MM.yyy  HH:mm");
		Date d = new Date();															
        
		print.println(name.getText() + ":  " + field.getText()+
				"   -   "+dFormat.format(d));
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
	public class buttonSending implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sendingToServer();
		}
	}
	
	/**
	 * Der Thread des Clients prüft stets, ob Nachrichten
	 * vom Server angekommen sind.
	 * 
	 */
	public class serverMessages implements Runnable {

		@Override
		public void run() {
			String message;

			try {
				while ((message = buffer.readLine()) != null) {
					messageToArea(message);
					area.setCaretPosition(area.getText().length());
				}
			} catch (IOException e) {
				messageToArea("Message could not sent");
				e.printStackTrace();
			}
		}
	}
}