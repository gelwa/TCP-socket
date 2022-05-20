import sheet3.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {
  public static void main (String args[]) {
    Team one = new Team("Frankfurt");   
    Team two = new Team("Manchester");  
    Team three = new Team("Barcelona"); 
    try{
      System.out.println("The Server is running");
	  int serverPort = 7896;
          //creates socket at port 7896, no need to specify host, will be current machine
	  ServerSocket listenSocket = new ServerSocket (serverPort);
	  while(true) {    //basically waiting for client to connect to socket
            //created (listen)socket waits for client request => becomes clientSocket
            //accept() = method to accept incoming request to socket
	    Socket clientSocket = listenSocket.accept();
	    System.out.println("New Connection");
            //starts connection through created socket
	    Connection c = new Connection(clientSocket, one, two, three);
	  }
    } catch( IOException e) {System.out.println(" Listen: "+ e.getMessage());}
  }// main
}//class

//transfers information between client and server
class Connection extends Thread {
  Team one;
  Team two;
  Team three;
  DataInputStream in;
  DataOutputStream out;
  Socket clientSocket;

  public Connection (Socket aClientSocket, Team _one, Team _two, Team _three) { 
    one = _one;
    two = _two;
    three = _three;
    try {
      clientSocket = aClientSocket;
      //Socket gets OutputStream from Socket (created by client)
      out = new DataOutputStream ( clientSocket.getOutputStream() );
      //Socket gets InputStream from Socket 
      in = new DataInputStream ( clientSocket.getInputStream() );   
      //starts delivery of incoming messages (from client)
      //start() thread starts -> Java VM calls run() (next thread)
      this.start();
    } catch(IOException e) {System.out.println(" Connection:"+ e.getMessage());}
  }

  public void run(){
    try {
        
      String[] received;  //Array to split string
     
      //read message/string from socket (from client) (encoded in UTF format)
      //reads OutputStream from client into InputStream
      String data = in.readUTF ();
      
      //splits received input string at ", "  and assigns to array received
      received = data.split(", ");
	  
      //Running method, depending on input received from client
      if (received[1].equals("add")) {
          switch (received[0]) {
              case "Frankfurt": {
                  one.add_player(received[2], received[3], Integer.parseInt(received[4]));
                  //OutputStream that is sent back to clinet
                  out.writeUTF(received[2] + ", " + received[3] + ", (" + received[4] + ") was added to Team Frankfurt");
                  break;
              }
              case "Manchester": {
                  two.add_player(received[2], received[3], Integer.parseInt(received[4]));
                  //OutputStream that is sent back to client
                  out.writeUTF(received[2] + ", " + received[3] + ", (" + received[4] + ") was added to Team Manchester");
                  break;
              }
              case "Barcelona": {
                  three.add_player(received[2], received[3], Integer.parseInt(received[4]));
                  //OutputStream that is sent back to client
                  out.writeUTF(received[2] + ", " + received[3] + ", (" + received[4] + ") was added to Team Manchester");
                  break;
              }
          }
      }
      else if (received[1].equals("names")) {
          switch (received[0]) {
              case "Frankfurt": {
                  //get_names() returns list of names by creating array
                  List<String> names = one.get_names();
                  //OutputStream that is sent back to client
                  out.writeUTF("Players of Team Frankfurt: " + names);
                  break;
              }
              case "Manchester": {
                  List<String> names = two.get_names();
                  out.writeUTF("Players of Team Manchester" + names);
                  break;
              }
              case "Barcelona": {
                  List<String> names = three.get_names();
                  out.writeUTF("Players of Team Barcelona" + names);
                  break;
              }
          }
      }
      
      
    } catch( EOFException e) {System.out.println(" EOF:"+ e.getMessage());
    } catch( IOException e) {System.out.println(" IO:"+ e.getMessage());}
  }
}