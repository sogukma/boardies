package gui;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class provides text-based messaging functionality for a client.
 * We do no processing of the message contents - the client is responsible for that.
 * We manage the socket, and if the socket is closed or invalid, we create a new one.
 */
public class MessageHandler {
	private String IP_Address = null;
	private int port = -1;	
	private Socket socket = null;
	
	/**
	 * Constructor for clients that initiate a connection
	 */
	public MessageHandler(String IP_Address, int port) {
		this.IP_Address = IP_Address;
		this.port = port;
	}
	
	/**
	 * Constructor for servers, which already have a connection
	 */
	public MessageHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void close() {
		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	public boolean send(String message) {
		//diese Thread-Sleep pause stellt sicher, dass Nachrichten besser ankommen
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean sent = false;
		if (connect(true)) {
			PrintWriter out;
			try {
				out = new PrintWriter(socket.getOutputStream());
				out.println(message);
				out.flush();
				sent = true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		return sent;
	}
	
	
	public String receive() {
		String message = null;
		if (connect(false)) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				message = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	
	private boolean connect(boolean output) {
		boolean ok = true;
		if (socket == null || socket.isClosed() ||
				(output && socket.isOutputShutdown()) ||
				(!output && socket.isInputShutdown())
			) {
			// Close old socket, if present
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e1) {
				}
			
			// Open a new socket
			if (IP_Address != null) {
				try {
					this.socket = new Socket(IP_Address, port);
				} catch (Exception e) {
					ok = false;
				}
			}
		}
		return ok;
	}
}
