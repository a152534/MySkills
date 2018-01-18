package org.notice.server;



 
 

import java.io.EOFException;

//the server should recieve transactions from the client
//the server should let the vendingengine handle the transaction

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.notice.buscontrol.BusinessControl;
import org.notice.client.Transaction;

 

public class SkillsServerWorkerThread implements Runnable

{
	private Thread thread;
	private BusinessControl bc;
	private Socket clientSocket;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	public SkillsServerWorkerThread(Socket clientSocket, BusinessControl bc) {
		thread = new Thread(this);
		this.bc = bc;
		this.clientSocket = clientSocket;

		try {
			out = new ObjectOutputStream(this.clientSocket.getOutputStream()); // get the output stream of client.
			in = new ObjectInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Thread getThread() {

		return thread;
	}

	@Override
	public void run() {
		Transaction fromClient;
		try {
			while ((fromClient =(Transaction) in.readObject()) != null) {
					System.out.println("SkillServerWorkerThread: recieved transaction");
					handleTransaction((Transaction) fromClient);
			}
		}
		catch (EOFException e ) {
			System.out.println("Connection Closed from client");
		}
		catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	
	private void handleTransaction(Transaction fromClient) {
		System.out.println("SkillServerWorkerThread: before call bc,handleTransaction");
		Transaction fromServer = bc.handleTransaction(fromClient);
		System.out.println("SkillServerWorkerThread: After call bc.handleTransaction");
		try {
			out.writeObject(fromClient);
			System.out.println("SkillServerWorkerThread: After write to socket");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
