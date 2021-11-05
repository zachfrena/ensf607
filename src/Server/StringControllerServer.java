package Server;

import Server.Model.PerformConcat;
import Server.Model.StringModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringControllerServer {

	private Socket aSocket;
	private ServerSocket serverSocket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private StringModel theModel;
	private ExecutorService pool;
	
	public StringControllerServer (int portNumber){
		try{
			serverSocket = new ServerSocket(portNumber);
			aSocket = serverSocket.accept();
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
			System.out.println("The server has formed a connection with a new client!");

			pool = Executors.newFixedThreadPool(5);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void runStringControllerServer(){
		while(true){
			PerformConcat pc = new PerformConcat(theModel, socketIn, socketOut);
			pool.execute(pc);
		}
	}

	public static void main(String args[]){
		try {
			StringControllerServer myServerController = new StringControllerServer(1996); //create comms channel
			myServerController.theModel = new StringModel(); //define a new Model object
			myServerController.runStringControllerServer(); //run the server

			myServerController.socketIn.close(); //close the sockets
			myServerController.socketOut.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
