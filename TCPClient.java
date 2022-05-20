
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    
    //Choosing a team and calling one of the two methods
    //creates String input with data to be sent to server
    static String userInput(Scanner scanner, String input) {
        System.out.println("===Welcome to our Sports Team Management System===");
        System.out.println("Available Teams: Frankfurt, Manchester, Barcelona");
        System.out.println("\nEnter the team you want to manage: ");
        input = input + scanner.nextLine();
        System.out.println("\nEnter 'add' to add a player or 'names' to display all names");
        String inputtemp = scanner.nextLine();
        input = input + ", " + inputtemp;
        if (inputtemp.equals("add")) {
            System.out.println("\nEnter player's name: ");
            input = input + ", " + scanner.nextLine();
            System.out.println("Enter player's position: ");
            input = input + ", " + scanner.nextLine();
            System.out.println("Enter player's age: ");
            input = input + ", " + scanner.nextLine();
        }
        else if (inputtemp.equals("names")) {
            System.out.println("\nDisplaying names of all players");
        }
        else {
            System.out.println("Error! Unknown input");
            System.exit(0);
        }
       
    return input;  
    }
  
    
  public static void main (String args[]) {
      
      String userInput =  "";
      //create Scanner for input -> passed in userInput()
      Scanner scanner = new Scanner(System.in);
    try {
          //we need host and port, we want to connect the ServerSocket at port 7896
	  int serverPort = 7896;
	  Socket s = new Socket (args[1], serverPort);
          //creates OutputStream for Socket s to send data
	  DataOutputStream out = new DataOutputStream ( s.getOutputStream());
          //creates InputStream for Socket s
	  DataInputStream in = new DataInputStream ( s.getInputStream());
          //message that we want to send to server:
          //writeUTF encodes input (one String)of method to OutputStream
          out.writeUTF(userInput(scanner, userInput));
          
          //readUTF reads incoming string from server 
	  String data = in.readUTF();
	  System.out.println("\nReceived: " + data) ;
          //close socket (not available for further network use)
	  s.close();
    }catch (UnknownHostException e){
	  System.out.println(" Sock:"+ e.getMessage());
    }catch (EOFException e){ System.out.println(" EOF:"+ e.getMessage());
    }catch (IOException e){ System.out.println(" IO: "+ e.getMessage());}
  }// main
}// class

