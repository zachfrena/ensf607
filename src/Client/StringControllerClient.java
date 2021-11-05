package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class StringControllerClient {

    private StringView theView; // creates new instance of the View class
    private Socket asocket; //creates socket
    private PrintWriter socketOut; // writes to server
    private BufferedReader socketIn; //reads from server
    private String sent; // string to be sent to server
    private String firstString; // variables for the user-entered values
    private String secondString;
    private int letterCase; //variable to keep track of what button was pressed

    StringControllerClient(StringView theView, String serverName, int portNumber){
        this.theView = theView;
        theView.setVisible(true);
        theView.addStringListener(new UpperStringListener(), new LowerStringListener());
        try{
            asocket = new Socket(serverName, portNumber);
            socketIn = new BufferedReader(new InputStreamReader(asocket.getInputStream()));
            socketOut = new PrintWriter(asocket.getOutputStream(), true);
        }catch (IOException e){
            theView.displayErrorMessage("Error!");
        }
    }

    class UpperStringListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent arg0){
            letterCase = 1;
            firstString = theView.getFirstString();
            secondString = theView.getSecString();
            communicate(firstString,secondString);
        }
    }

    class LowerStringListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent arg0) {
            letterCase = 2;
            firstString = theView.getFirstString();
            secondString = theView.getSecString();
            communicate(firstString,secondString);
        }
    }

    public void communicate(String firstString, String secondString){
        sent = "";
        try{
            if(letterCase==1){
                System.out.println("requested uppercase concat");
                System.out.println("Sending '" + firstString + " " + secondString + "' to the server");
                socketOut.println(firstString+"/"+secondString+"/"+"u");
            }
            if(letterCase==2){
                System.out.println("requested lowercase concat");
                System.out.println("Sending '" + firstString + " and " + secondString + "' to the server");
                socketOut.println(firstString+"/"+secondString+"/"+"l");
            }
            String response = socketIn.readLine();
            theView.setResult(response);
        }catch (IOException e){
            theView.displayErrorMessage("Error!");
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        StringView theView = new StringView(); //create new View object to pass into the ClientController constructor
        StringControllerClient myClientController = new StringControllerClient(theView, "localhost", 1996); //creating new ClientController object

    }

}
