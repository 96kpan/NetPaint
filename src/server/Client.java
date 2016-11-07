package server;

/**
 * TO BE INTEGRATED WITH THE GUI IN PACKAGE CLIENT AFTER YOUR PARTNER 
 * CAN DRAW GHOST SHAPES AND ADD TO A VECTOR OF PaintObject OBJECTS.
 * 
 * THIS CONTAINS SOME OF THE BOILERPLATE CODE PROVIDED HERE
 * 
 * This Client connects to a Server (if it is up and running),
 * writes a String object to the server, reads a String object
 * from the server (which is printed to the console)
 * and closes the connection to the server.
 * 
 * @author YOUR NAMES
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {

  public static void main(String[] args) {
    
    try {
      // Connect to a Server and get the two streams from the server
      Socket server = new Socket("localhost", 11);
      
      // Do some IO with the server
      ObjectOutputStream outputToServer = new ObjectOutputStream(server.getOutputStream());
      ObjectInputStream inputFromServer = new ObjectInputStream(server.getInputStream());
   
      // Do a write and read
      outputToServer.writeObject("Hello server, how are you today");
      String fromServer = (String) inputFromServer.readObject();
      System.out.println("The server wrote back: " + fromServer);
      
      // Close the connection to the server
      server.close();     
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}