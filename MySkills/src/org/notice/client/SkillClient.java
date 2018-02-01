package org.notice.client;


import java.io.*;
import java.net.Socket;
import java.util.Properties;
 

public class SkillClient {
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Transaction transaction;
//private Object fromServer = null; ;
	
	String server = null;
    int port = 0;
    
	 

	public SkillClient()
	{
		Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("Client.properties");

            // load a properties file
            prop.load(input);

            // get the property value 
            server = prop.getProperty("server");
            port = Integer.parseInt(prop.getProperty("port"));
          

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		
		try
		{
			socket = new Socket(server, port);
		//	socket = new Socket("localhost", 60606);
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
