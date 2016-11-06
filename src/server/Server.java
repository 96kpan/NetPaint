package server;

/**
 * TO BE CHANGED to allow multiple clients the read and write a Vector of
 * PaintObjects every time a client writes a PaintObject to this server.
 * 
 * THIS CONTAINS SOME OF THE BOILERPLATE CODE PROVIDED HERE
 * 
 * Currently this server waits for one connection, reads the Clients message, which  
 * is printed to a console, writes a message to the Client, and closes the connection.
 *
 * @author YOUR NAME(S)
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
	
	public static final int SERVER_PORT = 4000;
	
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());
	
	private static ServerSocket socket;

	public static void main(String[] args) throws IOException {
		socket = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		Socket client;
		
		while(true) {
			client = socket.accept();
			
			ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());

			clients.add(outputToClient);
			
			ClientHandler c = new ClientHandler(inputFromClient, clients);
			c.start();
			System.out.println("Accepted a new connection from " + socket.getInetAddress());
		}
		
		
//		try {
//			// Create a ServerSocket and wait for a Client to connect
//			@SuppressWarnings("resource")
//			
//			Socket client = socket.accept();
//			// Make both connection steams available
//			ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
//			ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());
//
//			// Do a read and write
//			String messageFromClient = (String) inputFromClient.readObject();
//			System.out.println("The client wrote this to the server: " + messageFromClient);
//			outputToClient.writeObject("Hello Client. How may I serve you today?");
//
//			// Close the connection
//			client.close();
//		} catch (IOException e) {
//		} catch (ClassNotFoundException e) {
//		}
	}
}

class ClientHandler extends Thread {
	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;
	
	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
		this.input = input;
		this.clients = clients;
	}
	
	@Override
	public void run() {
		while(true) {
			String message = null;
			
			try {
				message = (String) input.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.writeStringToClients(message);
		}
	}
	
	private void writeStringToClients(String message) {
		synchronized(clients) {
			for(ObjectOutputStream client : clients) {
				try {
					client.writeObject(message);
					client.reset();
				} catch (IOException e) {
					e.printStackTrace();
					clients.remove(client);
				}
			}
		}
	}
}