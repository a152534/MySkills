package org.notice.server;

    import java.io.*;
    import java.net.*;
    import java.sql.Connection;

import org.notice.client.MySkillsThread;
    

    public class MySkillsServer
    {

        private ServerSocket serversoc = null;
        private Socket clientsoc = null;
        private PrintWriter out = null;

        
        private FileReader fr = null;
        private BufferedReader filebr = null;
        private String msg = null;

        public MySkillsServer()
        {
    	try
    	{
    	    serversoc = new ServerSocket(1234);
    	   
    	    

    	} 
    	catch (IOException e)
    	{
    	    System.err.println("could not listen on port 3333");
    	    System.exit(0);
    	}

    	while (true)
    	{
    	    try
    	    {
    		System.out.println("server waiting for client connection");
    		clientsoc = serversoc.accept();
    		MySkillsThread st = new MySkillsThread(clientsoc);
    		st.start();

    	    } catch (IOException e)
    	    {
    		System.err.println("Accept Failed");
    		e.printStackTrace();
    		System.exit(0);
    	    }

    	}
        }

        

        public static void main(String[] args)
        {
    	new MySkillsServer();

        }

  

}
