/*	Netpaint 16
 *	Authors: Katie Pan & Niven Francis
 *
 *	Section Leaders: Bree Collins & Cody Macdonald
 *	Due: 11/7/16
 *	
 *	Last Edited: 11/7 10:10
 *
 *	Server.java-------------------------------
 *	|
 *	|	Sets up the server so it syncs all
 *	|	paint objects to clients, takes new
 *	|	clients and new paint objects.
 *	|
 *
 */

package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import model.PaintObject;

public class Server implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int SERVER_PORT = 9001;

	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());
	public static Vector<PaintObject> allPaintObjects = new Vector<PaintObject>();
	private static ServerSocket socket;

	// Sets up server ports, streams, and clients
	public static void main(String[] args) throws IOException {
		socket = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		Socket client;

		while (true) {
			client = socket.accept();

			ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());

			synchronized (clients) {
				clients.add(outputToClient);
			}
			outputToClient.reset();
			outputToClient.writeObject(allPaintObjects);
			Thread c = new ClientHandler(inputFromClient, clients);
			c.start();
			System.out.println("Accepted a new connection from " + socket.getInetAddress());
		}
	}
}

// Inner class handles clients by accepting new ones and takes in paint objects
class ClientHandler extends Thread {
	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;

	// Sets the inner class objects
	public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
		this.input = input;
		this.clients = clients;
	}

	// Gets new paint objects from clients in order to synchronize with everyone else
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (true) {
			try {
				Server.allPaintObjects = (Vector<PaintObject>) input.readObject();
				writePaintObject();
			} catch (Exception e) {
				try {
					input.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
					break;
				}
			}
		}
	}

	// If new paint objects are collected, it draws it to all clients
	private void writePaintObject() {
		synchronized (clients) {
			ArrayList<ObjectOutputStream> closed = new ArrayList<>();
			for (ObjectOutputStream client : clients) {
				try {
					client.reset();
					client.writeObject(Server.allPaintObjects);
				} catch (IOException e) {
					closed.add(client);
				}
				clients.removeAll(closed);
			}
		}
	}
}