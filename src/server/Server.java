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

public class Server {

  public static void main(String[] args) {

    try {
      // Create a ServerSocket and wait for a Client to connect
      @SuppressWarnings("resource")
      ServerSocket server = new ServerSocket(4000);
      Socket client = server.accept();
      // Make both connection steams available 
      ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
      ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());

      // Do a read and write
      String messageFromClient = (String) inputFromClient.readObject();
      System.out.println("The client wrote this to the server: " + messageFromClient);
      outputToClient.writeObject("Hello Client. How may I serve you today?");

      // Close the connection
      client.close();
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
    }
  }
}