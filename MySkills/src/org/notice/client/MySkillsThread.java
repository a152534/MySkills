package org.notice.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MySkillsThread extends Thread
{
    
	private Socket threadsoc;
	private PrintWriter out = null;
	private BufferedReader serverbr = null;
	private ObjectOutputStream oos = null;

	public MySkillsThread(Socket threadsoc)
	{

	    this.threadsoc = threadsoc;

	}

	public void sendArray()
	{
	    String newarray[] =
	    { "brad", "karen", "tom" };
	    System.out.println("in sendarray");
	    try
	    {
		oos = new ObjectOutputStream(threadsoc.getOutputStream());
		oos.writeObject(newarray);

	    } catch (IOException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

	@Override
	public void run()
	{
	    try
	    {
		out = new PrintWriter(threadsoc.getOutputStream(), true);
		serverbr = new BufferedReader(new InputStreamReader(threadsoc.getInputStream()));
		boolean running = true;
		
		while (running)
		{
		    String reqin = serverbr.readLine();
		    if (reqin.equals("send"))
		    {
//			if ((msg = filebr.readLine()) != null)
//			{
//			    out.println(msg);
//			} else
//			{
//			    out.println("file empty");
//			    out.flush();
//			}
		    }
		    if (reqin.equals("array"))
		    {
			System.out.println("array button press");
			sendArray();
		    }
//		    running = false;
//		    if (serverbr != null)
//		    {
//			serverbr.close();
//		    }
//		    if (out != null)
//		    {
//			out.close();
//		    }
//		    if (threadsoc != null)
//		    {
//			threadsoc.close();
//		    }
//		    filebr.close();
//		    fr.close();
		}

	    } catch (

	    IOException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

   
}
