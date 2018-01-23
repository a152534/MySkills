package org.notice.client;


import java.io.*;
import java.net.Socket;
 

public class SkillClient {
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Transaction transaction;
//private Object fromServer = null; ;
	 

	public SkillClient()
	{
		
		try
		{
			socket = new Socket("localhost", 60606);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e)
		{
			System.out.println("Client Unable to connect to Server \nCheck if server is running  on port 60606");
		}
	}
	
	public Transaction sendTransaction(Transaction trans) {
		Transaction outTrans = null ; 
		try {
			out.reset();
			out.writeObject(trans);
			Object fromServer = in.readObject();
			if(! (fromServer instanceof Transaction) ) {
				System.out.println("Did not recieve transaction back from server on transaction request");
				 
			} else {
				//System.out.println("recieved transaction back from server ");
				outTrans = (Transaction)fromServer ; 
			}
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		return outTrans ; 
	}
		
	
	
	
			 
			
	public void disconnect() {
		try {
			in.close();
			out.close(); ;
			socket.close();
		
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
